
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1493 implements Runnable {

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
        new Thread(new task_1493()).start();
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
        int p = readInt();
        if (p == 0 || p == 999999) {
            out.println("Yes");
            return;
        }
        String s1 = Integer.toString(p - 1);
        String s2 = Integer.toString(p + 1);
        int[] a1 = new int[6];
        int[] a2 = new int[6];
        for (int i = 0; i < s1.length(); i++) {
            a1[6 - s1.length() + i] += (s1.charAt(i) - '0');
        }
        for (int i = 0; i < s2.length(); i++) {
            a2[6 - s2.length() + i] += (s2.charAt(i) - '0');
        }
        if (lucky(a1) || lucky(a2)) {
            out.println("Yes");
        }
        else {
            out.println("No");
        }
    }

    boolean lucky(int[] a) {
        int s1 = a[0] + a[1] + a[2];
        int s2 = a[3] + a[4] + a[5];
        return s1 == s2;
    }

}