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
public class optativa extends assignatura{
    enum perfil {teoric,practica}
    private perfil per;
    
    public optativa(){
        nom = "";
        codi = 0;
        llista = new llistaEstudiants();
    }
    
    @Override
    public String imprimir() {
        return "ASSIGNATURA OPTATIVA :\n\nNOM : "+nom+"\nCODI : "+codi+"\nPERFIL : "+per.name()+"\n";
    }

    @Override
    public void posarNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posarCodi(int codi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCodi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
