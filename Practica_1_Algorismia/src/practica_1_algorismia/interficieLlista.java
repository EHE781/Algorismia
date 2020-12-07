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
public interface interficieLlista <object>{
    public void insertar(object elem);
    public object trobar(String nom);
    public void borrar(String nom);
    public String imprimir();
    public boolean buida();
}
