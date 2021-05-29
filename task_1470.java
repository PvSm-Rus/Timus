import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1470 implements Runnable {

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
        new Thread(new task_1470()).start();
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
        long[][][] t;
        int n;
        Tree(int n) {
            this.n = n;
            t = new long[n][n][n];
        }
        void add(int x, int y, int z, long value) {
            int i, j, k;
            for (i = x; i < n; i = i | (i + 1))
                for (j = y; j < n; j = j | (j + 1))
                    for (k = z; k < n; k = k | (k + 1))
                        t[i][j][k] += value;
        }
        long sum(int x, int y, int z) {
            long ans = 0;
            int i, j, k;
            for (i = x; i >= 0; i = (i & (i + 1)) - 1)
                for (j = y; j >= 0; j = (j & (j + 1)) - 1)
                    for (k = z; k >= 0; k = (k & (k + 1)) - 1)
                        ans += t[i][j][k];
            return ans;
        }
        long sum(int x1, int x2, int y1, int y2, int z1, int z2) {
            return sum(x2, y2, z2)
                    - sum(x1 - 1, y2, z2) - sum(x2, y1 - 1, z2) - sum(x2, y2, z1 - 1)
                    + sum(x1 - 1, y1 - 1, z2) + sum(x1 - 1, y2, z1 - 1) + sum(x2, y1 - 1, z1 - 1)
                    - sum(x1 - 1, y1 - 1, z1 - 1);
        }
    }

    void solve() throws IOException {
        int n = readInt();
        Tree tree = new Tree(n);
        int command = readInt();
        while (command != 3) {
            if (command == 1) {
                int x = readInt();
                int y = readInt();
                int z = readInt();
                int k = readInt();
                tree.add(x, y, z, k);
            } else
            if (command == 2) {
                int x1 = readInt();
                int y1 = readInt();
                int z1 = readInt();
                int x2 = readInt();
                int y2 = readInt();
                int z2 = readInt();
                long sum = tree.sum(x1, x2, y1, y2, z1, z2);
                out.println(sum);
            }
            command = readInt();
        }
    }

}
