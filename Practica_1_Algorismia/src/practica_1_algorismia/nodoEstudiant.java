/*
Clase nodoEstudiant
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class nodoEstudiant {
    private estudiant est;
    private nodoEstudiant seg;
    
    public nodoEstudiant(estudiant est){
        this.est = est;
    }
    public estudiant getEstudiant(){
        return this.est;
    }
    
    public void setSeg(nodoEstudiant seg){
        this.seg = seg;
    }
    
    public nodoEstudiant seguent(){
        return seg;
    }
}
