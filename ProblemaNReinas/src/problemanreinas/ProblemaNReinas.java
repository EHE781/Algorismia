package problemanreinas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 *
 * @author emanuel
 *
 */
public class ProblemaNReinas {

    public PrintWriter out = new PrintWriter(System.out);
    public reina[] reinas;
    int n, f, c;
    public ProblemaNReinas(){
        JLabel askReinas = new JLabel("<html><h1>Reinas?</h1></html>");
        JTextField nReinas = new JTextField();
        JLabel askPosicion = new JLabel("<html><h2>Posición reina?</h2></html>");
        JTextField xReina = new JTextField();
        JTextField yReina = new JTextField();
        JButton aceptar = new JButton("Calcular");
        aceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                n = Integer.parseInt(nReinas.getText());
                f = Integer.parseInt(yReina.getText());
                c = Integer.parseInt(xReina.getText());
                start();
            }
        
        });
        JPanel nrReinas = new JPanel();
        nrReinas.setLayout(new GridLayout(1,2));
        nrReinas.add(askReinas);
        nrReinas.add(nReinas);
        JPanel coor = new JPanel();
        coor.setLayout(new GridLayout(1,3));
        coor.add(askPosicion);
        coor.add(xReina);
        coor.add(yReina);
        JFrame win = new JFrame("Datos");
        win.setLayout(new GridLayout(3,1));
        win.add(nrReinas);
        win.add(coor);
        win.add(aceptar);
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }

    public void start() {
        reinas = new reina[n];
        reinas[0] = new reina(f, c);

        if (getSolution(n, 0, 1)) {
            int[][] result = new int[n][n];
            for (reina q : reinas) {
                if (q != null) {
                    result[q.f][q.c] = 1;
                }
            }
            tablero t = new tablero(n);
            t.dibujarSoluciones(result, reinas);
            t.setVisible(false);
            t.setVisible(true);
            out.println("\n\nDisplay using normal For loop \n---------------------------");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(result[i][j] + " ");
                }
                out.println();
            }
        } else {
            out.println("Solution not available.");
            JOptionPane.showMessageDialog(null, "No hay soluciones!!!");
        }

        out.flush();

    }

    public boolean getSolution(int n, int columna, int cont) {
        if (n == 2 || n == 3 || columna == n) {
            return false;
        }
        boolean check = false;
        boolean terminate = false;
        for (int f = 0; f < n; f++) {
            boolean posible = true;
            for (reina r : reinas) {
                if (r != null) {

                    if (r.c == columna) {
                        terminate = true;
                        break;
                    } else {
                        if (r.f - r.c == f - columna
                                || r.f == f
                                || r.f + r.c == f + columna) {
                            posible = false;
                            break;
                        }
                    }

                } else {
                    check = true;
                    break;
                }
            }
            if (terminate) {
                break;
            } else {
                if (posible && check) {
                    reinas[cont] = new reina(f, columna);
                    if (cont == n - 1) {
                        return true;
                    }
                    if (getSolution(n, columna + 1, cont + 1)) {
                        return true;
                    }
                }
            }
        }
        if (terminate) {
            if (getSolution(n, columna + 1, cont)) {
                return true;
            }
        }
        reinas[cont] = null;
        if (cont != 1) //llevarem la reina anterior per evitar colisions al tornal al for, pero no la primera posada
        {
            reinas[cont - 1] = null;
        }
        return false;
    }

    public static void main(String[] args) {
        ProblemaNReinas p = new ProblemaNReinas();
    }
}
