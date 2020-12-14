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

    public llistaCursos getCursos() {
        return this.cursos;
    }

    public llistaEstudiants getEstudiants() {
        return this.estudiants;
    }

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
        //MIRAR AHORRAR CODIGO 0, 1
    }

    void matricularEstudiant(String nom, String dni, String codiAssignatura) {
        curs aux = cursos.getPrimer();
        boolean matriculat = false;
        while (aux != null && !matriculat) {
            if (aux.getLlistaAssign().trobar(codiAssignatura) != null) {
                //mirar si existe estudiante
                if (aux.getLlistaAssign().trobar(codiAssignatura).llista.trobar(dni) == null) {
                    //podemos iniciar el procedimiento de matriculacion del estudiante porque existe la assign
                    estudiant est = estudiants.trobar(dni);
                    //Si no es troba a la llista de estudiants (primera matriculació)
                    if (est == null) {
                        est = new estudiant(nom, dni);
                        estudiants.insertar(est);
                    } else {
                        //Si s'ha trobat l'estudiant i DNIs iguals
                        if (!est.getNom().equals(nom)) {
                            break;
                        }
                    }
                    aux.getLlistaAssign().trobar(codiAssignatura).llista.insertar(est);
                    est.getLlistaAssignatures().insertar(aux.getLlistaAssign().trobar(codiAssignatura));
                    matriculat = true;
                }
            }
            aux = aux.getSeg();
        }
        if (!matriculat) {
            JOptionPane.showMessageDialog(null, "Codi incorrecte o alumne ja matriculat!");
        }
    }

//    void matricularEstudiant(String nom, String dni, String codiAssignatura) {
//        curs aux = cursos.getPrimer();
//        boolean matriculat = false;
//        while (aux != null && !matriculat) {
//            if (aux instanceof batxiller) {
//                batxiller auxB = (batxiller) aux;
//                if (auxB.getLlistaAssign().trobar(codiAssignatura) != null) {
//                    //mirar si existe estudiante
//                    if (auxB.getLlistaAssign().trobar(codiAssignatura).llista.trobar(dni) == null) {
//                        //podemos iniciar el procedimiento de matriculacion del estudiante porque existe la assign
//                        estudiant est = estudiants.trobar(dni);
//                        //Si no es troba a la llista de estudiants (primera matriculació)
//                        if (est == null) {
//                            est = new estudiant(nom, dni);
//                            estudiants.insertar(est);
//                        } else {
//                            //Si s'ha trobat l'estudiant i DNIs iguals
//                            if (!est.getNom().equals(nom)) {
//                                break;
//                            }
//                        }
//                        auxB.getLlistaAssign().trobar(codiAssignatura).llista.insertar(est);
//                        est.getLlistaAssignatures().insertar(auxB.getLlistaAssign().trobar(codiAssignatura));
//                        matriculat = true;
//                    }
//                }
//            } else {
//                FP auxFP = (FP) aux;
//                if (auxFP.getLlistaAssign().trobar(codiAssignatura) != null) {
//                    if (auxFP.getLlistaAssign().trobar(codiAssignatura).llista.trobar(dni) == null) {
//                        //podemos iniciar el procedimiento de matriculacion del estudiante porque existe la assign
//                        estudiant est = estudiants.trobar(dni);
//                        if (est == null) {
//                            est = new estudiant(nom, dni);
//                            estudiants.insertar(est);
//                        } else {
//                            //Si s'ha trobat l'estudiant i nom no coincideix
//                            if (!est.getNom().equals(nom)) {
//                                break;
//                            }
//                        }
//                        auxFP.getLlistaAssign().trobar(codiAssignatura).llista.insertar(est);
//                        est.getLlistaAssignatures().insertar(auxFP.getLlistaAssign().trobar(codiAssignatura));
//                        matriculat = true;
//                    }
//                }
//            }
//            aux = aux.getSeg();
//        }
//        if (!matriculat) {
//            JOptionPane.showMessageDialog(null, "Codi incorrecte o alumne ja matriculat!");
//        }
//    }
//cuando se da de alta una assignatura se mira(es un metodo de ayuda, se usa en dar de alta un curso)
    //si ya existe(ya dada de alta)
    //una asignatura SOLO se da de alta en conjunto con otras en un mismo curso, por lo que se crea
    //si aun no está creado
    //si no se da de alta en el curso que le es asignado, tipus devuelve:
    //0 si es bachiller, 1 si es FP
    //c_t val credits si es obligatoria i tipus si es optativa                  
    public boolean altaAssignatura(String codiCurs, String nom, String codi, String tipus, String c_t) {
        curs aux = cursos.trobar(codiCurs);
        if (aux != null) {
            if (!trobarAssignatura(codi)) {
                if (tipus.equals("obligatoria")) {
                    obligatoria assignatura = new obligatoria();
                    assignatura.posarNom(nom);
                    assignatura.posarCodi(Integer.parseInt(codi));
                    assignatura.setCredits(Integer.parseInt(c_t));
                    aux.getLlistaAssign().insertar(assignatura);
                } else {
                    optativa assignatura = new optativa();
                    assignatura.posarNom(nom);
                    assignatura.posarCodi(Integer.parseInt(codi));
                    assignatura.posarPerfil(optativa.perfil.valueOf(c_t));
                    aux.getLlistaAssign().insertar(assignatura);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ja existeix una assginatura amb aquest codi!");
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
//    public boolean altaAssignatura(int curs, String especialitat, int codiCurs, String nom, String codi, String tipus, String c_t) {
//        curs aux = cursos.getPrimer();
//        boolean cursTrobat = false;
//        if (curs == 0) {
//            while (aux != null && !cursTrobat) {
//                if (aux instanceof batxiller) {
//                    batxiller auxB = (batxiller) aux;
//                    if (auxB.getEspecialitat() == batxiller.especialitat.valueOf(especialitat) && auxB.getCodi() == codiCurs) {
//                        cursTrobat = true;
//                        if (!trobarAssignatura(codi)) {
//                            if (tipus.equals("obligatoria")) {
//                                obligatoria assignatura = new obligatoria();
//                                assignatura.posarNom(nom);
//                                assignatura.posarCodi(Integer.parseInt(codi));
//                                assignatura.setCredits(Integer.parseInt(c_t));
//                                auxB.getLlistaAssign().insertar(assignatura);
//                            } else {
//                                optativa assignatura = new optativa();
//                                assignatura.posarNom(nom);
//                                assignatura.posarCodi(Integer.parseInt(codi));
//                                assignatura.posarPerfil(optativa.perfil.valueOf(c_t));
//                                auxB.getLlistaAssign().insertar(assignatura);
//                            }
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Ja existeix una assginatura amb aquest codi!");
//                            return true;
//                        }
//                    }
//                }
//                aux = aux.getSeg();
//            }
//        } else if (curs == 1) {
//            while (aux != null && !cursTrobat) {
//                if (aux instanceof FP) {
//                    FP auxFp = (FP) aux;
//                    if (auxFp.getEspecialitat() == FP.especialitat.valueOf(especialitat) && auxFp.getCodi() == codiCurs) {
//                        cursTrobat = true;
//                        if (!trobarAssignatura(codi)) {
//                            if (tipus.equals("obligatoria")) {
//                                obligatoria assignatura = new obligatoria();
//                                assignatura.posarNom(nom);
//                                assignatura.posarCodi(Integer.parseInt(codi));
//                                assignatura.setCredits(Integer.parseInt(c_t));
//                                auxFp.getLlistaAssign().insertar(assignatura);
//                            } else {
//                                optativa assignatura = new optativa();
//                                assignatura.posarNom(nom);
//                                assignatura.posarCodi(Integer.parseInt(codi));
//                                assignatura.posarPerfil(optativa.perfil.valueOf(c_t));
//                                auxFp.getLlistaAssign().insertar(assignatura);
//                            }
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Ja existeix una assginatura amb aquest codi!");
//                            return true;
//                        }
//                    }
//                }
//                aux = aux.getSeg();
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "No es pot crear la assignatura!");
//            return true;
//        }
//        return false;
//    }
//posar codiCurs

    void baixaAssignatura(int codiCurs, int codi) {
        boolean cursTrobat = false;
        assignatura auxPrint = null;
        if (cursos.trobar(String.valueOf(codiCurs)) != null) {
            curs aux = cursos.trobar(String.valueOf(codiCurs));
            llistaAssignatures iterador = aux.getLlistaAssign();
            if (iterador.trobar(String.valueOf(codi)) != null) {
                cursTrobat = true;
                auxPrint = iterador.trobar(String.valueOf(codi));
                nodoEstudiant e = auxPrint.llista.getPrimer();
                while (e != null) {
                    e.getEstudiant().getLlistaAssignatures().borrar(String.valueOf(codi));
                    if (e.getEstudiant().getLlistaAssignatures().buida()) {
                        estudiants.borrar(e.getEstudiant().getDni());
                    }
                    e = e.seguent();
                }
                iterador.borrar(String.valueOf(codi));
                if (iterador.buida()) {
                    baixaCurs(aux.getCodi());
                    JOptionPane.showMessageDialog(null, "El curs es donarà de baixa perquè no queden assignatures!");
                }
            }
        }
        if (cursTrobat) {
            if (auxPrint instanceof obligatoria) {
                obligatoria o = (obligatoria) auxPrint;
                JOptionPane.showMessageDialog(null, "Donada de baixa correctament!, assignatura: \n\n" + o.imprimir());
            } else {
                optativa o = (optativa) auxPrint;
                JOptionPane.showMessageDialog(null, "Donada de baixa correctament!, assignatura: \n\n" + o.imprimir());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No s'ha pogut donar de baixa, no existeix!");
        }

    }

    public boolean altaCurs(String tipus, String especialitat, int codi) {
        boolean trobat = true;
        if (tipus.equals("batxiller")) {
            if (cursos.trobar(String.valueOf(codi)) == null) {
                batxiller b = new batxiller();
                b.setEspecialitat(batxiller.especialitat.valueOf(especialitat));
                b.posarNom("Curs de " + especialitat + " de batxiller");
                b.posarCodi(codi);
                cursos.insertar(b);
            } else {
                trobat = false;
            }
        } else {
            if (cursos.trobar(String.valueOf(codi)) == null) {
                FP fp = new FP();
                fp.setEspecialitat(FP.especialitat.valueOf(especialitat));
                fp.posarNom("Curs de " + especialitat + " de FP");
                fp.posarCodi(codi);
                cursos.insertar(fp);
            } else {
                trobat = false;
            }
        }
        if (!trobat) {
            JOptionPane.showMessageDialog(null, "El curs ja existeix!");
            return false;
        } else {
            return true;
        }
    }

//    //Tipus = 0 si batxiller, 1 si FP
//    void baixaCurs(int tipus, int codiCurs) {
//        curs aux = cursos.getPrimer();
//        //Cas batxiller
//        while (aux != null) {
//            if (tipus == 0) {
//                if (aux instanceof batxiller) {
//                    batxiller b = (batxiller) aux;
//                    if (b.getCodi() == codiCurs) {
//                        //borrar curs, assignatures, desmatricular estudents, ver si ya no estan borrarlos etc
//                        int baixes[] = new int[b.getLlistaAssign().size()];
//                        for (assignatura i : b.getLlistaAssign()) {
//                            baixes[b.getLlistaAssign().indexOf(i)] = i.getCodi();
//                        }
//                        for (int i = 0; i < baixes.length; i++) {
//                            baixaAssignatura(codiCurs, baixes[i]);
//                        }
//                        b.getLlistaAssign().clear();
//                        //Despues de haber borrado las asignaturas del curso borramos curso
//                        cursos.borrar(String.valueOf(b.getCodi()));
//                        break;
//                    }
//                }
//            } else {
//                if (aux instanceof FP) {
//                    FP fp = (FP) aux;
//                    if (fp.getCodi() == codiCurs) {
//                        //borrar curs, assignatures, desmatricular estudents, ver si ya no estan borrarlos etc
//                        int baixes[] = new int[fp.getLlistaAssign().size()];
//                        for (assignatura i : fp.getLlistaAssign()) {
//                            baixes[fp.getLlistaAssign().indexOf(i)] = i.getCodi();
//                        }
//                        for (int i = 0; i < baixes.length; i++) {
//                            baixaAssignatura(codiCurs, baixes[i]);
//                        }
//                        fp.getLlistaAssign().clear();
//                        //Despues de haber borrado las asignaturas del curso borramos curso
//                        cursos.borrar(String.valueOf(fp.getCodi()));
//                        break;
//                    }
//                }
//            }
//            aux = aux.getSeg();
//        }
//        if (aux == null) {
//            JOptionPane.showMessageDialog(null, "No s'ha trobat el curs, no existeix!");
//        }
//    }

    void baixaCurs(int codiCurs) {
        curs aux = cursos.trobar(String.valueOf(codiCurs));
        if (aux != null) {
            int baixes[] = new int[aux.getLlistaAssign().size()];
            for (assignatura i : aux.getLlistaAssign()) {
                baixes[aux.getLlistaAssign().indexOf(i)] = i.getCodi();
            }
            for (int i = 0; i < baixes.length; i++) {
                baixaAssignatura(codiCurs, baixes[i]);
            }
            aux.getLlistaAssign().clear();
            //Despues de haber borrado las asignaturas del curso borramos curso
            cursos.borrar(String.valueOf(aux.getCodi()));
        } else {
            JOptionPane.showMessageDialog(null, "No s'ha trobat el curs, no existeix!");
        }
    }

    private boolean trobarAssignatura(String codiAssignatura) {
        curs aux = cursos.getPrimer();
        boolean trobat = false;
        while (aux != null) {
            for (assignatura i : aux.getLlistaAssign()) {
                if (i.getCodi() == Integer.parseInt(codiAssignatura)) {
                    trobat = true;
                    break;
                }
            }
            if (trobat) {
                break;
            }
            aux = aux.getSeg();
        }
        return trobat;
    }
}
