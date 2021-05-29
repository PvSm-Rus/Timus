
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1413 implements Runnable {

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
        new Thread(new task_1413()).start();
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

    final int R = 0, U = 1, UR = 2, UL = 3;
    int[] d = new int[4];

    void solve() throws IOException {
        String s = in.readLine();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case '1' -> d[UR]--;
                case '2' -> d[U]--;
                case '3' -> d[UL]--;
                case '4' -> d[R]--;
                case '6' -> d[R]++;
                case '7' -> d[UL]++;
                case '8' -> d[U]++;
                case '9' -> d[UR]++;
                case '0' -> {
                    writeAnswer();
                    return;
                }
            }
        }
        writeAnswer();
    }

    void writeAnswer() {
        double x = d[R] + (d[UR] - d[UL]) / sqrt(2);
        double y = d[U] + (d[UL] + d[UR]) / sqrt(2);
        out.printf(Locale.US, "%.10f %.10f", x, y);
    }

}
