/*
Clase obligatoria
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class obligatoria extends assignatura{
    private int credits;
    
    public obligatoria(){
        nom = "";
        codi = 0;
        llista = new llistaEstudiants();
    }
    
    public llistaEstudiants getLlistaEstudiants(){
        return this.llista;
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
    @Override
    public int compareTo(assignatura a){
        return nom.compareTo(a.nom);
    }
}
