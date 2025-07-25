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
        panelLogos.setBackground(Color.WHITE);
        panelLogos.setPreferredSize(new Dimension(800, 120));

        // Cargar y escalar imágenes
        ImageIcon utpIcon = new ImageIcon(getClass().getResource("/logoutp.jpg"));
        ImageIcon fiscIcon = new ImageIcon(getClass().getResource("/logosistemas.png"));

        Image utpImg = utpIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image fiscImg = fiscIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        JLabel logoUTP = new JLabel(new ImageIcon(utpImg));
        JLabel logoFISC = new JLabel(new ImageIcon(fiscImg));

        // Agregar padding para no pegarlos a los bordes
        logoUTP.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        logoFISC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        // Añadir al panel
        panelLogos.add(logoUTP, BorderLayout.WEST);
        panelLogos.add(logoFISC, BorderLayout.EAST);

        // Panel de contenido central
        JPanel panelCentro = new JPanel(new GridLayout(10, 1));
        panelCentro.setBackground(Color.WHITE);

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
