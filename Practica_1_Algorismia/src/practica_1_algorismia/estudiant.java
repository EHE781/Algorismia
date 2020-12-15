/*
Clase estudiant
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class estudiant {

    private String nom;
    private String dni;
    private llistaAssignatures llista;

    public estudiant(String nom, String dni) {
        this.nom = nom;
        this.dni = dni;
        llista = new llistaAssignatures();
    }

    public String getNom() {
        return this.nom;
    }

    public String getDni() {
        return this.dni;
    }

    public String imprimirEstudiant() {
        return "Estudiant:\nNOM : " + nom + "\nDNI : " + dni + "\n";
    }
    
    public llistaAssignatures getLlistaAssignatures(){
        return llista;
    }

    void setNom(String willyrex) {
        this.nom = willyrex;
    }

}
