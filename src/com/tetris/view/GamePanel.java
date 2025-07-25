package com.tetris.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Font;
import java.awt.RenderingHints;

import com.tetris.controller.Tetrisgame;
import com.tetris.model.Celdas;
import com.tetris.model.Tablero;

public class GamePanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private Tetrisgame juego;

    public GamePanel(Tetrisgame juego) {
        this.juego = juego;
        int pw = Tablero.COLUMNAS * CELL_SIZE + 200;
        int ph = Tablero.FILAS * CELL_SIZE;
        setPreferredSize(new Dimension(pw, ph));
        setBackground(new Color(40, 40, 40));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int boardW = Tablero.COLUMNAS * CELL_SIZE;
        int boardH = Tablero.FILAS * CELL_SIZE;

        // Dibujar información del juego
        dibujarInformacion(g2, boardW);
        
        // Dibujar siguiente pieza
        dibujarSiguientePieza(g2, boardW);
        
        // Dibujar controles
        dibujarControles(g2, boardW);

        // Game over
        if (juego.isGameOver()) {
            dibujarGameOver(g2, boardW, boardH);
            return;
        }
        
        // Pausa
        if (juego.isPausado()) {
            dibujarPausa(g2, boardW, boardH);
            return;
        }

        // Dibujar tablero
        dibujarTablero(g2);
        
        // Dibujar pieza actual
        dibujarPiezaActual(g2);
    }
    
    private void dibujarInformacion(Graphics2D g2, int boardW) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        
        int y = 30;
        g2.drawString("TETRIS", boardW + 20, y);
        
        y += 40;
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.drawString("Puntuación: " + juego.getPuntuacion(), boardW + 20, y);

        
        y += 25;
        g2.drawString("Líneas: " + juego.getLineasCompletadas(), boardW + 20, y);
    }
    
    private void dibujarSiguientePieza(Graphics2D g2, int boardW) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Siguiente:", boardW + 20, 180);
        
        // Dibujar siguiente pieza
        Point[] forma = juego.getSiguientePieza().getForma();
        Color color = juego.getSiguientePieza().getColor();
        
        for (Point p : forma) {
            int drawX = boardW + 50 + p.x * 20;
            int drawY = 200 + p.y * 20;
            g2.setColor(color);
            g2.fillRect(drawX, drawY, 20, 20);
            g2.setColor(Color.BLACK);
            g2.drawRect(drawX, drawY, 20, 20);
        }
    }
    
    private void dibujarControles(Graphics2D g2, int boardW) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Controles:", boardW + 20, 320);
        
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        String[] controles = {
            "← → : Mover",
            "↓ : Bajar",
            "↑ : Rotar",
            "Espacio : Caída Rápida",
            "P : Pausar",
            "R : Reiniciar"
        };
        
        int y = 340;
        for (String control : controles) {
            g2.drawString(control, boardW + 20, y);
            y += 20;
        }
    }
    
    private void dibujarGameOver(Graphics2D g2, int boardW, int boardH) {
        // Fondo semi-transparente
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, boardW, boardH);
        
        // Texto Game Over
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.setColor(Color.RED);
        String gameOver = "GAME OVER";
        int textWidth = g2.getFontMetrics().stringWidth(gameOver);
        g2.drawString(gameOver, (boardW - textWidth) / 2, boardH / 2 - 20);
        
        // Puntuación final
        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        g2.setColor(Color.WHITE);
        String puntuacion = "Puntuación: " + juego.getPuntuacion();
        textWidth = g2.getFontMetrics().stringWidth(puntuacion);
        g2.drawString(puntuacion, (boardW - textWidth) / 2, boardH / 2 + 20);
        
        // Instrucción para reiniciar
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        String reiniciar = "Presiona R para reiniciar";
        textWidth = g2.getFontMetrics().stringWidth(reiniciar);
        g2.drawString(reiniciar, (boardW - textWidth) / 2, boardH / 2 + 50);
    }
    
    private void dibujarPausa(Graphics2D g2, int boardW, int boardH) {
        // Fondo semi-transparente
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, boardW, boardH);
        
        // Texto Pausa
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.setColor(Color.YELLOW);
        String pausa = "PAUSA";
        int textWidth = g2.getFontMetrics().stringWidth(pausa);
        g2.drawString(pausa, (boardW - textWidth) / 2, boardH / 2);
    }
    
    private void dibujarTablero(Graphics2D g2) {
        // Borde del tablero
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, Tablero.COLUMNAS * CELL_SIZE, Tablero.FILAS * CELL_SIZE);
        
        // Dibujar celdas del tablero
        Celdas[][] m = juego.getTablero().getMatriz();
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x].isOcupada()) {
                    g2.setColor(m[y][x].getColor());
                } else {
                    g2.setColor(new Color(60, 60, 60));
                }
                g2.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g2.setColor(Color.BLACK);
                g2.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
    
    private void dibujarPiezaActual(Graphics2D g2) {
        Point pos = juego.getPiezaActual().getPosicion();
        for (Point p : juego.getPiezaActual().getForma()) {
            int drawX = (pos.x + p.x) * CELL_SIZE;
            int drawY = (pos.y + p.y) * CELL_SIZE;
            g2.setColor(juego.getPiezaActual().getColor());
            g2.fillRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
            g2.setColor(Color.BLACK);
            g2.drawRect(drawX, drawY, CELL_SIZE, CELL_SIZE);
        }
    }
}



