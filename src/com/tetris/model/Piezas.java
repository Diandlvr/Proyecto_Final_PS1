package com.tetris.model;

import java.awt.Point;
import java.awt.Color;

public class Piezas {
    // Guarda el valor del enum
    private TipoPieza tipo;
    // describe la posición de cada bloque en un arreglo 4x4...
    private Point[] forma;
    // Ubicación de la pieza en el tablero
    private Point posicion;
    // de qué color se pintan las piezas
    private Color color;

    // aquí guardamos el tipo, las coordenadas y el color de la pieza
    // además inicializa la posición para que salga la pieza en la parte de arriba...
    public Piezas(TipoPieza tipo) {
        this.tipo     = tipo;
        this.forma    = tipo.getFormaInicial();
        this.color    = tipo.getColor();
        this.posicion = new Point(3, 0);
    }

    // para poder ver los valores sin que se vean los campos
    public TipoPieza getTipo()     { return tipo; }
    public Point[]    getForma()    { return forma; }
    public Point      getPosicion() { return new Point(posicion); }
    public Color      getColor()    { return color; }

    // utilizamos un método para desplazar las piezas sumando dx o dy a su posición actual
    public void mover(int dx, int dy) {
        posicion.translate(dx, dy);
    }

    // rota 90° la figura hacia la derecha
    public void rotarHorario() {
        for (Point p : forma) {
            int x = p.x, y = p.y;
            p.setLocation(y, -x);
        }
    }

    // restaura una forma previa (rollback en rotación)
    public void setForma(Point[] nuevaForma) {
        this.forma = nuevaForma;
    }
}
