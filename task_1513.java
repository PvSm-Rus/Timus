import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1513 implements Runnable {

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
        new Thread(new task_1513()).start();
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

    final BigInteger TWO = BigInteger.valueOf(2);

    void solve() throws IOException {
        int n = readInt();
        int k = readInt();
        if (k == 0) {
            out.println(1);
            return;
        }
        BigInteger[] d = new BigInteger[n + k + 2];
        for (int i = 0; i < k; i++) {
            d[i] = BigInteger.ZERO;
        }
        d[k] = BigInteger.ONE;
        d[k + 1] = BigInteger.ONE;
        for (int i = k + 2; i <= 2 * k + 1; i++) {
            d[i] = d[i - 1].multiply(TWO);
        }
        BigInteger sumOfPrev = BigInteger.ZERO;
        for (int j = 2 * k + 1; j >= k + 1; j--) {
            sumOfPrev = sumOfPrev.add(d[j]);
        }
        for (int i = 2 * k + 2; i < n + k + 2; i++) {
            d[i] = sumOfPrev;
            sumOfPrev = sumOfPrev.subtract(d[i - k - 1]).add(d[i]);
        }
        out.println(d[n + k + 1]);
    }

}