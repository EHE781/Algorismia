/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class llistaAssignatures {
        Exception ListaVacia,posicionInexistente;
	private ArrayList <assignatura> llista;

	public llistaAssignatures() {
		llista = new ArrayList<assignatura>();
	}

        public void insertar(assignatura elemento) {
		llista.add(elemento);
	}

        public boolean vacia() {
            return llista.isEmpty();
        }

        public assignatura borrar(String nom) throws Exception {
                int pos = buscarPos(nom);
                if (((llista.size())>pos)&& (pos>=0)) return llista.remove(pos);
                else throw posicionInexistente;
	}

        public assignatura consultar(String nom) throws Exception {
                int pos = buscarPos(nom);
                if (((llista.size())>pos)&& (pos>=0)) return llista.get(pos);
                else throw posicionInexistente;
        }

        private int buscarPos(String nom) throws Exception {
            for (int i=0;i<llista.size();i++) {
                if (nom==llista.get(i).nom) return i;
            }
            return -1;
        }

       private void intercambiar(int pos1, int pos2) throws Exception {
           assignatura aux;
           if ((((llista.size())>pos1)&& (pos1>=0))&&(((llista.size())>pos2)&& (pos2>=0))) {
                    aux=llista.get(pos1);
                    llista.set(pos1,llista.get(pos2));
                    llista.set(pos2, aux);
                }
                else throw posicionInexistente;
       }

       public boolean buscar(assignatura elemento) {
           return llista.contains(elemento);
       }
       public int numElementos() {
           return llista.size();
       } 
}
