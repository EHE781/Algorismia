/*
 */
package practica_1_algorismia;

import javax.swing.*;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
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
