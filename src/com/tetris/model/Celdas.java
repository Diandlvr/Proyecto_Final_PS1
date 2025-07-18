package com.tetris.model;

import java.awt.Color;

public class Celdas {
    private boolean ocupada;
    private Color color;

    public Celdas() {
        this.ocupada = false;
        this.color   = Color.BLACK;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar(Color c) {
        this.ocupada = true;
        this.color   = c;
    }

    public void limpiar() {
        this.ocupada = false;
        this.color   = Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

// TODO: atributo booleano ocupado y Color color...
// TODO: métodos isOcupada(), ocupar(Color c), limpiar(), getColor()
}