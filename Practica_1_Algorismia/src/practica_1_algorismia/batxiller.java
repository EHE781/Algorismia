/*
Clase batxiller
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class batxiller extends curs{
    public enum especialitat {primer,segon}
    private especialitat type;
    
    public batxiller (){
        nom = "";
        codi = 0;
        llista = new llistaAssignatures();
    }

    @Override
    public String imprimir() {
        return "\nNOM : "+nom+"\nCODI : "+codi+"\nESPECIALITAT : "+type.name()+"\n"; 
    }

    @Override
    public void posarNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void posarCodi(int codi) {
        this.codi = codi;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getCodi() {
        return codi;
    }
    
    public void setEspecialitat(especialitat a){
        type = a;
    }
    
    public especialitat getEspecialitat(){
        return type;
    }
}
