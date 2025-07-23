package com.tetris;

import com.tetris.controller.Tetrisgame;
import com.tetris.view.GamePanel;
import com.tetris.view.Ventana;

public class Main {
    public static void main(String[] args) {
        Tetrisgame juego = new Tetrisgame();

        GamePanel panel = new GamePanel(juego);
        juego.setVista(panel);

        new Ventana();
    }
}
