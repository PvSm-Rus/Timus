import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1510 implements Runnable {

    StreamTokenizer tok;
    BufferedReader in;
    PrintWriter out;
    boolean timus = System.getProperty("Hi") != null;

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
        new Thread(new task_1510()).start();
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
        int n = readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
        }
        Arrays.sort(a);
        out.println(a[n / 2]);
    }

}
