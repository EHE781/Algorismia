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
public class llistaAssignatures extends ArrayList<assignatura> implements interficieLlista<assignatura>{
    //Exception ListaVacia,posicionInexistente;

    @Override
    public boolean buida() {
        return this.isEmpty();
    }

    @Override
    public assignatura trobar(String codi) {
        int pos = buscarPos(Integer.parseInt(codi));
        if (((this.size()) > pos) && (pos >= 0)) {
            return this.get(pos);
        }
        return null;
    }

    private int buscarPos(int codi) {
        for (int i = 0; i < this.size(); i++) {
            if (codi == this.get(i).codi) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public String imprimir() {
        String res = "";
        for (int i = 0; i < this.size(); i++) {
            assignatura aux = this.get(i);
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
    public void borrar(String codi) {
        int pos = buscarPos(Integer.parseInt(codi));
        if (((this.size()) > pos) && (pos >= 0)) {
            this.remove(pos);
        } else {
            System.err.println("No existe");
            //JOptionPane.showMessageDialog(null, "No existeix");
        }
    }

    @Override
    public void insertar(assignatura elemento) {
        if (trobar(String.valueOf(elemento.codi)) == null) {
            this.add(elemento);
            Collections.sort(this, new Comparator<assignatura>() {
                @Override
                public int compare(assignatura a1, assignatura a2) {
                    return a1.getNom().toLowerCase().compareTo(a2.getNom().toLowerCase());
                }
            });
        } else {
            System.out.println("JA EXISTEIX");
        }
    }

}
