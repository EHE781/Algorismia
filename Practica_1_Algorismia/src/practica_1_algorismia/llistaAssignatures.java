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

    public ArrayList<assignatura> llista;

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
    
    @Override
    public String imprimir() {
        String res = "";
        for (int i = 0; i < llista.size(); i++) {
            assignatura aux = llista.get(i);
            if (aux instanceof optativa) {
                optativa aux1 = (optativa) aux;
                res += aux1.imprimir();
            } else {
                obligatoria aux1 = (obligatoria) aux;
                res += aux1.imprimir();
            }
        }
        return res;
    }

    @Override
    public void borrar(String nom) {
        int pos = buscarPos(nom);
        if (((llista.size()) > pos) && (pos >= 0)) {
            llista.remove(pos);
        } else {
            System.err.println("No existe");
            //JOptionPane.showMessageDialog(null, "No existeix");
        }
    }

    @Override
    public void insertar(assignatura elemento) {
        if (trobar(elemento.nom) == null) {
            llista.add(elemento);
            Collections.sort(llista);
        } else {
            System.out.println("JA EXISTEIX");
        }
    }

}
