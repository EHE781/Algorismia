/*
Clase optativa
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class optativa extends assignatura{
    public enum perfil {teoric,practica}
    private perfil per;
    
    public optativa(){
        nom = "";
        codi = 0;
        llista = new llistaEstudiants();
    }
    
    public llistaEstudiants getLlistaEstudiants(){
        return this.llista;
    }
    @Override
    public String imprimir() {
        return "ASSIGNATURA OPTATIVA :\n\nNOM : "+nom+"\nCODI : "+codi+"\nPERFIL : "+per.name()+"\n";
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
    public void posarPerfil(perfil a){
        per = a;
    }
}
