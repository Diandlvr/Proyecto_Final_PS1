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
    private PiezasFactory fabrica;
    private GamePanel vista;
    private int puntuacion;

    public Tetrisgame() {
        this.tablero     = new Tablero();
        this.fabrica     = new PiezasFactory();
        this.piezaActual = fabrica.nuevaPieza();
        this.puntuacion  = 0;
    }

    // baja la pieza, si no cabe, la bloquea
    public void pasoDelJuego() {
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
        piezaActual = fabrica.nuevaPieza();
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
        if (tablero.puedeColocar(piezaActual, 0, 1)) {
            piezaActual.mover(0, 1);
        } else {
            bloquearPieza();
        }
        vista.repaint();
    }

    // rotación con rollback si choca
    public void rotarPieza() {
        Point[] backup = Arrays.stream(piezaActual.getForma())
                .map(Point::new)
                .toArray(Point[]::new);
        piezaActual.rotarHorario();
        if (!tablero.puedeColocar(piezaActual, 0, 0)) {
            piezaActual.setForma(backup);
        }
        vista.repaint();
    }

    // getters para la vista
    public Tablero getTablero()      {
        return tablero; }
    public Piezas  getPiezaActual()  {
        return piezaActual; }
    public int     getPuntuacion()   {
        return puntuacion; }
}
