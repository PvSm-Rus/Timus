import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1352 implements Runnable {

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
        new Thread(new task_1352()).start();
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

    void solve() throws IOException {
        final int[] a = { -1,
                2, 3, 5, 7, 13,
                17, 19, 31, 61, 89,

                107, 127, 521, 607, 1279,
                2203, 2281, 3217, 4253, 4423,

                9689, 9941, 11213, 19937, 21701,
                23209, 44497, 86243, 110503, 132049,

                216091, 756839, 859433, 1257787, 1398269,
                2976221, 3021377, 6972593
        };
        int n = readInt();
        for (int i = 0; i < n; i++) {
            out.println(a[readInt()]);
        }
    }

}
