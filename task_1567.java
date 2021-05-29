import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1567 implements Runnable {

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
        new Thread(new task_1567()).start();
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
        String s = in.readLine();
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('a', 1); map.put('b', 2); map.put('c', 3);
        map.put('d', 1); map.put('e', 2); map.put('f', 3);
        map.put('g', 1); map.put('h', 2); map.put('i', 3);
        map.put('j', 1); map.put('k', 2); map.put('l', 3);
        map.put('m', 1); map.put('n', 2); map.put('o', 3);
        map.put('p', 1); map.put('q', 2); map.put('r', 3);
        map.put('s', 1); map.put('t', 2); map.put('u', 3);
        map.put('v', 1); map.put('w', 2); map.put('x', 3);
        map.put('y', 1); map.put('z', 2);
        map.put('.', 1); map.put(',', 2); map.put('!', 3);
        map.put(' ', 1);
        int cost = 0;
        for (int i = 0; i < n; i++) {
            cost += map.get(s.charAt(i));
        }
        out.println(cost);
    }

}
