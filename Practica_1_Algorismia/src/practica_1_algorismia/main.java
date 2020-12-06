/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

/**
 *
 * @author emanu
 */
public class main {

    
    public static void main(String[] args) {
        //Quedan hacer métodos funcionales de la lista de estudiantes
            //Hechos, revisar el borrar que tengo dudas
        //Mirar si estan bien la lista de assginaturas ( arrayList )
            //Falta el método imprimir()
        //hacer que insertar de los estudiantes ordene
        //pensar de mirar ArrayList.sort();
        //Mirar si todos los métodos funcionales de todas las clases funcionan correctamente ( sobretodo de las listas )
        llistaEstudiants llista = new llistaEstudiants();
        llista.insertar(new nodoEstudiant(new estudiant("Carlos","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Carlos","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Jose","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Alba","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Bernat","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Dani","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Emanuel","435Z")));
        llista.insertar(new nodoEstudiant(new estudiant("Bartomeu","435Z")));
        //interfaceGrafica finestra = new interfaceGrafica();
    }
    
}
