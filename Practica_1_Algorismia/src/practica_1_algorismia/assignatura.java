/*
Clase Assignatura
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public abstract class assignatura implements interficieVariables,Comparable<assignatura>{
    protected String nom;
    protected int codi;
    protected llistaEstudiants llista;
    
    public abstract String imprimir();
}
