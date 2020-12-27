/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemacaballo;
//LO QUE FALTA ES HACER QUE EL JFRAME AUN NO CREADO QUE PREGUNTE PARAMETROS ABRA EL TABLERO CUANDO SE HAYAN ENCONTRADO 3 SOLUCIONES, O
//LAS QUE HAYAN <ES A 3. DESPUÉS SOLAMENTE ES MEJORAR CÓDIGO(CLASES Y LARGARIE)

import java.util.Scanner;

/**
 *
 * @author emanu
 */
public class problemaCaballo {

    int movX[] = new int[8];
    int movY[] = new int[8];
    int tablero[][];
    int caballo = 0;
    int numSoluciones = 0;
    int soluciones[][][];
    private boolean ready = false;

    public void start(int n, int xC, int yC) {
        formarMov();
        int size = n;
        int x = xC;
        int y = yC;
        tablero = new int[size][size];
        soluciones = new int[3][size * size][2];
        for (int i[] : tablero) {
            for (int j : i) {
                j = 0;
            }
        }
        tablero[x][y] = ++caballo;
        soluciones[numSoluciones][caballo - 1][0] = x;
        soluciones[numSoluciones][caballo - 1][1] = y;
        if (x >= 0 && x < size && y >= 0 && y < size) {
            moverCaballo(x, y);
        }
    }
    int contadorIteraciones = 0;

    private void moverCaballo(int x, int y) {
        int f, c;
        for (int i = 0; i < 8; i++) {
            int fc = siguienteMovimiento(x, y, i);
            if (fc >= 0) {
                f = fc / tablero.length;
                c = fc % tablero.length;
                tablero[f][c] = ++caballo;
                soluciones[numSoluciones][caballo - 1][0] = f;
                soluciones[numSoluciones][caballo - 1][1] = c;
                if (moverCaballoR(f, c) && numSoluciones < 3) {
                    System.out.println("Solución " + numSoluciones + ":\n");
                    //imprimir la solución
                    for (int j[] : tablero) {
                        for (int k : j) {
                            System.out.print(k + " ");
                        }
                        System.out.println("");
                    }
                    System.out.println("****************************************\n");

                    //poner 0 a todo menos a 1
                    for (int fila[] : tablero) {
                        for (int columna = 0; columna < fila.length; columna++) {
                            fila[columna] = 0;
                        }
                    }
                    caballo = 0;
                    tablero[x][y] = ++caballo;
                    soluciones[numSoluciones][caballo - 1][0] = x;
                    soluciones[numSoluciones][caballo - 1][1] = y;
                    numSoluciones++;
                    if (numSoluciones >= 3) {
                        break;
                    }
                }
            }
        }
        System.out.println("Se han encontrado un total de " + numSoluciones + " soluciones para el tablero.");
        if (numSoluciones > 0) {
            ready = true;
        }

    }

    public boolean getReady() {
        return this.ready;
    }

    private boolean moverCaballoR(int x, int y) {
        int f, c, fc;
        for (int i = 0; i < 8; i++) {
            fc = siguienteMovimiento(x, y, i);
            if (fc >= 0) {
                f = fc / tablero.length;
                c = fc % tablero.length;
                tablero[f][c] = ++caballo; // anotar
                soluciones[numSoluciones][caballo - 1][0] = f;
                soluciones[numSoluciones][caballo - 1][1] = c;
                if (caballo == tablero.length * tablero.length) { // si ya ha recorrido todo el tablero
                    return true;
                }
                if (moverCaballoR(f, c)) {
                    return true;
                }
                tablero[f][c] = 0; // se desanota el ultimo movimiento
                caballo--;
            }
        }
        return false;
    }

    private void printTablero(int tablero[][]) {
        for (int p[] : tablero) {
            for (int k : p) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    private void formarMov() {
        movX[0] = -2;
        movY[0] = 1;
        movX[1] = -1;
        movY[1] = 2;
        movX[2] = 1;
        movY[2] = 2;
        movX[3] = 2;
        movY[3] = 1;
        movX[4] = 2;
        movY[4] = -1;
        movX[5] = 1;
        movY[5] = -2;
        movX[6] = -1;
        movY[6] = -2;
        movX[7] = -2;
        movY[7] = -1;
    }

    private int siguienteMovimiento(int x, int y, int i) {
        int fila = x;
        int columna = y;
        fila += movX[i];
        columna += movY[i];
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length && tablero[fila][columna] == 0) {
            return fila * tablero.length + columna;
        } else {
            return -1;
        }
    }
    public void setFalse(){
        this.ready = false;
    }

}
