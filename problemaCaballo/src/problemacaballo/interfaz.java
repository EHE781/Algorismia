/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemacaballo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author emanu
 */
public class interfaz extends JFrame {

    private JTextField resSize;
    private JTextField x, y;
    private boolean solucion = false;
    private boolean ready = false;
    private final Dimension coorSize = new Dimension(100, 100);//es ignorada se puede quitar
    private problemaCaballo pr;
    
    public interfaz() {
        this.setLayout(new GridLayout(4, 1));
        inicializar();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void inicializar() {
        JPanel tablero = new JPanel();
        tablero.setLayout(new GridLayout(1, 2));
        JPanel caballo = new JPanel();
        caballo.setLayout(new GridLayout(1, 2));
        JLabel askSize = new JLabel("Tamaño del tablero: ");
        resSize = new JTextField();
        tablero.add(askSize);
        tablero.add(resSize);
        JLabel askCaballo = new JLabel("Coordenadas del caballo: ");
        x = new JTextField();
        y = new JTextField();
        JLabel middle = new JLabel(", ");
        x.setSize(coorSize);
        y.setSize(coorSize);
        caballo.add(askCaballo);
        JPanel coord = new JPanel();
        coord.setLayout(new GridLayout(1, 3));
        coord.add(x);
        coord.add(middle);
        coord.add(y);
        caballo.add(coord);
        this.add(tablero);
        this.add(caballo);
        JButton soluciones = new JButton("Ver Soluciones");
        soluciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tablero t = new tablero(getSizeTablero());
                for (int solucion[][] : getInstance().soluciones) {
                    t.dibujarSoluciones(solucion, false);
                }
                t.dibujarSoluciones(null, true);
                pr.setFalse();
                soluciones.setEnabled(false);
            }
        });
        soluciones.setEnabled(false);
        this.add(soluciones);
        JButton res = new JButton("Calcular");
        res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (getNice()) {
                    setReady(true);
                }
                if (ready) {
                    pr = new problemaCaballo();
                    pr.start(getSizeTablero(), getXCaballo(), getYCaballo());
                    soluciones.setEnabled(pr.getReady());
                    if (!pr.getReady()) {
                        JOptionPane.showMessageDialog(null, "No se han encontrado soluciones");
                    }
                }
            }
        });
        this.add(res);
    }

    public int getSizeTablero() {
        return Integer.parseInt(this.resSize.getText());
    }

    public int getXCaballo() {
        return Integer.parseInt(this.x.getText());
    }

    public int getYCaballo() {
        return Integer.parseInt(this.y.getText());
    }

    public boolean verSoluciones() {
        return this.solucion;
    }

    public void setReady(boolean state) {
        this.ready = state;
    }

    public problemaCaballo getInstance() {
        return this.pr;
    }

    public boolean getNice() {
        try {
            int i = Integer.parseInt(resSize.getText());
            int j = Integer.parseInt(x.getText());
            int k = Integer.parseInt(y.getText());
            if (i + j + k == i + j + k) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
