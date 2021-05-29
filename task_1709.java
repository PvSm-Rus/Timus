import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class task_1709 implements Runnable {

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

    long readLong() throws IOException {
        tok.nextToken();
        return (long) tok.nval;
    }

    String readString() throws IOException {
        tok.nextToken();
        return tok.sval;
    }

    public static void main(String[] args) {
        new Thread(new task_1709()).start();
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

    int n;
    int[][] graph;
    int[] color;
    long ans = 0;
    long a, d;
    final int ADDED = 2;
    final int DELETED = 3;

    void solve() throws IOException {
        n = readInt();
        graph = new int[n][n];
        d = readLong();
        a = readLong();
        in.readLine();
        for (int i = 0; i < n; i++) {
            String s = in.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = s.charAt(j) - '0';
            }
        }
        color = new int[n];
        int nextColor = 0;
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                dfs(i, ++nextColor);
            }
        }

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<Integer, TreeSet<Integer>>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(color[i])) {
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(i);
                map.put(color[i], set);
            } else {
                TreeSet<Integer> set = map.get(color[i]);
                set.add(i);
            }
        }
        Collection<TreeSet<Integer>> mapValues = map.values();
        Iterator<TreeSet<Integer>> it = mapValues.iterator();
        int from = it.next().first();
        while (it.hasNext()) {
            int to = it.next().first();
            graph[from][to] = ADDED;
            graph[to][from] = ADDED;
            ans += a;
        }

        for (int i = 0; i < n; i++) {
            color[i] = i;
        }
        long added = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] == 1) {
                    graph[i][j] = DELETED;
                    graph[j][i] = DELETED;
                    added += d;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((graph[i][j] > 1) &&
                        color[i] != color[j]) {
                    if (graph[i][j] == DELETED) {
                        graph[i][j] = 1;
                        graph[j][i] = 1;
                        added -= d;
                    }
                    int c1 = color[i], c2 = color[j];
                    for (int k = 0; k < n; k++) {
                        if (color[k] == c2) {
                            color[k] = c1;
                        }
                    }
                }
            }
        }

        ans += added;
        out.println(ans);
        printMatrix();
    }

    void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (graph[i][j]) {
                    case ADDED -> out.print('a');
                    case DELETED -> out.print('d');
                    default -> out.print(0);
                }
            }
            out.println();
        }
    }

    void dfs(int v, int c) {
        color[v] = c;
        for (int i = 0; i < n; i++) {
            if (graph[v][i] > 0 && color[i] == 0) {
                dfs(i, c);
            }
        }
    }

}
