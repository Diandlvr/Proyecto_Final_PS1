package com.tetris.model;
//Aqui se importa la calse celda para usarla en el tablero
import com.tetris.model.Celdas;
public class Tablero { //contiene la matriz en la que se almacenan las celdas
    // se configura el tamaño de las celdas
    public static final int FILAS = 20;
    public static final int COLUMNAS = 10;
    //aqui guardamos las dimensiones de las tablas de celdas
    private final Celdas[][] matriz = new Celdas[FILAS][COLUMNAS];
//recorremos todas las posiciones de las filas (y) desde 0, hasta filas-1, y en x desde 0 a columnas-1
    public Tablero() {
        for (int y = 0; y < FILAS; y++) {
            for (int x = 0; x < COLUMNAS; x++) {
                matriz[y][x] = new Celdas();
        }
    }
}
//sirve para acceder desde afuera a la celda en (x,y)
    public Celdas getCeldas(int x, int y) {
        return matriz[y][x];
    }
}

    // TODO: constantes FILAS, COLUMNAS
    // TODO: Celda[][] matriz
    // TODO: constructor que inicializa todas las celdas
    // TODO: métodos canPlace(...), clearLines(), getCelda(x,y), etc.


