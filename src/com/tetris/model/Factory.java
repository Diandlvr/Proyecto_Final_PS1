package com.tetris.model;

public class Factory {
    public Piezas nuevaPieza() {
        // Aquí decides aleatoriamente un TipoPieza y creas la instancia:
        TipoPieza tipo = TipoPieza.values()[(int)(Math.random() * TipoPieza.values().length)];
        return new Piezas(tipo);
    }

}
