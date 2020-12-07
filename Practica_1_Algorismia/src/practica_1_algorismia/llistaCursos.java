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
public class llistaCursos implements interficieLlista<curs>{
    private curs primer;
    
    public llistaCursos(){
        primer = null;
    }

    @Override
    public void insertar(curs elem) {
        curs aux;
        aux = primer;
        primer = elem;
        elem.seg = aux;
    }

    @Override
    public curs trobar(String nom) {
        curs aux = primer;
        while(!aux.nom.equals(nom) && aux.seg != null){
            aux = aux.seg;
        }
        if(aux.nom.equals(nom)) return aux;
        else return null;
    }

    @Override
    public void borrar(String nom) {
        curs aux,paux;
        paux = null;
        aux = primer;
        if(primer == null){
            System.out.println("Llista buida");
        }
        else{
            if(primer.nom.equals(nom)){
                primer = primer.seg;
            }
            else{
                while(!aux.nom.equals(nom) && aux.seg != null){
                    paux = aux;
                    aux = aux.seg;
                }
                    if(aux.nom.equals(nom)){
                    paux.seg = aux.seg;
                }
            } 
        }     
    }

    @Override
    public String imprimir() {
        String res = "";
        curs aux = primer;
        while(aux != null){
            if(aux instanceof FP){
                FP aux1 = (FP) aux;
                res += aux1.imprimir();
                res += "\n";
            }
            else{
                batxiller aux2 = (batxiller) aux;
                res += aux2.imprimir();
                res += "\n";
            }
            aux = aux.seg;
        }
        return res;
    }

    @Override
    public boolean buida() {
        return primer == null;
    }

   
    
}
