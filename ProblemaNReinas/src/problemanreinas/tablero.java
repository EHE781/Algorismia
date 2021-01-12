/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemanreinas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author emanu
 */
public class tablero extends JFrame {
    JLabel casillas[][];
    private ArrayList<reina> reinas = new ArrayList();
    //private final Dimension sizeFrame = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension sizeFrame = new Dimension(720, 720);
    private int cont = 0;
    private int xC, yC;
    public tablero(int n) {
        this.setLayout(new FlowLayout());
        this.setMinimumSize(sizeFrame);
        this.setLayout(new GridLayout(n, n));
        casillas = new JLabel[n][n];
        xC = (int) Math.floor(sizeFrame.height / n);
        yC = xC;
        inicializarCasillas();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (int i = 0; i < reinas.size(); i++) {
            g2d.drawImage(reinas.get(i).getReina(), reinas.get(i).getX(), reinas.get(i).getY(), null);
        }
    }

    private void inicializarCasillas() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                JLabel casilla = new JLabel();
                casilla.setOpaque(true);
                casilla.setSize(new Dimension(xC, yC));
                casillas[i][j] = casilla;
                paintCasillas(casilla, i, j);
                this.add(casilla);
            }
        }
    }

    private void paintCasillas(JLabel casilla, int x, int y) {
        if ((y + x + 1) % 2 == 0) {
            casilla.setBackground(new Color(47, 32, 15));
        } else {
            casilla.setBackground(new Color(220, 191, 128));
        }
    }

    public void dibujarSoluciones(int[][] sol, reina[] q) {
        int cont = 0;
        for (int f = 0; f < sol.length; f++) {
            for (int c = 0; c < sol.length; c++) {
                if (sol[f][c] == 1) {
                    int pos[] = centroDe(casillas[f][c]);
                    q[cont].setImage(pos[0], pos[1], casillas[f][c].getWidth() - 10, casillas[f][c].getHeight() - 10);
                    reinas.add(q[cont++]);
                }
            }
        }
        this.repaint();
    }

    private int[] centroDe(JLabel c) {
        int xy[] = new int[2];
        xy[0] = c.getX() + (c.getSize().width / 2) + 10;
        xy[1] = c.getY() + (c.getSize().height / 2) + 30;
        return xy;
    }
}
/*
class tpanel extends JPanel {

    JLabel casillas[][];
    private ArrayList<reina> reinas = new ArrayList();
    private final Dimension sizeFrame = new Dimension(512, 512);
    private int cont = 0;
    private int xC, yC;

    public tpanel(int n) {
        super();
        this.setLayout(new GridLayout(n, n));
        casillas = new JLabel[n][n];
        xC = (int) Math.floor(sizeFrame.height / n);
        yC = xC;
        inicializarCasillas();
    }

    
}
*/
