/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_1_algorismia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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

    private final String[] nom_elements = {"Matricular estudiant", "Donar de alta curs",
        "Donar de baixa curs", "Donar de baixa assignatura"};
    private final int BOTONS_ELEMENTS = nom_elements.length;
    private String imatgeLogo = "logo.png";
    private main principal;
    private JTextArea texte = null;
    public interfaceGrafica(main principal) {
        this.principal = principal;
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
        JLabel blank = new JLabel("<html><h1>Gestionador de cursos</h1></center></html>", SwingConstants.CENTER);
        blank.setSize(600, 100);
        blank.setMinimumSize(new Dimension(600, 100));
        blank.setMaximumSize(new Dimension(600, 100));
        blank.setPreferredSize(new Dimension(600, 100));
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
        JPanel operacions = new JPanel();
        JButton imprimidor = new JButton("Imprimir");
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
                setVisible(false);
                setVisible(true);
            }

        };
        imprimidor.addActionListener(escuchador);
        operacions.add(imprimidor);
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
                obrirEmergentAltaCurs();
            }
        };
        ActionListener baixaCurs = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String infoCurs[] = obrirEmergentBaixaCurs();
                principal.baixaCurs(Integer.parseInt(infoCurs[0]), infoCurs[1], Integer.parseInt(infoCurs[2]));
            }
        };
        ActionListener baixaAssignatura = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int codi = obrirEmergentBorrarAssignatura();
                if (codi != -1) {
                    principal.baixaAssignatura(codi);
                } else {
                    JOptionPane.showMessageDialog(null, "No s'ha detectat cap codi!");
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
        }
        JPanel elemOp = new JPanel();
        elemOp.setLayout(new GridLayout(2, 1));
        elemOp.add(elements);
        elemOp.add(operacions);
        this.add(elemOp, BorderLayout.WEST);
    }

    private void inicialitzaCentre() {
        texte = new JTextArea();
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
    public int obrirEmergentBorrarAssignatura() {
        JTextField codi = new JTextField();
        Object dades[] = {
            "Codi assignatura a borrar: ", codi
        };
        JOptionPane.showConfirmDialog(null, dades, "Codi", JOptionPane.OK_CANCEL_OPTION);
        //Si codi es null, retornam -1
        return codi == null ? -1 : Integer.parseInt(codi.getText());
    }

    public void obrirEmergentAltaCurs() {
        JTextField nr = new JTextField();
        String[] nomTipusCurs = {"Batxiller", "FP"};
        String nomEspecialitat = "";
        JList tipusCurs = new JList<String>(nomTipusCurs);
        boolean batxNfp = false;
        Object info[] = {
            "Curs del tipus: ", tipusCurs,
            "Numero assignatures que tendrà el curs: ", nr
        };
        int res = JOptionPane.showConfirmDialog(null, info, "Informació", JOptionPane.OK_CANCEL_OPTION);
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
                principal.altaCurs(tipusCurs.getSelectedValue().toString().toLowerCase(), nomEspecialitat.toLowerCase(), Integer.parseInt(codiCurs.getText()));
                if (res == JOptionPane.OK_OPTION && nr.getText() != null) {

                    for (int i = 0; i < Integer.parseInt(nr.getText()); i++) {

                        String nova[] = obrirEmergentAssignatura();
                        //en tipus es si es de batxiller o FP
                        principal.altaAssignatura(tipus, nomEspecialitat, Integer.parseInt(codiCurs.getText()), nova[0], nova[1], nova[2], nova[3]);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No es possible!");
            }
        }
    }

    public String[] obrirEmergentBaixaCurs() {
        String tipus[] = {"Batxiller", "FP"};
        JList lista = new JList<String>(tipus);
        boolean batxNfp = false;
        String batx[] = new String[batxiller.especialitat.values().length];
        String fp[] = new String[FP.especialitat.values().length];
        int cont = 0;
        JList listaBatx = new JList(batx);
        JList listaFp = new JList(fp);
        try {
            if (JOptionPane.showConfirmDialog(null, lista, "Tipus", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                if (lista.getSelectedValue().equals(tipus[0])) {
                    for (batxiller.especialitat i : batxiller.especialitat.values()) {
                        batx[cont] = i.toString();
                        cont++;
                    }
                    batxNfp = true;
                }
                if (lista.getSelectedValue().equals(tipus[1])) {
                    for (FP.especialitat i : FP.especialitat.values()) {
                        fp[cont] = i.toString();
                        cont++;
                    }
                }
                JTextField codi = new JTextField();
                Object demanar[] = {
                    "Especialitat del curs?: ", batxNfp ? listaBatx : listaFp,
                    "Quin es el codi (numeric)?: ", codi,};
                if (JOptionPane.showConfirmDialog(null, demanar, "Especialitat?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    if (lista.getSelectedValue() != null) {
                        String dades[] = {
                            lista.getSelectedValue().equals("Batxiller") ? "0" : "1",
                            batxNfp ? listaBatx.getSelectedValue().toString().toLowerCase() : listaFp.getSelectedValue().toString().toLowerCase(),
                            codi.getText()
                        };
                        return dades;
                    }
                }
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
