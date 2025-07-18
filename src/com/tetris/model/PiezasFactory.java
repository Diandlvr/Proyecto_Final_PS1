package com.tetris.model;

import java.util.concurrent.ThreadLocalRandom;

public class PiezasFactory {
    public Piezas nuevaPieza() {
        // Aquí decides aleatoriamente un TipoPieza y creas la instancia:
        TipoPieza[] valores  = TipoPieza.values();
        int idx = ThreadLocalRandom.current().nextInt(valores.length);
        return new Piezas(valores[idx]);
    }

}
