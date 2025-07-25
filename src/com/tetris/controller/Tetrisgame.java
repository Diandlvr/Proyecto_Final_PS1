package com.tetris.controller;

import com.tetris.model.Piezas;
import com.tetris.model.PiezasFactory;
import com.tetris.model.Tablero;
import com.tetris.view.GamePanel;
import java.awt.Point;
import java.util.Arrays;


public class Tetrisgame {
    private Tablero tablero;
    private Piezas piezaActual;
    private Piezas siguientePieza;
    private PiezasFactory fabrica;
    private GamePanel vista;
    private int puntuacion;
    private int nivel;
    private int lineasCompletadas;
    private boolean gameOver;
    private boolean pausado;
    private int velocidad;


    
    public Tetrisgame() {
        this.tablero = new Tablero();
        this.fabrica = new PiezasFactory();
        this.piezaActual = fabrica.nuevaPieza();
        this.siguientePieza = fabrica.nuevaPieza();
        this.puntuacion = 0;
        this.nivel = 1;
        this.lineasCompletadas = 0;
        this.gameOver = false;
        this.pausado = false;
        this.velocidad = 500; // ms entre caídas
    }

    // baja la pieza, si no cabe, la bloquea
    public void pasoDelJuego() {
        if (gameOver || pausado) return;
        
        if (!tablero.puedeColocar(piezaActual, 0, 0)) {
            gameOver = true;
             javax.swing.SwingUtilities.invokeLater(() -> new com.tetris.view.VentanaPerdiste().setVisible(true));
            return;
        }
        
        if (tablero.puedeColocar(piezaActual, 0, 1)) {
            piezaActual.mover(0, 1);
        } else {
            bloquearPieza();
        }
        vista.repaint();
    }

    public void setVista(GamePanel vista) {
        this.vista = vista;
    }

    // fija la pieza, limpia líneas y pide nueva
    public void bloquearPieza() {
        for (Point b : piezaActual.getForma()) {
            int x = piezaActual.getPosicion().x + b.x;
            int y = piezaActual.getPosicion().y + b.y;
            tablero.getCeldas(x, y).ocupar(piezaActual.getColor());
        }
        
        int lineas = tablero.limpiarLineas();
        lineasCompletadas += lineas;
        
        // Sistema de puntuación 
        if (lineas > 0) {
            switch (lineas) {
                case 1: puntuacion += 40 * nivel; break;
                case 2: puntuacion += 100 * nivel; break;
                case 3: puntuacion += 300 * nivel; break;
                case 4: puntuacion += 1200 * nivel; break;
            }
        }
        
        // Sistema de niveles
        int nuevoNivel = (lineasCompletadas / 10) + 1;
        if (nuevoNivel > nivel) {
            nivel = nuevoNivel;
            velocidad = Math.max(50, 500 - (nivel - 1) * 50); // Aumenta velocidad
        }
        
        piezaActual = siguientePieza;
        siguientePieza = fabrica.nuevaPieza();
        vista.repaint();
    }

    // movimientos laterales
    public void moverIzquierda() {
        if (!gameOver && !pausado && tablero.puedeColocar(piezaActual, -1, 0)) {
            piezaActual.mover(-1, 0);
            vista.repaint();
        }
    }

    public void moverDerecha() {
        if (!gameOver && !pausado && tablero.puedeColocar(piezaActual, 1, 0)) {
            piezaActual.mover(1, 0);
            vista.repaint();
        }
    }

    // "soft drop"
    public void bajarFila() {
        if (!gameOver && !pausado) {
            if (tablero.puedeColocar(piezaActual, 0, 1)) {
                piezaActual.mover(0, 1);
            } else {
                bloquearPieza();
            }
            vista.repaint();
        }
    }

    // "hard drop"
    public void hardDrop() {
        if (!gameOver && !pausado) {
            int filasBajadas = 0;
            while (tablero.puedeColocar(piezaActual, 0, 1)) {
                piezaActual.mover(0, 1);
                filasBajadas++;
            }
            puntuacion += filasBajadas * 2; // Puntos por hard drop
            bloquearPieza();
        }
    }

    // rotación con rollback si choca
    public void rotarPieza() {
        if (!gameOver && !pausado) {
            Point[] backup = Arrays.stream(piezaActual.getForma())
                    .map(Point::new)
                    .toArray(Point[]::new);
            piezaActual.rotarHorario();
            if (!tablero.puedeColocar(piezaActual, 0, 0)) {
                piezaActual.setForma(backup);
            }
            vista.repaint();
        }
    }

    // Pausar/reanudar juego
    public void togglePausa() {
        if (!gameOver) {
            pausado = !pausado;
            vista.repaint();
        }
    }

    // Reiniciar juego
    public void reiniciarJuego() {
        this.tablero = new Tablero();
        this.piezaActual = fabrica.nuevaPieza();
        this.siguientePieza = fabrica.nuevaPieza();
        this.puntuacion = 0;
        this.nivel = 1;
        this.lineasCompletadas = 0;
        this.gameOver = false;
        this.pausado = false;
        this.velocidad = 500;
        vista.repaint();
    }

    // getters para la vista
    public Tablero getTablero() {
        return tablero;
    }
    
    public Piezas getPiezaActual() {
        return piezaActual;
    }
    
    public Piezas getSiguientePieza() {
        return siguientePieza;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public int getLineasCompletadas() {
        return lineasCompletadas;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public boolean isPausado() {
        return pausado;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
}
