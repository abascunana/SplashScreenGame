package com.example.splash.twofoureight;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
    public static final int TAB = 4; //Número de filas/columnas

    private static final String TAG = "Juego";//posición del programa
    private int[][] tablero;//el tablero en sí
    private Random r;

    private class Casilla {
        final int x;
        final int y;

        private Casilla(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Juego() { //Juego
        r = new Random();
        reiniciarTablero();
    }

    public void reiniciarTablero() {
        tablero = new int[TAB][TAB]; //creación de tablero
        conseguirSiguientePieza();
    }

    public boolean detectarLleno(int i, int j, int[][] casilla) {
        boolean lleno = false;
        if (tablero[i][j] != 0) {
            lleno = true;
        }
        return lleno;

    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean estaTableroLleno() {
        return getCasillaVacia() == null;
    }

    public void conseguirSiguientePieza() {
        //SELECCIONA CASILLA VACÍA RANDOM (FILA/COLUMNA) Y LE ASIGNA EL NÚMERO 2
        Casilla c = getCasillaVacia();
        if (tablero[c.x][c.y] == 0){
            tablero[c.x][c.y] = 2;
        }

    }

    public boolean moverHaciaDerecha() {
        boolean huboCambio = false;
        for (int x = 0; x < TAB; ++x) {
            while (true) {

                int cuadroVacio;

                for (cuadroVacio = TAB - 1; cuadroVacio >= 0; cuadroVacio--)
                    if (tablero[x][cuadroVacio] == 0)
                        break;
                    else if ((cuadroVacio - 1) >0){
                        if (tablero[x][cuadroVacio] == tablero[x][cuadroVacio - 1]) {
                            //Suma
                            tablero[x][cuadroVacio] = (tablero[x][cuadroVacio - 1] * 2);
                            tablero[x][cuadroVacio - 1] = 0;
                        }
                    }

                if (cuadroVacio < 0)
                    break;

                int cuadroOcupado;
                for (cuadroOcupado = cuadroVacio - 1; cuadroOcupado >= 0; cuadroOcupado--)
                    if (tablero[x][cuadroOcupado] > 0)
                        break;
                if (cuadroOcupado < 0)
                    break;

                tablero[x][cuadroVacio] = tablero[x][cuadroOcupado];
                tablero[x][cuadroOcupado] = 0;
                huboCambio = true;
            }
        }
        conseguirSiguientePieza();
        return huboCambio;
    }

    public boolean moverHaciaIzquierda() {


        boolean huboCambio = false;
        for (int x = 0; x < TAB; ++x) {
            while (true) {
                int cuadroVacio;
                for (cuadroVacio = 0; cuadroVacio < TAB; cuadroVacio++)
                    if (tablero[x][cuadroVacio] == 0) {
                        break;
                    } else if ((cuadroVacio + 1) <TAB){
                        if (tablero[x][cuadroVacio] == tablero[x][cuadroVacio + 1]) {
                            tablero[x][cuadroVacio] = (tablero[x][cuadroVacio + 1] * 2);
                            //Suma
                            tablero[x][cuadroVacio + 1] = 0;
                        }
                    }

                if (cuadroVacio >= TAB)
                    break;

                int cuadroOcupado;
                for (cuadroOcupado = cuadroVacio + 1; cuadroOcupado < TAB; cuadroOcupado++)
                    if (tablero[x][cuadroOcupado] > 0)
                        break;
                if (cuadroOcupado >= TAB)
                    break;
                tablero[x][cuadroVacio] = tablero[x][cuadroOcupado];
                tablero[x][cuadroOcupado] = 0;
                huboCambio = true;


            }
        }
        conseguirSiguientePieza();
        return huboCambio;
    }

    public boolean moverHaciaAbajo() {

        boolean huboCambio = false;
        for (int y = 0; y < TAB; ++y) {
            while (true) {
                int cuadroVacio;
                for (cuadroVacio = TAB - 1; cuadroVacio >= 0; cuadroVacio--)
                    if (tablero[cuadroVacio][y] == 0) {
                        break;
                    }else if ((cuadroVacio - 1) >0){
                        if (tablero[cuadroVacio][y] == tablero[cuadroVacio - 1][y]) {
                            tablero[cuadroVacio][y] = (tablero[cuadroVacio - 1][y] * 2);
                            tablero[cuadroVacio - 1][y] = 0;
                        }
                    }


                if (cuadroVacio < 0)
                    break;

                int cuadroOcupado;
                for (cuadroOcupado = cuadroVacio - 1; cuadroOcupado >= 0; cuadroOcupado--)
                    if (tablero[cuadroOcupado][y] > 0)
                        break;
                if (cuadroOcupado < 0)
                    break;

                tablero[cuadroVacio][y] = tablero[cuadroOcupado][y];
                tablero[cuadroOcupado][y] = 0;
                huboCambio = true;
            }
        }
        conseguirSiguientePieza();
        return huboCambio;
    }

    public boolean moverHaciaArriba() {


        boolean huboCambio = false;
        for (int y = 0; y < TAB; ++y) {
            while (true) {
                int cuadroVacio;
                for (cuadroVacio = 0; cuadroVacio < TAB; cuadroVacio++)
                    if (tablero[cuadroVacio][y] == 0)
                        break;
                else if ((cuadroVacio + 1) <TAB){
                        if (tablero[cuadroVacio][y] == tablero[cuadroVacio + 1][y]) {
                            tablero[cuadroVacio][y] = (tablero[cuadroVacio + 1][y] * 2);
                            tablero[cuadroVacio + 1][y] = 0;
                        }
                    }

                if (cuadroVacio >= TAB)
                    break;

                int cuadroOcupado;
                for (cuadroOcupado = cuadroVacio + 1; cuadroOcupado < TAB; cuadroOcupado++)
                    if (tablero[cuadroOcupado][y] > 0)
                        break;
                if (cuadroOcupado >= TAB)
                    break;

                tablero[cuadroVacio][y] = tablero[cuadroOcupado][y];
                tablero[cuadroOcupado][y] = 0;
                huboCambio = true;
            }
        }
        conseguirSiguientePieza();
        return huboCambio;

    }

    private Casilla getCasillaVacia() {
        //arraylists de casillas disponibles
        ArrayList<Casilla> casillasVacias = new ArrayList<>();

        //recurre las columnas y filas
        for (int x = 0; x < TAB; ++x) {
            for (int y = 0; y < TAB; ++y) {
                //si la casilla está vacía (todas son 0 por defecto) introducirlas en casillasVacias
                if (tablero[x][y] == 0) {
                    casillasVacias.add(new Casilla(x, y));
                }
            }
        }
//si las casillas vacías son mayores de 0
        if (casillasVacias.size() > 0) {
            //pilla una casilla vacía
            return casillasVacias.get(r.nextInt(casillasVacias.size()));
        } else {
            return null;
        }
    }

}