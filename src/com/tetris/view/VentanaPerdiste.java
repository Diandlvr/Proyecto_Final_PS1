package com.tetris.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaPerdiste extends JFrame {
    public VentanaPerdiste() {
        setTitle("GAME OVER");
        setSize(520, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(5, 5, 20));

        JLabel mensaje = new JLabel("¡PERDISTE!", SwingConstants.CENTER);
        mensaje.setFont(new Font("Press Start 2P", Font.BOLD, 42)); 
        mensaje.setForeground(new Color(255, 0, 80)); 
        mensaje.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(mensaje, BorderLayout.CENTER);

        // Panel de botones 
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(5, 5, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        panelBotones.setLayout(new GridLayout(1, 2, 40, 0));

        JButton btnReintentar = new JButton("▶ REINTENTAR");
        btnReintentar.setBackground(new Color(0, 255, 180)); 
        btnReintentar.setForeground(Color.BLACK);
        btnReintentar.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
        btnReintentar.setFocusPainted(false);

        JButton btnSalir = new JButton("■ SALIR");
        btnSalir.setBackground(new Color(255, 120, 0)); 
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
        btnSalir.setFocusPainted(false);

        // Acciones
        btnReintentar.addActionListener((ActionEvent e) -> {
            dispose();
            new Ventana(); // vuelve a abrir el juego
        });

        btnSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        panelBotones.add(btnReintentar);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
