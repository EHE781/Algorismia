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

    private static llistaCursos cursos = new llistaCursos();
    private static llistaEstudiants estudiants = new llistaEstudiants();

    public static void main(String[] args) {
        main start = new main();

        interfaceGrafica ig = new interfaceGrafica(start);

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
        //ALTA CURS Y ALTA ASSIGNATURA FUNCIONAN no diré perfecto pq faltan controles de exceptions,
        //pero si eres usuario no tocon funcionan.
    }

    void matricularEstudiant(String nom, String dni, String codiAssignatura) {
        curs aux = cursos.getPrimer();
        boolean matriculat = false;
        while (aux != null && !matriculat) {
            if (aux instanceof batxiller) {
                batxiller auxB = (batxiller) aux;
                for (int i = 0; i < auxB.getLlistaAssign().size(); i++) {
                    if (auxB.getLlistaAssign().trobar(codiAssignatura) != null) {
                        //mirar si existe estudiante
                        if (auxB.getLlistaAssign().trobar(codiAssignatura).llista.trobar(dni) != null) {
                            break;
                        } else {
                            //podemos iniciar el procedimiento de matriculacion del estudiante porque existe la assign
                            estudiant est = estudiants.trobar(dni);
                            auxB.getLlistaAssign().trobar(codiAssignatura).llista.insertar(est);
                            est.getLlistaAssignatures().insertar(auxB.getLlistaAssign().trobar(codiAssignatura));
                            matriculat = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                FP auxFP = (FP) aux;
                for (int i = 0; i < auxFP.getLlistaAssign().size(); i++) {
                    if (auxFP.getLlistaAssign().trobar(codiAssignatura) != null) {
                        if (auxFP.getLlistaAssign().trobar(codiAssignatura).llista.trobar(dni) != null) {
                            break;
                        } else {
                            //podemos iniciar el procedimiento de matriculacion del estudiante porque existe la assign
                            estudiant est = estudiants.trobar(dni);
                            auxFP.getLlistaAssign().trobar(codiAssignatura).llista.insertar(est);
                            est.getLlistaAssignatures().insertar(auxFP.getLlistaAssign().trobar(codiAssignatura));
                            matriculat = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            aux = aux.getSeg();
        }
        if (!matriculat) {
            JOptionPane.showMessageDialog(null, "Codi incorrecte o alumne ja matriculat!");
        }
    }
//cuando se da de alta una assignatura se mira(es un metodo de ayuda, se usa en dar de alta un curso)
    //si ya existe(ya dada de alta)
    //una asignatura SOLO se da de alta en conjunto con otras en un mismo curso, por lo que se crea
    //si aun no está creado
    //si no se da de alta en el curso que le es asignado, tipus devuelve:
    //0 si es bachiller, 1 si es FP
    //c_t val credits si es obligatoria i tipus si es optativa

    void altaAssignatura(int curs, String especialitat, String nom, String codi, String tipus, String c_t) {
        curs aux = cursos.getPrimer();
        boolean cursTrobat = false;
        if (curs == 0) {
            while (aux != null && !cursTrobat) {
                if (aux instanceof batxiller) {
                    batxiller auxB = (batxiller) aux;
                    if (auxB.getEspecialitat() == batxiller.especialitat.valueOf(especialitat)) {
                        cursTrobat = true;
                        if (tipus.equals("obligatoria")) {
                            obligatoria assignatura = new obligatoria();
                            assignatura.posarNom(nom);
                            assignatura.posarCodi(Integer.parseInt(codi));
                            assignatura.setCredits(Integer.parseInt(c_t));
                            auxB.getLlistaAssign().insertar(assignatura);
                        } else {
                            optativa assignatura = new optativa();
                            assignatura.posarNom(nom);
                            assignatura.posarCodi(Integer.parseInt(codi));
                            assignatura.posarPerfil(optativa.perfil.valueOf(c_t));
                            auxB.getLlistaAssign().insertar(assignatura);
                        }
                    }
                }
                aux = aux.getSeg();
            }
        } else {
            while (aux != null && !cursTrobat) {
                if (aux instanceof FP) {
                    FP auxFp = (FP) aux;
                    if (auxFp.getEspecialitat() == FP.especialitat.valueOf(especialitat)) {
                        cursTrobat = true;
                        if (tipus.equals("obligatoria")) {
                            obligatoria assignatura = new obligatoria();
                            assignatura.posarNom(nom);
                            assignatura.posarCodi(Integer.parseInt(codi));
                            assignatura.setCredits(Integer.parseInt(c_t));
                            auxFp.getLlistaAssign().insertar(assignatura);
                        } else {
                            optativa assignatura = new optativa();
                            assignatura.posarNom(nom);
                            assignatura.posarCodi(Integer.parseInt(codi));
                            assignatura.posarPerfil(optativa.perfil.valueOf(c_t));
                            auxFp.getLlistaAssign().insertar(assignatura);
                        }
                    }
                }
                aux = aux.getSeg();
            }
        }
    }

    void baixaAssignatura(int i
    ) {

    }
//falta poner un sistema de codi, no 2020 
    void altaCurs(String tipus, String especialitat) {
        if (tipus.equals("batxiller")) {
            batxiller b = new batxiller();
            b.setEspecialitat(batxiller.especialitat.valueOf(especialitat));
            b.posarNom("Curs de " + especialitat + " de batxiller");
            b.posarCodi(Integer.parseInt("2020"));
            cursos.insertar((curs) b);
        } else {
            FP fp = new FP();
            fp.setEspecialitat(FP.especialitat.valueOf(especialitat));
            fp.posarNom("Curs de " + especialitat + " de FP");
            fp.posarCodi(Integer.parseInt("2020"));
            cursos.insertar((curs) fp);
        }
    }

}
