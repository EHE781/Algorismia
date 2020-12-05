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
public class obligatoria extends assignatura{
    private int credits;
    
    public obligatoria(){
        nom = "";
        codi = 0;
        llista = new llistaEstudiants();
    }
    
    public void setCredits(int credits){
        this.credits = credits;
    }
    
    @Override
    public String imprimir() {
        return "ASSIGNATURA OBLIGATORIA:\n\nNOM : "+nom+"\nCODI : "+codi+"\nCREDITS : "+credits+"\n";
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
    
}
