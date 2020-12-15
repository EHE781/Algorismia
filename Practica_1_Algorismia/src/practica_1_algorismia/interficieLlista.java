/*
Clase interficieLlista
 */
package practica_1_algorismia;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public interface interficieLlista <object>{
    public void insertar(object elem);
    public object trobar(String nom);
    public void borrar(String nom);
    public String imprimir();
    public boolean buida();
}
