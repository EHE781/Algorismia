/*
Clase InterfaceGrafica on manipulam tots els grafics de l'app
 */
package practica_1_algorismia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @authors Damián Gebhard , Emanuel Hegedus , Bartomeu Capó
 */
public class interfaceGrafica extends JFrame {

    private final String[] nom_elements = {
        "<html><h2>Matricular estudiant</h2></html>",
        "<html><h2>Donar de alta curs</h2></html>",
        "<html><h2>Donar de baixa curs</h2></html>",
        "<html><h2>Donar de baixa assignatura</h2></html>"};
    private final int BOTONS_ELEMENTS = nom_elements.length;
    private final int BOTONS_OPERACIONS = 2;
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

    //Logo de l'escola.
    private void inicialitzaNord() {
        JPanel tiraSuperior = new JPanel();
        tiraSuperior.setBackground(Color.GRAY);
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
        JLabel blank = new JLabel("<html><h1>Gestionador de col·legi </title></h1>", SwingConstants.CENTER);
        blank.setOpaque(true);
        blank.setBackground(new Color(152, 226, 233));
        blank.setSize(600, 100);
        blank.setMinimumSize(new Dimension(1150, 100));
        blank.setMaximumSize(new Dimension(1150, 100));
        blank.setPreferredSize(new Dimension(1150, 100));
        tiraSuperior.add(logo);
        tiraSuperior.add(blank);
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
        JButton limpiar = new JButton("<html><h2>Borrar text imprimit</h2></html>");
        botoCurs.setBackground(new Color(250,252,160));
        botoAssignatura.setBackground(new Color(250,252,160));
        botoEstudiant.setBackground(new Color(250,252,160));
        limpiar.setBackground(new Color(250,252,160));
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                texte.setText("");
            }
        });
        ActionListener imprimirCurs = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                JTextField res = new JTextField();
                JLabel missatge = new JLabel("Codi del curs:");
                missatge.setFont(new Font("Arial", Font.BOLD, 18));
                Object pregunta[] = {
                    missatge, res
                };
                int ok = JOptionPane.showConfirmDialog(null, pregunta, "Quin curs imprimir?:", JOptionPane.OK_CANCEL_OPTION);
                int codiCurs = "".equals(res.getText()) || !esNumero(res.getText()) ? -1 : Integer.parseInt(res.getText());
                if (codiCurs == -1 || ok == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "No es pot llegir el codi!");
                } 
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
                                    impresion += "\n\n";
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
            }

        };
        ActionListener imprimirAssignatura = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                JTextField res = new JTextField();
                JLabel missatge = new JLabel("Codi de la assignatura?");
                missatge.setFont(new Font("Arial", Font.BOLD, 18));
                Object pregunta[] = {
                    missatge, res
                };
                int ok = JOptionPane.showConfirmDialog(null, pregunta, "Quin es el codi?", JOptionPane.OK_CANCEL_OPTION);
                int codi = "".equals(res.getText()) || !esNumero(res.getText()) ? -1 : Integer.parseInt(res.getText());
                if (codi != -1 && ok == JOptionPane.OK_OPTION) {
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
                    } else {
                        JOptionPane.showMessageDialog(null, "No existeix l'assignatura");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existeix l'assignatura o s'ha cancelat l'acció");
                }
            }

        };
        ActionListener imprimirEstudiant = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String impresion = "";
                JTextField res = new JTextField();
                JLabel missatge = new JLabel("DNI de l'estudiant?");
                missatge.setFont(new Font("Arial", Font.BOLD, 18));
                Object pregunta[] = {
                    missatge, res
                };
                int ok = JOptionPane.showConfirmDialog(null, pregunta, "Quin es el DNI de l'estudiant?", JOptionPane.OK_CANCEL_OPTION);
                estudiant est = principal.getEstudiants().trobar(res.getText());
                if (est != null && ok == JOptionPane.OK_OPTION) {
                    impresion += est.imprimirEstudiant();
                    impresion += "--------------------------------------------------------------------\n";
                    for (assignatura a : est.getLlistaAssignatures()) {
                        impresion += a.imprimir();
                        curs aux = principal.getCursos().getPrimer();
                        while (aux != null) {
                            if (aux.getLlistaAssign().trobar(String.valueOf(a.getCodi())) != null) {
                                impresion += "\nPertany al curs:\n";
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
                    JOptionPane.showMessageDialog(null, "No existeix l'estudiant! o s'ha cancelat l'acció");
                }
            }

        };
        botoCurs.addActionListener(imprimirCurs);
        botoAssignatura.addActionListener(imprimirAssignatura);
        botoEstudiant.addActionListener(imprimirEstudiant);
        operacions.add(botoCurs);
        operacions.add(botoAssignatura);
        operacions.add(botoEstudiant);
        operacions.add(limpiar);
        JButton botonsElements[] = new JButton[BOTONS_ELEMENTS];
        ActionListener accions[] = new ActionListener[BOTONS_ELEMENTS];
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
            boto.setBackground(new Color(182,251,162));
        }
        JPanel elemOp = new JPanel();
        elemOp.setLayout(new GridLayout(5, 1));
        JLabel titolOperacions = new JLabel("<html><h1>Operacions</h1></html>", SwingConstants.CENTER);
        JLabel titolImpresions = new JLabel("<html><h1>Impressions</h1></html>", SwingConstants.CENTER);
        titolOperacions.setOpaque(true);
        titolImpresions.setOpaque(true);
        titolOperacions.setBackground(new Color(103, 255, 103));
        titolImpresions.setBackground(new Color(255, 255, 83));
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
        exit.setOpaque(true);
        exit.setBackground(new Color(255, 83, 83));
        tancament.setLayout(new GridLayout(2, 1));
        tancar.setBackground(new Color(254,131,131));
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
    private int[] obrirEmergentBorrarAssignatura() {
        JTextField codi = new JTextField();
        JTextField codiCurs = new JTextField();
        JLabel m1 = new JLabel("Codi del curs en el que es troba: ");
        JLabel m2 = new JLabel("Codi assignatura a borrar: ");
        m1.setFont(new Font("Arial", Font.BOLD, 18));
        m2.setFont(new Font("Arial", Font.BOLD, 18));
        Object dades[] = {
            m1, codiCurs,
            m2, codi
        };
        int res = JOptionPane.showConfirmDialog(null, dades, "Codi", JOptionPane.OK_CANCEL_OPTION);
        //Si codi es null, retornam -1
        int resultats[] = {
            "".equals(codiCurs.getText()) || !esNumero(codiCurs.getText()) ? -1 : Integer.parseInt(codiCurs.getText()),
            "".equals(codi.getText()) || !esNumero(codi.getText()) ? -1 : Integer.parseInt(codi.getText())
        };
        if (res == JOptionPane.OK_OPTION) {
            return resultats;
        } else {
            resultats[0] = -1;
            return resultats;
        }
    }

    private boolean esNumero(String nr) {
        try {
            int i = Integer.parseInt(nr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void obrirEmergentAltaCurs() {
        JTextField nr = new JTextField();
        boolean problema = false;
        String[] nomTipusCurs = {"Batxiller", "FP"};
        String nomEspecialitat = "";
        JList tipusCurs = new JList<String>(nomTipusCurs);
        tipusCurs.setFont(new Font("Arial", Font.BOLD, 16));
        boolean batxNfp = false;
        JLabel m1 = new JLabel("Curs del tipus: ");
        JLabel m2 = new JLabel("Numero assignatures que tendrà el curs: ");
        m1.setFont(new Font("Arial", Font.BOLD, 17));
        m2.setFont(new Font("Arial", Font.BOLD, 17));
        Object info[] = {
            m1, tipusCurs,
            m2, nr
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
                            if (problema) {
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

    private String obrirEmergentBaixaCurs() {
        boolean batxNfp = false;
        String batx[] = new String[batxiller.especialitat.values().length];
        String fp[] = new String[FP.especialitat.values().length];
        int cont = 0;
        JList listaBatx = new JList(batx);
        JList listaFp = new JList(fp);
        try {
            JTextField codi = new JTextField();
            Object demanar[] = {
                "Quin es el codi (numeric)?: ", codi};
            if (JOptionPane.showConfirmDialog(null, demanar, "CODI?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                String dades = codi.getText();
                return dades;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dades o botons incorrectes!");
        }
        return null;
    }

    private String[] obrirEmergentEstudiant() {
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

    private String[] obrirEmergentAssignatura() {
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
