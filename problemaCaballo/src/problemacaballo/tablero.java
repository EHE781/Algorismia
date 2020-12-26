/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemacaballo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author emanu
 */
public class tablero extends JFrame {
    private caballo horse;
    JLabel casillas[][];
    private boolean colorear = false;
    private int xC;
    private int yC;
    private final Dimension sizeFrame = new Dimension(512, 512);
    private Point puntos[][];
    private boolean ready = false;
    private int cont = 0;

    public tablero(int n) {
        puntos = new Point[3][n * n];
        for (Point punto[] : puntos) {
            for (int p = 0; p < punto.length; p++) {
                punto[p] = new Point();
            }
        }
        this.setLayout(new GridLayout(n, n));
        this.setSize(sizeFrame);
        this.setMinimumSize(sizeFrame);
        this.setMaximumSize(sizeFrame);
        casillas = new JLabel[n][n];
        xC = (int) Math.floor(sizeFrame.height / n);
        yC = xC;
        inicializarCasillas();
        //this.setGlassPane(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void dibujarSoluciones(int soluciones[][], boolean dibujarDefinitivo) {
        if (!dibujarDefinitivo) {
            for (int i = 0; i < soluciones.length - 1; i++) {
                //pintar linea de solucion[i][0],solucion[i][1] a las demas, 1 a 1;
                int posInicial[] = new int[2];
                posInicial = centroDe(casillas[soluciones[i][0]][soluciones[i][1]]);
                int posFinal[] = new int[2];
                posFinal = centroDe(casillas[soluciones[i + 1][0]][soluciones[i + 1][1]]);
                puntos[cont][i].x = posInicial[0];
                puntos[cont][i].y = posInicial[1];
                puntos[cont][i + 1].x = posFinal[0];
                puntos[cont][i + 1].y = posFinal[1];
            }
            cont++;
            repaint();
        } else {
            horse = new caballo(puntos[0][0].x, puntos[0][0].y, casillas[0][0].getWidth() - 10, casillas[0][0].getHeight() - 10);
            ready = true;
            repaint();
        }
    }

    private int[] centroDe(JLabel c) {
        int xy[] = new int[2];
        xy[0] = c.getX() + (c.getSize().width / 2) + 10;
        xy[1] = c.getY() + (c.getSize().height / 2) + 30;
        return xy;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (ready) {
            g2d.drawImage(horse.getCaballo(), horse.getX(), horse.getY(), null);
            Color colores[] = new Color[3];
            colores[0] = Color.RED;
            colores[1] = Color.BLUE;
            colores[2] = Color.BLACK;
            int contador = 0;
            int ARR_SIZE = 13;
            int thickness = 7;
            for (Point punto[] : puntos) {
                g2d.setColor(colores[contador++]);
                ARR_SIZE -= 3;
                thickness -= 2;
                for (int j = 0; j < punto.length - 1; j++) {
                    g2d.setStroke(new BasicStroke(thickness));
                    double dx = punto[j + 1].x - punto[j].x;
                    double dy = punto[j + 1].y - punto[j].y;
                    double angle = Math.atan2(dy, dx);
                    int len = (int) Math.sqrt(dx * dx + dy * dy);
                    Line2D line = new Line2D.Double(punto[j], punto[j + 1]);
                    g2d.draw(line);
                    Graphics2D g2dCopy = (Graphics2D) g2d.create();
//                    g2d.drawLine(punto[j].x, punto[j].y, punto[j + 1].x, punto[j + 1].y);
                    AffineTransform at = AffineTransform.getTranslateInstance(punto[j].x, punto[j].y);
                    at.concatenate(AffineTransform.getRotateInstance(angle));
                    g2dCopy.transform(at);

                    g2dCopy.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                            new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
                }
            }
        }
        g2d.dispose();

    }

    
}
