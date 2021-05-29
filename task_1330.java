import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1330 implements Runnable {

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
        new Thread(new task_1330()).start();
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

    static class Tree {
        int[] t;
        int n;
        Tree(int[] a) {
            n = a.length;
            t = new int[n];
            for (int i = 0; i < n; i++) {
                add(i, a[i]);
            }
        }
        void add(int index, int value) {
            for (int i = index; i < n; i = i | (i + 1)) {
                t[i] += value;
            }
        }
        int sum(int right) {
            int ans = 0;
            for (int i = right; i >= 0; i = (i & (i + 1)) - 1) {
                ans += t[i];
            }
            return ans;
        }
        int sum(int left, int right) {
            return sum(right) - sum(left - 1);
        }
    }

    void solve() throws IOException {
        int n = readInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = readInt();
        }
        Tree tree = new Tree(a);
        int q = readInt();
        for (int i = 0; i < q; i++) {
            int left = readInt();
            int right = readInt();
            out.println(tree.sum(left, right));
        }
    }

}
