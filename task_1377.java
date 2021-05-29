import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

public class task_1377 implements Runnable {

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
        new Thread(new task_1377()).start();
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

    int n, m;
    boolean[][] b;
    int x1, y1, x2, y2;
    final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;
    int lastDirection;

    int makeMove(int x, int y, int direction) {
        lastDirection = direction;
        b[x][y] = true;
        if ((x == x1 && y == y1) || (x == x2 && y == y2)) {
            return 0;
        }
        switch (lastDirection) {
            case RIGHT -> {
                if (y + 1 < m && !b[x][y + 1]) return 1 + makeMove(x, y + 1, RIGHT);
                if (x + 1 < n && !b[x + 1][y]) return 1 + makeMove(x + 1, y, DOWN);
                if (y - 1 >= 0 && !b[x][y - 1]) return 1 + makeMove(x, y - 1, LEFT);
                if (x - 1 >= 0 && !b[x - 1][y]) return 1 + makeMove(x - 1, y, UP);
            }
            case DOWN -> {
                if (x + 1 < n && !b[x + 1][y]) return 1 + makeMove(x + 1, y, DOWN);
                if (y - 1 >= 0 && !b[x][y - 1]) return 1 + makeMove(x, y - 1, LEFT);
                if (x - 1 >= 0 && !b[x - 1][y]) return 1 + makeMove(x - 1, y, UP);
                if (y + 1 < m && !b[x][y + 1]) return 1 + makeMove(x, y + 1, RIGHT);
            }
            case LEFT -> {
                if (y - 1 >= 0 && !b[x][y - 1]) return 1 + makeMove(x, y - 1, LEFT);
                if (x - 1 >= 0 && !b[x - 1][y]) return 1 + makeMove(x - 1, y, UP);
                if (y + 1 < m && !b[x][y + 1]) return 1 + makeMove(x, y + 1, RIGHT);
                if (x + 1 < n && !b[x + 1][y]) return 1 + makeMove(x + 1, y, DOWN);
            }
            case UP -> {
                if (x - 1 >= 0 && !b[x - 1][y]) return 1 + makeMove(x - 1, y, UP);
                if (y + 1 < m && !b[x][y + 1]) return 1 + makeMove(x, y + 1, RIGHT);
                if (x + 1 < n && !b[x + 1][y]) return 1 + makeMove(x + 1, y, DOWN);
                if (y - 1 >= 0 && !b[x][y - 1]) return 1 + makeMove(x, y - 1, LEFT);
            }
        }
        return 0;
    }

    void solve() throws IOException {
        n = readInt();
        m = readInt();
        b = new boolean[n][m];
        x1 = readInt() - 1;
        y1 = readInt() - 1;
        x2 = readInt() - 1;
        y2 = readInt() - 1;
        makeMove(0, 0, RIGHT);
        int x, y;
        if (b[x1][y1]) {
            x = x1;  y = y1;
            x1 = -1; y1 = -1;
        } else {
            x = x2;	 y = y2;
            x2 = -1; y2 = -1;
        }
        int ans = makeMove(x, y, lastDirection);
        out.println(ans);
    }

}
