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
import javax.swing.JTextField;

/**
 *
 * @author emanu
 */
public class interfaceGrafica extends JFrame {

    private final String[] nom_elements = {"Matricular estudiant", "Eliminar estudiant",
        "Implementar assignatura", "Anular assignatura"};
    private final int BOTONS_ELEMENTS = nom_elements.length;
    private String imatgeLogo = "logo.png";

    public interfaceGrafica() {
        this.setLayout(new BorderLayout());
        this.inicialitzaNord();
        this.inicialitzaOest();
        //this.inicialitzaEst();
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
        JLabel blank = new JLabel("HOLA");
        blank.setSize(800, 100);
        blank.setMinimumSize(new Dimension(800, 100));
        blank.setMaximumSize(new Dimension(800, 100));
        blank.setPreferredSize(new Dimension(800, 100));
        JLabel temps = new JLabel();
        temps.setSize(50, 100);
        tiraSuperior.add(logo);
        tiraSuperior.add(blank);
        tiraSuperior.add(temps);
        this.add(tiraSuperior, BorderLayout.NORTH);
    }

    private void inicialitzaOest() {
        JPanel elements = new JPanel();
        elements.setLayout(new GridLayout(BOTONS_ELEMENTS, 1));
        JPanel operacions = new JPanel();
        JButton botonsElements[] = new JButton[BOTONS_ELEMENTS];
        ActionListener accions[] = new ActionListener[BOTONS_ELEMENTS];
        //faltan 3 actionListeners de las 3 acciones que faltan
        ActionListener matricular = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String dades[] = obrirEmergentEstudiant();
                main.matricularEstudiant(dades[0], dades[1]);
            }

        };
        //meter en 1 2 3 lo que falta
        accions[0] = matricular;

        for (int i = 0; i < BOTONS_ELEMENTS; i++) {
            JButton boto = new JButton(nom_elements[i]);
            elements.add(boto);
            botonsElements[i] = boto;
            boto.addActionListener(accions[i]);
        }
        this.add(elements, BorderLayout.WEST);
    }

    private void inicialitzaEst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            "Codi assignatura: ", codi
        };
        JOptionPane.showConfirmDialog(null, dades, "Codi", JOptionPane.OK_CANCEL_OPTION);
        //Si codi es null, retornam -1
        return codi == null ? -1 : Integer.parseInt(codi.getText());
    }

    public String[] obrirEmergentEstudiant() {
        JTextField nom = new JTextField();
        JTextField dni = new JTextField();
        JTextField assignatura = new JTextField();
        Object camps[] = {
            "Nom: ", nom,
            "Dni: ", dni,
            "Assignatura: ", assignatura 
        };
        JOptionPane.showConfirmDialog(null, camps, "Introdueix les dades", JOptionPane.OK_CANCEL_OPTION);
        String respostes[] = {nom.getText(), dni.getText()};
        return respostes;
    }

    public String[] obrirEmergentAssignatura() {
        JTextField nom = new JTextField();
        JTextField codi = new JTextField();
        boolean batxNfp = false;
        String batx[] = new String[batxiller.especialitat.values().length];
        String fp[] = new String[FP.especialitat.values().length];
        int cont = 0;
        String tipus[] = {"Batxiller", "FP"};
        JList lista = new JList<String>(tipus);
        JScrollPane eleccio = new JScrollPane(lista);
        JScrollPane especialitat = null;
        JOptionPane.showConfirmDialog(null, eleccio, "Tipus", JOptionPane.OK_CANCEL_OPTION);
        JList listaBatx = new JList(batx);
        JList listaFp = new JList(fp);
        if (lista.getSelectedValue().equals(tipus[0])) {
            for (batxiller.especialitat i : batxiller.especialitat.values()) {
                batx[cont] = i.toString();
                cont++;
            }
            especialitat = new JScrollPane(listaBatx);
            batxNfp = true;
        }
        if (lista.getSelectedValue().equals(tipus[1])) {
            for (FP.especialitat i : FP.especialitat.values()) {
                fp[cont] = i.toString();
                cont++;
            }
            especialitat = new JScrollPane(listaFp);
        }
        Object demanar[] = {
            "Nom: ", nom,
            "Codi: ", codi,
            "Especialitat: ", especialitat
        };
        JOptionPane.showConfirmDialog(null, demanar, "Especialitat", JOptionPane.OK_CANCEL_OPTION);
        String dades[] = {nom.getText(), codi.getText(), batxNfp ? listaBatx.getSelectedValue().toString() : listaFp.getSelectedValue().toString()};

        return dades;
    }
}
