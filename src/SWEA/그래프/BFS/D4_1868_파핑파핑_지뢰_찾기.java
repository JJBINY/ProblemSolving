package SWEA.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;


/**
 * D4_1868_파핑파핑_지뢰_찾기
 * BFS
 */
public class D4_1868_파핑파핑_지뢰_찾기 {

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, click;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dc = {0, 0, -1, 1, 1, -1, 1, -1};

    static Object solve(BufferedReader br) throws IOException {
        init(br);

        //click zero
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (!isZero(i, j)) continue;
                // bfs
                bfs(i, j);
                click++;
            }
        }

        // click remainder
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if(isBomb(i,j)) continue;
                click++;
            }
        }

        return click;
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        click = 0;
        visited = new boolean[N][N];
    }

    private static void bfs(int r, int c) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(r, c));
        visited[r][c] = true;

        while (!dq.isEmpty()) {
            r = dq.peek().r;
            c = dq.poll().c;

            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isOutOfBound(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if (isZero(nr, nc)) {
                    dq.offer(new Point(nr, nc));
                }
            } // for i
        } // while
    }

    private static boolean isZero(int r, int c) {
        if (isBomb(r, c)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOutOfBound(nr, nc)) continue;
            if (isBomb(nr, nc)) {
                return false;
            }
        }

        return true;
    }

    static boolean isBomb(int r, int c) {
        return arr[r][c] == '*';
    }

    private static boolean isOutOfBound(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= N;
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

/*
02X20
13X31
2X32X
3X311
2X2OO
 */