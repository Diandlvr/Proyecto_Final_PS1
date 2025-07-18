package com.tetris.model;
//formas de las piezas...
import java.awt.Point;
import java.awt.Color;
//defino los datos del enum
    public enum TipoPieza {
        I(new Point[]{ new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0) }, Color.CYAN),
        O(new Point[]{ new Point(0, 0),  new Point(1, 0),  new Point(0, 1),  new Point(1, 1)  }, Color.YELLOW),
        T(new Point[]{ new Point(-1, 0), new Point(0, 0),  new Point(1, 0),  new Point(0, 1)  }, Color.MAGENTA),
        S(new Point[]{ new Point(-1, 1), new Point(0, 1),  new Point(0, 0),  new Point(1, 0)  }, Color.GREEN),
        Z(new Point[]{ new Point(-1, 0), new Point(0, 0),  new Point(0, 1),  new Point(1, 1)  }, Color.RED),
        J(new Point[]{ new Point(-1, 0), new Point(0, 0),  new Point(1, 0),  new Point(1, 1)  }, Color.BLUE),
        L(new Point[]{ new Point(-1, 0), new Point(0, 0),  new Point(1, 0),  new Point(-1,1)  }, Color.ORANGE);
        private final Point[] formaInicial;
        private final Color   color;

        //para que cada valor del enum guarde sus datos
        TipoPieza(Point[] formaInicial, Color color) {
            this.formaInicial = formaInicial;
            this.color        = color;
        }

        // para tener una copia de la forma inicial sin cambiar la nueva
        public Point[] getFormaInicial() {
            Point[] copia = new Point[formaInicial.length];
            for (int i = 0; i < formaInicial.length; i++) {
                copia[i] = new Point(formaInicial[i]);
            }
            return copia;
        }

        //para pintar las piezas
        public Color getColor() {
            return color;
        }
    }
