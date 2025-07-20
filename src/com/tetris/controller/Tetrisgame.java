package com.tetris.controller;

import com.tetris.model.Tablero;
import com.tetris.model.Piezas;
import com.tetris.model.PiezasFactory;
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
    private boolean gameOver;


    public Tetrisgame() {
        this.tablero     = new Tablero();
        this.fabrica     = new PiezasFactory();
        this.piezaActual = fabrica.nuevaPieza();
        this.siguientePieza = fabrica.nuevaPieza();
        this.puntuacion  = 0;
        this.gameOver = false;

    }

    // baja la pieza, si no cabe, la bloquea
    public void pasoDelJuego() {
        if (gameOver) return;
        if  (!tablero.puedeColocar(piezaActual,0,0)){
            gameOver = true;
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
        puntuacion += lineas * 100;
        piezaActual = siguientePieza;
        siguientePieza = fabrica.nuevaPieza();
        vista.repaint();
    }

    // movimientos laterales
    public void moverIzquierda() {
        if (tablero.puedeColocar(piezaActual, -1, 0)) {
            piezaActual.mover(-1, 0);
            vista.repaint();
        }
    }

    public void moverDerecha() {
        if (tablero.puedeColocar(piezaActual, 1, 0)) {
            piezaActual.mover(1, 0);
            vista.repaint();
        }
    }

    // "soft drop"
    public void bajarFila() {
        if (!gameOver) {


            if (tablero.puedeColocar(piezaActual, 0, 1)) {
                piezaActual.mover(0, 1);
            } else {
                bloquearPieza();
            }
            vista.repaint();
        }
    }

    // rotación con rollback si choca
    public void rotarPieza() {
        if (!gameOver) {


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

    // getters para la vista
    public Tablero getTablero()      {
        return tablero; }
    public Piezas  getPiezaActual()  {
        return piezaActual; }
    public Piezas getSiguientePieza() {
        return siguientePieza;
    }
    public int     getPuntuacion()   {
        return puntuacion; }
    public boolean isGameOver()  {
        return gameOver;
    }
}
