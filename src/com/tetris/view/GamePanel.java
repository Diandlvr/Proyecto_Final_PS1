package com.tetris.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import com.tetris.controller.Tetrisgame;

public class GamePanel extends JPanel {
    private Tetrisgame juego;

    public GamePanel(Tetrisgame juego) {
        this.juego = juego;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Aquí dibujarías cada celda y la pieza actual, por ejemplo:
        // Tablero t = juego.getTablero();
        // Piezas p = juego.getPiezaActual();
        // ... recorrer matriz y p.getForma() ...
    }
}
