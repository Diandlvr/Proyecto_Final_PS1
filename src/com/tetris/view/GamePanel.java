package com.tetris.view;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.tetris.controller.Tetrisgame;
import com.tetris.model.Celdas;
import com.tetris.model.Tablero;

public class GamePanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private Tetrisgame juego;

    public GamePanel(Tetrisgame juego) {
        this.juego = juego;
        setPreferredSize(new Dimension(
                Tablero.COLUMNAS * CELL_SIZE,
                Tablero.FILAS   * CELL_SIZE
        ));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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