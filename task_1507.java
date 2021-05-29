
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1507 implements Runnable {

    StreamTokenizer tok;
    BufferedReader in;
    PrintWriter out;
    boolean timus = System.getProperty("hi") != null;

    void init() throws IOException {
        if (timus) {
            in = new BufferedReader(new InputStreamReader(System.in));
            tok = new StreamTokenizer(in);
            out = new PrintWriter(System.out);
        } else {
            in = new BufferedReader(new FileReader("input.txt"));
            tok = new StreamTokenizer(in);
            out = new PrintWriter("output.txt");
        }
    }

    int readInt() throws IOException {
        tok.nextToken();
        return (int) tok.nval;
    }

    String readString() throws IOException {
        tok.nextToken();
        return tok.sval;
    }

    public static void main(String[] args) {
        new Thread(new task_1507()).start();
    }

    @Override
    public void run() {
        try {
            if (timus) {
                init();
                solve();
                out.flush();
            } else {
                long t1 = System.currentTimeMillis();
                init();
                solve();
                out.close();
                long t2 = System.currentTimeMillis();
                System.out.println(t2 - t1);
            }
        } catch(IOException e) {
            System.exit(1);
        }
    }

    boolean[][] E(int n) {
        boolean[][] e = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            e[i][i] = true;
        }
        return e;
    }

    boolean[][] sum(boolean[][] a, boolean[][] b) {
        int n = a.length;
        boolean[][] c = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] || b[i][j];
            }
        }
        return c;
    }

    boolean[][] multiply(boolean[][] a, boolean[][] b) {
        int n = a.length;
        boolean[][] c = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] |= (a[i][k] && b[k][j]);
                }
            }
        }
        return c;
    }

    boolean[][] pow(boolean[][] a, int k) {
        if (k <= 2 * n && matrixArray[k] != null) {
            return matrixArray[k];
        }
        if (k % 2 == 0) {
            boolean[][] b = pow(a, k / 2);
            return multiply(b, b);
        } else {
            return multiply(a, pow(a, k - 1));
        }
    }

    boolean hasZeros(boolean[][] a) {
        int n = a.length;
        for (boolean[] booleans : a) {
            for (int j = 0; j < n; j++) {
                if (!booleans[j]) return true;
            }
        }
        return false;
    }

    boolean[][][] matrixArray;
    boolean[][] a;
    int n;

    void solve() throws IOException {
        n = readInt();
        a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = readInt() != 0;
            }
        }
        matrixArray = new boolean[2 * n + 1][n][n];
        matrixArray[0] = E(n);
        for (int i = 1; i <= 2 * n; i++) {
            matrixArray[i] = multiply(a, matrixArray[i - 1]);
        }
        boolean[][] s = new boolean[n][n];
        for (int i = 0; i <= 2 * n; i++) {
            s = sum(s, matrixArray[i]);
        }
        boolean[][] t = pow(a, n * (n - 1));
        boolean[][] result = multiply(s, t);

        out.println(hasZeros(result) ? "No" : "Yes");
    }

}