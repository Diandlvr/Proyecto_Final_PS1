package com.tetris.view;
//esta clase es para representar cada casilla del tablero...
import javax.swing.JFrame;

import javax.swing.Timer;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// control y panel
import com.tetris.controller.Tetrisgame;


public class Ventana  extends JFrame {

    private Tetrisgame juego;
    private GamePanel panel;
    private Timer timer;

    public Ventana() {
        super("Tetris - Juego Clásico");
        juego = new Tetrisgame();
        panel = new GamePanel(juego);
        juego.setVista(panel);

        // Panel enlazado al juego
        this.add(panel);
        
        // Configuración de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Configuración de controles de teclado
        configurarControles();
        
        // Loop de juego con velocidad dinámica
        timer = new Timer(juego.getVelocidad(), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juego.pasoDelJuego();
                // Actualizar velocidad del timer
                timer.setDelay(juego.getVelocidad());
            }
        });
        timer.start();

        setVisible(true);
    }
    
    private void configurarControles() {
        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

       // Movimiento izquierda
        im.put(KeyStroke.getKeyStroke("LEFT"), "moverIzq");
        am.put("moverIzq", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.moverIzquierda();
            }
        });
        
        // Movimiento derecha
        im.put(KeyStroke.getKeyStroke("RIGHT"), "moverDer");
        am.put("moverDer", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.moverDerecha();
            }
        });

        // Soft drop (bajar)
        im.put(KeyStroke.getKeyStroke("DOWN"), "bajar");
        am.put("bajar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.bajarFila();
            }
        });

        // Hard drop (espacio)
        im.put(KeyStroke.getKeyStroke("SPACE"), "hardDrop");
        am.put("hardDrop", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.hardDrop();
            }
        });

        // Rotación (flecha arriba)
        im.put(KeyStroke.getKeyStroke("UP"), "rotar");
        am.put("rotar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.rotarPieza();
            }
        });
        
        // Pausa (P)
        im.put(KeyStroke.getKeyStroke("P"), "pausar");
        am.put("pausar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.togglePausa();
            }
        });
        
        // Reiniciar (R)
        im.put(KeyStroke.getKeyStroke("R"), "reiniciar");
        am.put("reiniciar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.reiniciarJuego();
            }
    }
}
