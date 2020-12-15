/*
Clase curs
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public abstract class curs implements interficieVariables{
    protected String nom;
    protected int codi;
    protected curs seg;
    protected llistaAssignatures llista;
    
    public curs getSeg(){
        return this.seg;
    }
    public int getCodi(){
        return this.codi;
    }
    public llistaAssignatures getLlistaAssign(){
        return this.llista;
    }
    public abstract String imprimir();
}
