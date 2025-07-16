package com.tetris.model;
//con esta libreria se maneja las coordenadas relativas de los bloques de la pieza
import java.awt.Point;
// aca importamos para darle color a las piezas
import java.awt.Color;
//importamos el enum


public class Piezas {
    //Guarda el valor del enum
    private Piezas tipo;
    private Point[] forma; // describe la posición de cada bloque en un arreglo 4x4...
    private Point posicion; //Ubicacion de la pieza en el tablero
    private Color color;// de que color se pinta la pieza

    //aca guardamos el tipo, las coordenadas y el color de la pieza
    //ademas inicializa la posicion para que salga la pieza en la parte de arriba...
    public Piezas(Piezas tipo, Point[] forma, Color color) {
        this.tipo = tipo;
        this.forma = forma;
        this.color = color;
        this.posicion = new Point(3, 0);
    }
    //para poder ver los valores sin que se vean los campos
    public Piezas getTipo() { return tipo; }
    public Point[] getForma() { return forma; }
    public Point getPosicion() { return posicion; }
    public Color getColor() { return color; }
//utilizamos un metodo para desplazar las piezas para sumar dx o dy a su posicion actual
    public void mover(int dx, int dy) {
        posicion.translate(dx, dy);
    }
//rota 90° la figura hacia la derecha
    public void rotar() {
        for (Point p : forma) {
            int x = p.x, y = p.y;
            p.setLocation(y, -x);
        }
    }






}
