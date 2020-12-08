/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author emanu
 */
public class main {

    public static void main(String[] args) {
    //mirar los de mayusculas/minusculas para la ordenacion de listas
        //Hecho para llistaEstudiants, ordena sin errores
        //Hecho para llistaAssignatures, ordena sin errores

        /*
        La práctica consiste en dar de alta cursos, no asignaturas, por tanto al principio
        del programa se preguntará el plan de estudios, tipo para batxiller que asignaturas
        hay en primero y/o en segundo. Después de FP. Al completar las asignaturas de cada año 
        se pueden guardar en un fichero para más adelante, lo que implica un modelo de estudios
        guardable como objeto en un fichero (no muy dificil pero opcional, por lo que podemos 
        skipear eso). Después se inicia el programa, que tendrá botones con las funciones pedidas
        por el señor Fiel Gabriol, donde cada botón abre su correspondiente ventanita emergente para
        poder completar la operación(usar ventanas emergentes ahorra un 70% de trabajo con gráficos).
        */
        
        
        
        /*LO DEJO POR SI QUEREIS COMPROBARLO TAMBIEN
        llistaEstudiants st = new llistaEstudiants();
        llistaAssignatures l = new llistaAssignatures();
        String noms[] = {"Alba","Marcos","esteve","willy","bernat","Bernat","kola","Jola","Koala","Jusep"};
        for (int i = 0; i < 10; i++) {
            optativa b = new optativa();
            b.nom = noms[i];
            b.codi = i*5;
            b.posarPerfil(optativa.perfil.teoric);
            assignatura k = (assignatura) b;
            st.insertar(new estudiant(noms[i],Integer.toString(i)+"84854Y"));
            l.insertar(k);
        }
        System.out.println(st.imprimir());
        System.out.println("----------------------------------------------------");
        System.out.println(l.imprimir());
         */
    }

    static void matricularEstudiant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void matricularEstudiant(String dade, String dade0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
