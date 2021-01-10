package problemanreinas;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 *
 * @author emanuel
 *
 */
public class ProblemaNReinas {

    public static PrintWriter out = new PrintWriter(System.out);
    public static QueenPosition[] p;

    public static void main(String[] args) {

        System.out.println("Enter Number of Queens");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int f = 21;
        int c = 27;
        p = new QueenPosition[n];
        p[0] = new QueenPosition(f, c);

        if (getSolution(n, 0, 0)) {
            int[][] result = new int[n][n];
            for(QueenPosition q : p){
                if(q != null){
                    result[q.row][q.col] = 1;
                }
            }

            out.println("\n\nDisplay using normal For loop \n---------------------------");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(result[i][j] + " ");
                }
                out.println();
            }
        } else {
            out.println("Solution not available.");
        }

        out.flush();

    }

    public static boolean getSolution(int n, int row, int cont) {
        if (n == 2 || n == 3) {
            return false;
        }

        if (row == n || cont == n - 1) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            boolean isSafe = true;
            p[cont] = new QueenPosition(row, col);

            for (int placedQueen = 0; placedQueen < row; placedQueen++) {
                if (p[placedQueen].col == col || p[placedQueen].row == row ||
                        p[placedQueen].row - p[placedQueen].col == row - col
                        || p[placedQueen].row + p[placedQueen].col == row + col) {
                    isSafe = false;
                
                }
            }

            if (isSafe) {
                if (getSolution(n, row + 1, cont + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class QueenPosition {

    int row;
    int col;

    public QueenPosition(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
}
