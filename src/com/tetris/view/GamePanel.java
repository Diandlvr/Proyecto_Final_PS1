package com.tetris.view;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Font;

import com.tetris.controller.Tetrisgame;
import com.tetris.model.Celdas;
import com.tetris.model.Tablero;
import com.tetris.model.Piezas;

public class GamePanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private Tetrisgame juego;

    public GamePanel(Tetrisgame juego) {
        this.juego = juego;
        int pw = Tablero.COLUMNAS * CELL_SIZE + 150;
        int ph = Tablero.FILAS   * CELL_SIZE;
        setPreferredSize(new Dimension(pw, ph));
        setBackground(Color.DARK_GRAY);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int boardW = Tablero.COLUMNAS * CELL_SIZE;
        int boardH = Tablero.FILAS   * CELL_SIZE;

        //Puntuacion
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.drawString("Puntos: " + juego.getPuntuacion(),
                boardW + 20, 20);

        //Gameover
        if (juego.isGameOver()) {
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", 30, boardH / 2);
            return;
        }

        //Se dibujan las celdas que van a estar fijas en el tablero
        Celdas[][] m = juego.getTablero().getMatriz();
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x].isOcupada()) {
                    g2.setColor(m[y][x].getColor());
                } else {
                    g2.setColor(Color.LIGHT_GRAY);
                }
                g2.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g2.setColor(Color.BLACK);
                g2.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }


        // 2) Dibujar la pieza en movimiento
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



