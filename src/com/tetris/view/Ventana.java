package com.tetris.view;
//esta clase es para representar cada casilla del tablero
import javax.swing.JFrame;
import com.tetris.controller.Tetrisgame;

public class Ventana  extends JFrame {
    private Tetrisgame juego;
    public Ventana(Tetrisgame juego ) {
        super("Tetris");
        this.juego = juego;
        //TODO: configurar BorderLayout, tamaño, operaciones de cierre
        //TODO: añadir GamePanel y ControlPanel

    }
        public void iniciar() {
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
