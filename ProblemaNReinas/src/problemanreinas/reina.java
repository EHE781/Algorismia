/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemanreinas;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author emanu
 */
public class reina {

    private int x, y, jPx, jPy;
    int f;
    int c;

    public reina(int fila, int columna) {
        this.f = fila;
        this.c = columna;
    }
    public void setImage(int x, int y, int jPanelWidth, int jPanelHeight) {
        this.x = x - jPanelWidth / 2;
        this.y = y - jPanelHeight / 2;
        this.jPx = jPanelWidth;
        this.jPy = jPanelHeight;
    }

    public Image getReina() {
        try {
            return ImageIO.read(new File("reinaR.png")).getScaledInstance(jPx, jPy, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            return null;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
