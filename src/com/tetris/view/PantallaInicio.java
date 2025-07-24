package com.tetris.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicio extends JFrame {
    public PantallaInicio() {
        setTitle("Presentación - Tetris");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con logos (UTP y FISC)
        JPanel panelLogos = new JPanel(new BorderLayout());
        panelLogos.setBackground(Color.BLACK);

        JLabel logoUTP = new JLabel(new ImageIcon("e498a239-6169-4d31-91ec-b62ecebfd0e3.png"));
        JLabel logoFISC = new JLabel(new ImageIcon("a603a410-0fcb-4501-a5b0-65317ffa5515.png"));
        logoUTP.setHorizontalAlignment(SwingConstants.LEFT);
        logoFISC.setHorizontalAlignment(SwingConstants.RIGHT);

        panelLogos.add(logoUTP, BorderLayout.WEST);
        panelLogos.add(logoFISC, BorderLayout.EAST);

        // Panel de contenido central
        JPanel panelCentro = new JPanel(new GridLayout(10, 1));
        panelCentro.setBackground(Color.WHITE);

        JLabel universidad = new JLabel("Universidad Tecnológica de Panamá", SwingConstants.CENTER);
        JLabel facultad = new JLabel("Facultad de Ingeniería de Sistemas Computacionales", SwingConstants.CENTER);
        JLabel carrera = new JLabel("Carrera: Ingeniería de Software", SwingConstants.CENTER);
        JLabel grupo = new JLabel("Grupo: 1SF125", SwingConstants.CENTER);
        JLabel integrantes = new JLabel("<html>Integrantes:<br>Danah Rodríguez (6-727-2155)<br>María Rodríguez (6-727-1793)<br>Juan Pittí (2-755-783)</html>", SwingConstants.CENTER);
        JLabel profesor = new JLabel("Profesor: Rodrigo Yangüez", SwingConstants.CENTER);
        JLabel fecha = new JLabel("Fecha de entrega: 25/7/25", SwingConstants.CENTER);

        Font fuente = new Font("Helvetica", Font.BOLD, 16);
        JLabel[] etiquetas = {universidad, facultad, carrera, grupo, integrantes, profesor, fecha};
        for (JLabel lbl : etiquetas) {
            lbl.setFont(fuente);
            lbl.setForeground(Color.BLACK);
            panelCentro.add(lbl);
        }

        // Botón de iniciar juego
        JButton iniciarBtn = new JButton("Iniciar Juego");
        iniciarBtn.setFont(new Font("Arial", Font.BOLD, 18));
        iniciarBtn.setBackground(Color.GREEN);
        iniciarBtn.setFocusPainted(false);

        iniciarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ventana();  // Inicia el juego
                dispose();      // Cierra esta ventana
            }
        });

        add(panelLogos, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(iniciarBtn, BorderLayout.SOUTH);
    }
}
