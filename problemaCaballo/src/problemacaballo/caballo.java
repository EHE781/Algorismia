/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemacaballo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author emanu
 */
public class caballo {

    private int x;
    private int y;
    private int jPx;
    private int jPy;

    public caballo(int x, int y, int jPanelWidth, int jPanelHeight) {
        this.x = x - jPanelWidth / 2;
        this.y = y - jPanelHeight / 2;
        this.jPx = jPanelWidth ;
        this.jPy = jPanelHeight ;
    }

    public Image getCaballo() {
        try {
            return ImageIO.read(new File("caballo.png")).getScaledInstance(jPx, jPy, Image.SCALE_SMOOTH);
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
