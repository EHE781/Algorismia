/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import javax.swing.JOptionPane;

/**
 *
 * @author tomeu
 */
public class llistaEstudiants implements interficieLlista<estudiant> {

    private nodoEstudiant primer;

    public llistaEstudiants() {
        primer = null;
    }
    public nodoEstudiant getPrimer(){
        return this.primer;
    }
    @Override
    public void borrar(String nom) {
        nodoEstudiant aux = primer;
        nodoEstudiant paux = null;
        //Cas llista buida
        if (primer == null) {
            JOptionPane.showMessageDialog(null, "No existeix");
        } else {
            if (primer.getEstudiant().getDni().equals(nom)) {
                primer = primer.seguent();
            } else {
                //Cercar en tota la llista fins trobar nom o arribar al final
                while (aux.seguent() != null && !aux.getEstudiant().getDni().equals(nom)) {
                    paux = aux;
                    aux = aux.seguent();
                }
                //Si es l'últim element no ha pogut trobar nom, pertant...
                if (!aux.getEstudiant().getDni().equals(nom)) {
                    JOptionPane.showMessageDialog(null, "No existeix");
                    //Si no entra al if es perque ha trobat el nom
                } else {
                    paux.setSeg(aux.seguent());
                }
            }
        }
    }

    @Override
    public String imprimir() {
        nodoEstudiant aux = primer;
        String impr = "";
        while (aux != null) {
            estudiant aux1 = aux.getEstudiant();
            impr += aux1.imprimirEstudiant();
            impr += "\n";
            aux = aux.seguent();
        }
        return impr;
    }

    @Override
    public void insertar(estudiant est) {
        nodoEstudiant elem = new nodoEstudiant(est);
        if (trobar(elem.getEstudiant().getDni()) == null) {
            boolean ficat = false;
            if (primer == null) {
                primer = elem;
            } else {
                nodoEstudiant aux = primer;
                nodoEstudiant ant = primer;
                while (aux.seguent() != null) {
                    if ((elem.getEstudiant().getNom().toLowerCase().compareTo(aux.getEstudiant().getNom().toLowerCase())) < 0) {
                        //Cas primer element llista
                        if (ant == aux) {
                            primer = elem;
                            elem.setSeg(ant);
                            ficat = true;
                            break;
                        } else {
                            ant.setSeg(elem);
                            elem.setSeg(aux);
                            ficat = true;
                            break;
                        }
                    }
                    ant = aux;
                    aux = aux.seguent();
                }
                //Tant com si son iguals o el nou element es superior, el ficarem després de aux:
                //If innecesari, més posat per el break;
                if ((elem.getEstudiant().getNom().toLowerCase().compareTo(aux.getEstudiant().getNom().toLowerCase())) < 0 && !ficat) {
                    ant.setSeg(elem);
                    elem.setSeg(aux);
                }
                if ((elem.getEstudiant().getNom().toLowerCase().compareTo(aux.getEstudiant().getNom().toLowerCase())) >= 0) {
                    aux.setSeg(elem);
                }
            }
        }else{
            System.out.println("JA EXISTEIX");
        }

    }

    @Override
    public estudiant trobar(String dni) {
        nodoEstudiant aux = primer;
        //Cas llista buida
        if (primer == null) {
            return null;
        } else {
            //Cercar en tota la llista fins trobar nom o arribar al final
            while (aux.seguent() != null && !aux.getEstudiant().getDni().equals(dni)) {
                aux = aux.seguent();
            }
            //Si es l'últim element i no conté el nom cercat
            if (aux.seguent() == null && !aux.getEstudiant().getDni().equals(dni)) {
                //JOptionPane.showMessageDialog(null, "No existeix");
                return null;
                //Si no entra al if es perque ha trobat el nom
            } else {
                return aux.getEstudiant();
            }
        }
    }

    @Override
    public boolean buida() {
        return primer == null;
    }
}
