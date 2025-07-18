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
import com.tetris.view.GamePanel;


public class Ventana  extends JFrame {

    private Tetrisgame juego;
    private GamePanel panel;
    private Timer timer;

    public Ventana() {
        super("Tetris");
        juego = new Tetrisgame();
        panel = new GamePanel(juego);
        juego.setVista(panel);

        // Panel enlazado al juego
        this.add(panel);
        // Lo insertamos en el JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);
        // para terminar el programa cuando cierra
        setSize(300, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        //
        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "moverIzq");
        am.put("moverIzq", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.moverIzquierda();
            }
        });
        im.put(KeyStroke.getKeyStroke("RIGHT"), "moverDer");
        am.put("moverDer", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.moverDerecha();
            }
        });

        im.put(KeyStroke.getKeyStroke("DOWN"), "bajar");
        am.put("bajar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.bajarFila();
            }
        });

        im.put(KeyStroke.getKeyStroke("SPACE"), "rotar");
        am.put("rotar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                juego.rotarPieza();
            }
        });

        // Loop de juego: baja la pieza cada 500 ms
        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juego.pasoDelJuego();
            }
        });
        timer.start();

        setVisible(true);
    }
}

