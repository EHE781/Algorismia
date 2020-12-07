/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

/**
 *
 * @author tomeu
 */
public abstract class assignatura implements interficieVariables{
    protected String nom;
    protected int codi;
    protected llistaEstudiants llista;
    
    public abstract String imprimir();
}
