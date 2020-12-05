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
        return "CURS DE BATXILLER:\n\nNOM : "+nom+"\nCODI : "+codi+"\nESPECIALITAT : "+type.name()+"\n"; 
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
