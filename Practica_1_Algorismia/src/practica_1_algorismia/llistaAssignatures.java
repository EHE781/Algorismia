/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class llistaAssignatures implements interficieLlista<assignatura> {
    //Exception ListaVacia,posicionInexistente;

    private ArrayList<assignatura> llista;

    public llistaAssignatures() {
        llista = new ArrayList<>();
    }

    @Override
    public boolean buida() {
        return llista.isEmpty();
    }
    @Override
    public assignatura trobar(String nom) {
        int pos = buscarPos(nom);
        if (((llista.size()) > pos) && (pos >= 0)) {
            return llista.get(pos);
        } else {
            System.err.println("No existe");
            JOptionPane.showMessageDialog(null, "No existeix");
        }
        return null;
    }

    private int buscarPos(String nom) {
        for (int i = 0; i < llista.size(); i++) {
            if (nom == llista.get(i).nom) {
                return i;
            }
        }
        return -1;
    }

//    private void intercambiar(int pos1, int pos2) {
//        assignatura aux;
//        if ((((llista.size()) > pos1) && (pos1 >= 0)) && (((llista.size()) > pos2) && (pos2 >= 0))) {
//            aux = llista.get(pos1);
//            llista.set(pos1, llista.get(pos2));
//            llista.set(pos2, aux);
//        } else {
//            System.err.println("No existe");
//            JOptionPane.showMessageDialog(null, "No existeix");
//        }
//    }

//    public boolean buscar(assignatura elemento) {
//        return llista.contains(elemento);
//    }

//    public int numElementos() {
//        return llista.size();
//    }

    @Override
    public String imprimir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(String nom) {
        int pos = buscarPos(nom);
        if (((llista.size()) > pos) && (pos >= 0)) {
            llista.remove(pos);
        } else {
            System.err.println("No existe");
            JOptionPane.showMessageDialog(null, "No existeix");
        }
    }

    @Override
    public void insertar(assignatura elemento) {
        llista.add(elemento);
    }
}
