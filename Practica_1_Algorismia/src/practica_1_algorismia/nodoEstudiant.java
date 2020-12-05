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
public class nodoEstudiant {
    private estudiant est;
    private nodoEstudiant seg;
    
    public void nodoEstudiant(estudiant est){
        this.est = est;
    }
    
    public void setSeg(nodoEstudiant seg){
        this.seg = seg;
    }
    
    public nodoEstudiant seguent(){
        return seg;
    }
}
