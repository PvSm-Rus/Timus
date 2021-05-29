import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.*;



public class task_1777 implements Runnable {
    StringTokenizer tok = null;
    BufferedReader in;
    PrintWriter out;
    boolean timus = System.getProperty("Hi") != null;

    void init() throws IOException {
        if (timus) {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        } else {
            in = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter("output.txt");
        }
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    String readString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    public static void main(String[] args) {
        new Thread(new task_1777()).start();
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

    ArrayList<Long> a = new ArrayList<Long>();

    boolean check() {
        Collections.sort(a);
        int n = a.size();
        for (int i = 1; i < n; i++) {
            if (a.get(i - 1).equals(a.get(i))) return true;
        }
        return false;
    }

    void solve() throws IOException {
        for (int i = 0; i < 3; i++) {
            a.add(readLong());
        }
        int ans = 1;
        while (!check()) {
            long min = Long.MAX_VALUE;
            int n = a.size();
            for (int i = 1; i < n; i++) {
                min = min(min, a.get(i) - a.get(i - 1));
            }
            a.add(min);
            ans++;
        }
        out.println(ans);
    }

}
