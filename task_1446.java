
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1446 implements Runnable {

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
        new Thread(new task_1446()).start();
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

    HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String name = in.readLine();
            String fac = in.readLine();
            if (!map.containsKey(fac)) {
                LinkedList<String> list = new LinkedList<String>();
                list.add(name);
                map.put(fac, list);
            } else {
                LinkedList<String> list = map.get(fac);
                list.add(name);
            }
        }
        print("Slytherin");
        out.println();
        print("Hufflepuff");
        out.println();
        print("Gryffindor");
        out.println();
        print("Ravenclaw");
    }

    void print(String fac) {
        out.println(fac + ":");
        for (String s : map.get(fac)) {
            out.println(s);
        }
    }

}
