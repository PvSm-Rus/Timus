
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1355 implements Runnable {

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
        new Thread(new task_1355()).start();
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

    final int SIZE = (int) sqrt(1000 * 1000 * 1000) + 2;
    LinkedList<Integer> list = new LinkedList<Integer>();

    void calcPrimes() {
        boolean[] primes = new boolean[SIZE];
        Arrays.fill(primes, true);
        primes[0] = false; primes[1] = false;
        for (int i = 2; i < SIZE; i++) {
            if (primes[i]) {
                for (int k = i * i; k < SIZE; k += i) {
                    primes[k] = false;
                }
            }
        }
        for (int i = 2; i < SIZE; i++) {
            if (primes[i]) {
                list.add(i);
            }
        }
    }

    void solve() throws IOException {
        calcPrimes();
        int testCount = readInt();
        for (int test = 0; test < testCount; test++) {
            int a = readInt();
            int b = readInt();
            int ans = getAnswer(a, b);
            out.println(ans);
        }
    }

    int getAnswer(int a, int b) {
        if (b % a != 0) return 0;
        if (a > 1) return getAnswer(1, b / a);
        int ans = 1;
        Iterator<Integer> it = list.iterator();
        int p = it.next();
        int s = b;
        while(s > 1) {
            if (s % p == 0) {
                s /= p;
                ans++;
            } else {
                if (it.hasNext()) {
                    p = it.next();
                } else {
                    return ans + 1;
                }
            }
        }
        return ans;
    }

}
