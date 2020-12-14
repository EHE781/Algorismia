/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author emanu
 */
public class interfaceGrafica extends JFrame {

    private final String[] nom_elements = {"<html><h2>Matricular estudiant</h2></html>",
                                           "<html><h2>Donar de alta curs</h2></html>",
                                           "<html><h2>Donar de baixa curs</h2></html>", 
                                           "<html><h2>Donar de baixa assignatura</h2></html>"};
    private final int BOTONS_ELEMENTS = nom_elements.length;
    private final int BOTONS_OPERACIONS = 2;//3 diferents imprimirs
    private String imatgeLogo = "resources/logo.png";
    private main principal;
    private JTextArea texte = null;

    public interfaceGrafica(main principal) {
        this.principal = principal;
        Dimension midaPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(midaPantalla);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.inicialitzaNord();
        this.inicialitzaCentre();
        this.inicialitzaOest();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //Logo de l'escola, i rellotge.
    private void inicialitzaNord() {
        JPanel tiraSuperior = new JPanel();
        tiraSuperior.setLayout(new FlowLayout());
        JLabel logo = new JLabel();
        Image imatge = null;
        BufferedImage img = null;
        try {
            imatge = ImageIO.read(new File(imatgeLogo));
            img = (BufferedImage) imatge;
            img = resizeImage(img, 200, 100);
        } catch (IOException noImg) {
            JOptionPane.showMessageDialog(null, "No es troba la imatge del logo");
        }
        logo.setIcon(new ImageIcon(img));
        logo.setSize(200, 100);
        JLabel blank = new JLabel("<html><h1>Gestionador de cursos</title></h1>", SwingConstants.CENTER);
        blank.setSize(600, 100);
        blank.setMinimumSize(new Dimension(1150, 100));
        blank.setMaximumSize(new Dimension(1150, 100));
        blank.setPreferredSize(new Dimension(1150, 100));
        JLabel temps = new JLabel();
        temps.setSize(50, 100);
        tiraSuperior.add(logo);
        tiraSuperior.add(blank);
        tiraSuperior.add(temps);
        this.add(tiraSuperior, BorderLayout.NORTH);
    }

    //Botons de funcions i accions
    private void inicialitzaOest() {
        JPanel elements = new JPanel();
        elements.setLayout(new GridLayout(BOTONS_ELEMENTS, 1));
        elements.setBackground(Color.cyan);
        JPanel operacions = new JPanel();
        operacions.setLayout(new GridLayout(BOTONS_OPERACIONS, 2));
        operacions.setBackground(Color.GREEN);
        JButton botoCurs = new JButton("<html><h2>Imprimir curs</h2></html>");
        JButton botoAssignatura = new JButton("<html><h2>Imprimir assignatura</h2></html>");
        JButton botoEstudiant = new JButton("<html><h2>Imprimir estudiant</h2></html>");
        JButton limpiar = new JButton("<html><h2>Limpiar Consola Info</h2></html>");
        //modificable
        botoCurs.setBackground(Color.YELLOW);
        botoAssignatura.setBackground(Color.yellow);
        botoEstudiant.setBackground(Color.yellow);
        limpiar.setBackground(Color.lightGray);
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                texte.setText("");
            }
        });
        ActionListener imprimirCurs = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                //preguntar que curso imprimir
                JTextField res = new JTextField();
                JOptionPane.showConfirmDialog(null, res, "Quin curs imprimir?:", JOptionPane.OK_CANCEL_OPTION);
                int codiCurs = "".equals(res.getText()) || !esNumero(res.getText()) ? -1 : Integer.parseInt(res.getText());
                if (codiCurs == -1) {
                    JOptionPane.showMessageDialog(null, "No es pot llegir el codi!");
                } //imprimir ese curso
                else {
                    if (principal.getCursos().trobar(String.valueOf(codiCurs)) != null) {
                        if (principal.getCursos().trobar(String.valueOf(codiCurs)) instanceof batxiller) {
                            batxiller b = (batxiller) principal.getCursos().trobar(String.valueOf(codiCurs));
                            String codis[] = new String[b.getLlistaAssign().size()];
                            impresion += b.imprimir();
                            impresion += "---------------------------------------------------------------------------------\n";
                            impresion += "Conté les seguents assignatures:\n\n";
                            for (assignatura a : b.getLlistaAssign()) {
                                impresion += a.imprimir();
                                codis[b.getLlistaAssign().indexOf(a)] = String.valueOf(a.getCodi());
                                nodoEstudiant est = a.llista.getPrimer();
                                while (est != null) {
                                    estudiant e = est.getEstudiant();
                                    impresion += e.imprimirEstudiant();
                                    impresion += "\n\n";
                                    est = est.seguent();
                                }
                                impresion += "\n";
                            }
                        } else {
                            FP fp = (FP) principal.getCursos().trobar(String.valueOf(codiCurs));
                            String codis[] = new String[fp.getLlistaAssign().size()];
                            impresion += fp.imprimir();
                            impresion += "---------------------------------------------------------------------------------\n";
                            impresion += "Conté les seguents assignatures:\n";
                            for (assignatura a : fp.getLlistaAssign()) {
                                impresion += a.imprimir();
                                codis[fp.getLlistaAssign().indexOf(a)] = String.valueOf(a.getCodi());
                                nodoEstudiant est = a.llista.getPrimer();
                                while (est != null) {
                                    estudiant e = est.getEstudiant();
                                    impresion += "\n";
                                    impresion += e.imprimirEstudiant();
                                    impresion += "\n\n";
                                    est = est.seguent();
                                }
                                impresion += "\n";
                            }
                        }
                        texte.setText(impresion);
                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix el curs!");
                    }

                }
                //imprimir las asignaturas que tiene
                //imprimir estudiantes matricualdos a cada asginatura
            }

        };
        ActionListener imprimirAssignatura = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                JTextField res = new JTextField();
                JOptionPane.showConfirmDialog(null, res, "Quin es el codi?", JOptionPane.OK_CANCEL_OPTION);
                int codi = "".equals(res.getText()) || !esNumero(res.getText()) ? -1 : Integer.parseInt(res.getText());
                if (codi != -1) {
                    curs aux = principal.getCursos().getPrimer();
                    assignatura a = null;
                    while (aux != null) {
                        a = aux.getLlistaAssign().trobar(String.valueOf(codi));
                        if (a != null) {
                            impresion += a.imprimir();
                            impresion += "\n";
                            impresion += "--------------------------------------------------------------------\n";
                            if (aux instanceof batxiller) {
                                batxiller b = (batxiller) aux;
                                impresion += b.imprimir();
                                impresion += "\n\n";
                            } else {
                                FP fp = (FP) aux;
                                impresion += fp.imprimir();
                                impresion += "\n\n";
                            }
                            break;
                        }
                        aux = aux.getSeg();
                    }
                    if (a != null) {
                        nodoEstudiant est = a.llista.getPrimer();
                        while (est != null) {
                            estudiant e = est.getEstudiant();
                            impresion += e.imprimirEstudiant();
                            impresion += "\n\n";
                            est = est.seguent();
                        }
                        impresion += "\n";
                        texte.setText(impresion);
                    }else{
                        JOptionPane.showMessageDialog(null, "No existeix l'assignatura");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existeix l'assignatura");
                }
            }

        };
        ActionListener imprimirEstudiant = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                JTextField res = new JTextField();
                JOptionPane.showConfirmDialog(null, res, "Quin es el DNI de l'estudiant?", JOptionPane.OK_CANCEL_OPTION);
                estudiant est = principal.getEstudiants().trobar(res.getText());
                if (est != null) {
                    impresion += est.imprimirEstudiant();
                    impresion += "--------------------------------------------------------------------\n";
                    for (assignatura a : est.getLlistaAssignatures()) {
                        impresion += a.imprimir();
                        curs aux = principal.getCursos().getPrimer();
                        while (aux != null) {
                            if (aux.getLlistaAssign().trobar(String.valueOf(a.getCodi())) != null) {
                                impresion += "Pertany al curs:\n";
                                if (aux instanceof batxiller) {
                                    batxiller b = (batxiller) aux;
                                    impresion += b.imprimir();
                                } else {
                                    FP fp = (FP) aux;
                                    impresion += fp.imprimir();
                                }
                                impresion += "\n\n";
                                break;
                            }
                            aux = aux.getSeg();
                        }
                    }
                    texte.setText(impresion);
                } else {
                    JOptionPane.showMessageDialog(null, "No existeix l'estudiant!");
                }
            }

        };
        /*
        ActionListener escuchador = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                impresion += "Els cursos actuals son: \n";
                curs auxC = principal.getCursos().getPrimer();
                while (auxC != null) {
                    if (auxC instanceof batxiller) {
                        batxiller b = (batxiller) auxC;
                        impresion += "El curs s'anomena " + b.getNom() + ", amc codi: " + b.getCodi() + ", i especialitat: " + b.getEspecialitat().toString() + "\n";
                        impresion += "Conté les seguents assignatures:\n";
                        for (assignatura a : b.getLlistaAssign()) {
                            impresion += a.imprimir() + "\n";
                        }
                        impresion += "\n\n";
                    } else {
                        FP fp = (FP) auxC;
                        impresion += "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n";
                        impresion += "El curs s'anomena " + fp.getNom() + ", amc codi: " + fp.getCodi() + ", i especialitat: " + fp.getEspecialitat().toString() + "\n";
                        impresion += "Conté les seguents assignatures:\n";
                        for (assignatura a : fp.getLlistaAssign()) {
                            impresion += a.imprimir() + "\n";
                        }
                        impresion += "\n\n";
                    }
                    auxC = auxC.getSeg();
                }
                impresion += "--------------------------------------------------------------------\n";
                impresion += "En quant als estudiants, matriculats tenim: \n";
                nodoEstudiant auxE = principal.getEstudiants().getPrimer();
                while (auxE != null) {
                    impresion += "····································································\n";
                    estudiant auxe = auxE.getEstudiant();
                    impresion += auxe.getNom() + ", amd DNI: " + auxe.getDni() + " matriculat a: \n";
                    for (assignatura a : auxe.getLlistaAssignatures()) {
                        impresion += a.imprimir() + "\n";
                    }
                    auxE = auxE.seguent();
                }
                texte.setText(impresion);
            }

        };
         */
        botoCurs.addActionListener(imprimirCurs);
        botoAssignatura.addActionListener(imprimirAssignatura);
        botoEstudiant.addActionListener(imprimirEstudiant);
        operacions.add(botoCurs);
        operacions.add(botoAssignatura);
        operacions.add(botoEstudiant);
        operacions.add(limpiar);
        JButton botonsElements[] = new JButton[BOTONS_ELEMENTS];
        ActionListener accions[] = new ActionListener[BOTONS_ELEMENTS];
        //faltan 3 actionListeners de las 3 acciones que faltan
        ActionListener matricular = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String dades[] = obrirEmergentEstudiant();
                    principal.matricularEstudiant(dades[0], dades[1], dades[2]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No es possible matricular\nDades incorrectes!");
                }
            }

        };
        ActionListener altaCurs = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    obrirEmergentAltaCurs();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No s'ha pogut donar d'alta el curs!");
                }
            }
        };
        ActionListener baixaCurs = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String infoCurs = obrirEmergentBaixaCurs();
                    principal.baixaCurs(Integer.parseInt(infoCurs));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No s'ha pogut donar de baixa correctament!");
                }
            }
        };
        ActionListener baixaAssignatura = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int dades[] = obrirEmergentBorrarAssignatura();
                if (dades[0] != -1 && dades[1] != -1) {
                    principal.baixaAssignatura(dades[0], dades[1]);
                } else {
                    JOptionPane.showMessageDialog(null, "No s'ha detectat cap codi vàlid!");
                }
            }
        };
        accions[0] = matricular;
        accions[1] = altaCurs;
        accions[2] = baixaCurs;
        accions[3] = baixaAssignatura;

        for (int i = 0; i < BOTONS_ELEMENTS; i++) {
            JButton boto = new JButton(nom_elements[i]);
            elements.add(boto);
            botonsElements[i] = boto;
            boto.addActionListener(accions[i]);
            boto.setBackground(Color.green);
        }
        JPanel elemOp = new JPanel();
        elemOp.setLayout(new GridLayout(5, 1));
        JLabel titolOperacions = new JLabel("<html><h1>Operacions</h1></html>", SwingConstants.CENTER);
        JLabel titolImpresions = new JLabel("<html><h1>Impresions</h1></html>", SwingConstants.CENTER);
        elemOp.add(titolOperacions);
        elemOp.add(elements);
        elemOp.add(titolImpresions);
        elemOp.add(operacions);
        JPanel tancament = new JPanel();
        JButton tancar = new JButton("<html><h2>Sortir</h2></html>");
        tancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }

        });
        JLabel exit = new JLabel("<html><h1>Sortir del programa</h1></html>", SwingConstants.CENTER);
        tancament.setLayout(new GridLayout(2, 1));
        tancar.setBackground(Color.RED);
        tancament.add(exit);
        tancament.add(tancar);
        elemOp.add(tancament);
        this.add(elemOp, BorderLayout.WEST);
    }

    private void inicialitzaCentre() {
        texte = new JTextArea();
        texte.setFont(new Font("New Times Roman", Font.BOLD, 20));
        texte.setText("Aquí s'imprimirà informació");
        texte.setEditable(false);
        JScrollPane scroll = new JScrollPane(texte);
        this.add(scroll, BorderLayout.CENTER);
    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    //Retorna el codi o -1 si no es fica cap codi
    public int[] obrirEmergentBorrarAssignatura() {
        JTextField codi = new JTextField();
        JTextField codiCurs = new JTextField();
        Object dades[] = {
            "Codi del curs en el que es troba: ", codiCurs,
            "Codi assignatura a borrar: ", codi
        };
        JOptionPane.showConfirmDialog(null, dades, "Codi", JOptionPane.OK_CANCEL_OPTION);
        //Si codi es null, retornam -1
        int resultats[] = {
            "".equals(codiCurs.getText()) || !esNumero(codiCurs.getText()) ? -1 : Integer.parseInt(codiCurs.getText()),
            "".equals(codi.getText()) || !esNumero(codi.getText()) ? -1 : Integer.parseInt(codi.getText())
        };
        return resultats;
    }

    private boolean esNumero(String nr) {
        try {
            int i = Integer.parseInt(nr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void obrirEmergentAltaCurs() {
        JTextField nr = new JTextField();
        boolean problema = false;
        String[] nomTipusCurs = {"Batxiller", "FP"};
        String nomEspecialitat = "";
        JList tipusCurs = new JList<String>(nomTipusCurs);
        boolean batxNfp = false;
        Object info[] = {
            "Curs del tipus: ", tipusCurs,
            "Numero assignatures que tendrà el curs: ", nr
        };
        int res = JOptionPane.showConfirmDialog(null, info, "Informació", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String batx[] = new String[batxiller.especialitat.values().length];
            String fp[] = new String[FP.especialitat.values().length];
            int cont = 0;
            JList listaBatx = new JList(batx);
            JList listaFp = new JList(fp);
            if (tipusCurs.getSelectedValue().equals(nomTipusCurs[0])) {
                for (batxiller.especialitat i : batxiller.especialitat.values()) {
                    batx[cont] = i.toString();
                    cont++;
                }
                batxNfp = true;
            }
            if (tipusCurs.getSelectedValue().equals(nomTipusCurs[1])) {
                for (FP.especialitat i : FP.especialitat.values()) {
                    fp[cont] = i.toString();
                    cont++;
                }
            }
            JTextField codiCurs = new JTextField();
            Object enumerat[] = {
                "Especialitat del curs?: ", batxNfp ? listaBatx : listaFp,
                "Codi del curs (numeric): ", codiCurs
            };
            res = JOptionPane.showConfirmDialog(null, enumerat, "Especifica les dades", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    int tipus = -1;
                    if (tipusCurs.getSelectedValue().toString().equals(nomTipusCurs[0])) {
                        tipus = 0;
                    } else if (tipusCurs.getSelectedValue().toString().equals(nomTipusCurs[1])) {
                        tipus = 1;
                    }
                    nomEspecialitat = batxNfp ? listaBatx.getSelectedValue().toString() : listaFp.getSelectedValue().toString();
                    boolean trobat = principal.altaCurs(tipusCurs.getSelectedValue().toString().toLowerCase(), nomEspecialitat.toLowerCase(), Integer.parseInt(codiCurs.getText()));
                    if (trobat && res == JOptionPane.OK_OPTION && nr.getText() != null) {

                        for (int i = 0; i < Integer.parseInt(nr.getText()); i++) {

                            String nova[] = obrirEmergentAssignatura();
                            //en tipus es si es de batxiller o FP
                            problema = principal.altaAssignatura(codiCurs.getText(), nova[0], nova[1], nova[2], nova[3]);
                            if (problema){
                                break;
                            }
                        }
                    }
                    if (problema || res != JOptionPane.OK_OPTION) {
                        principal.baixaCurs(Integer.parseInt(codiCurs.getText()));
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No es possible!");
                    principal.baixaCurs(Integer.parseInt(codiCurs.getText()));
                    
                }
            }
        }
    }

    public String obrirEmergentBaixaCurs() {
        //String tipus[] = {"Batxiller", "FP"};
        //JList lista = new JList<String>(tipus);
        boolean batxNfp = false;
        String batx[] = new String[batxiller.especialitat.values().length];
        String fp[] = new String[FP.especialitat.values().length];
        int cont = 0;
        JList listaBatx = new JList(batx);
        JList listaFp = new JList(fp);
        try {
//            if (JOptionPane.showConfirmDialog(null, lista, "Tipus", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
//                if (lista.getSelectedValue().equals(tipus[0])) {
//                    for (batxiller.especialitat i : batxiller.especialitat.values()) {
//                        batx[cont] = i.toString();
//                        cont++;
//                    }
//                    batxNfp = true;
//                }
//                if (lista.getSelectedValue().equals(tipus[1])) {
//                    for (FP.especialitat i : FP.especialitat.values()) {
//                        fp[cont] = i.toString();
//                        cont++;
//                    }
//                }
                JTextField codi = new JTextField();
                Object demanar[] = {
                    "Quin es el codi (numeric)?: ", codi};
                if (JOptionPane.showConfirmDialog(null, demanar, "CODI?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        String dades = codi.getText();
                        //"".equals(codiCurs.getText()) || !esNumero(codiCurs.getText()) ? -1 : Integer.parseInt(codiCurs.getText())
                        return dades;
                }   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dades o botons incorrectes!");
        }
        return null;
    }

    public String[] obrirEmergentEstudiant() {
        JTextField nom = new JTextField();
        JTextField dni = new JTextField();
        JTextField assignatura = new JTextField();
        Object camps[] = {
            "Nom de l'estudiant: ", nom,
            "DNI (codi alfanumèric): ", dni,
            "Assignatura a la qual es matricula: ", assignatura
        };
        int err = JOptionPane.showConfirmDialog(null, camps, "Introdueix les dades", JOptionPane.OK_CANCEL_OPTION);
        String respostes[] = {nom.getText(), dni.getText(), assignatura.getText()};
        for (String s : respostes) {
            if (s == null || s == "") {
                respostes = null;
                break;
            }
        }
        if (err == JOptionPane.OK_OPTION && respostes != null) {
            return respostes;
        } else {
            return null;
        }
    }

    public String[] obrirEmergentAssignatura() {
        JTextField nom = new JTextField();
        JTextField codi = new JTextField();
        JTextField credits = new JTextField();
        String tipusAssign[] = {"Obligatoria", "Optativa"};
        JList oblOpt = new JList<String>(tipusAssign);
        String perfils[] = {"Teoric", "Practica"};
        JList perfil = new JList(perfils);
        int err = JOptionPane.showConfirmDialog(null, oblOpt, "Tipus?", JOptionPane.OK_CANCEL_OPTION);
        if (err == JOptionPane.OK_OPTION && oblOpt.getSelectedValue() != null) {
            boolean obligatoria = true;
            if (oblOpt.getSelectedValue().toString().equals(tipusAssign[1])) {
                obligatoria = false;
            }
            Object demanar[] = {
                "Nom de la assignatura: ", nom,
                "Assigna un codi: ", codi,
                obligatoria ? "Crèdits a superar: " : "Perfil de la assignatura: ", obligatoria ? credits : perfil
            };
            int res = JOptionPane.showConfirmDialog(null, demanar, "Assignatura", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                String dades[] = {
                    nom.getText(),
                    codi.getText().toLowerCase(),
                    oblOpt.getSelectedValue().toString().toLowerCase(),
                    obligatoria ? credits.getText().toLowerCase() : perfil.getSelectedValue().toString().toLowerCase()
                };
                for (String s : dades) {
                    if (s == null || s == "") {
                        dades = null;
                        break;
                    }
                }
                if (dades != null) {
                    return dades;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
