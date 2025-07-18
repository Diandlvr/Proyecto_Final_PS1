package com.tetris.model;
//Aqui se importa la calse celda para usarla en el tablero...
import com.tetris.model.Celdas;

import com.tetris.model.Celdas;
import java.awt.Point;
import com.tetris.model.Piezas;

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
    public boolean puedeColocar(Piezas pieza, int dx, int dy) {
        for (Point bloque : pieza.getForma()) {
            int x = pieza.getPosicion().x + bloque.x + dx;
            int y = pieza.getPosicion().y + bloque.y + dy;
            if (x < 0 || x >= COLUMNAS || y < 0 || y >= FILAS) {
                return false;
            }
            if (matriz[y][x].isOcupada()) {
                return false;
            }
        }
        return true;

    }
    // Pa limpiar las lineas y generarun espacio arriba del ultimo espacio ocupado
    public int limpiarLineas() {
        int lineas = 0;
        for (int y = FILAS - 1; y >= 0; y--) {
            boolean completa = true;
            for (int x = 0; x < COLUMNAS; x++) {
                if (!matriz[y][x].isOcupada()) {
                    completa = false;
                    break;
                }
            }
            if (completa) {
            lineas++;
                for (int yy = y; yy > 0; yy--) {
                    System.arraycopy(matriz[yy - 1], 0, matriz[yy], 0, COLUMNAS);
                }
                for (int x = 0; x < COLUMNAS; x++) {
                    matriz[0][x] = new Celdas();
                }
                y++; // revisar de nuevo esta fila
            }
        }
        return lineas;

    }
}



    // TODO: constantes FILAS, COLUMNAS
    // TODO: Celda[][] matriz
    // TODO: constructor que inicializa todas las celdas
    // TODO: métodos canPlace(...), clearLines(), getCelda(x,y), etc.


