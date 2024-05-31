package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G1_16933_벽부수고이동하기3
 */
public class G1_16933_벽부수고이동하기3 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M, K;
    static int[] dr = {0, 0, -1, 1}, dc = {1, -1, 0, 0};
    static int[][] arr;
    static boolean[][][] visited;
    static Deque<Point> dq = new ArrayDeque<>();

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        dq.offer(new Point(0, 0, 0, 1));
        visited = new boolean[N][M][K + 1];
        visited[0][0][0] = true;

        while (!dq.isEmpty()) {
            Point now = dq.poll();
            if (now.r == N - 1 && now.c == M - 1) {
                return now.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (isWall(nr, nc)) {
                    int nk = now.k + 1;
                    if (nk > K) continue;
                    if (isDay(now)) {
                        if (visited[nr][nc][nk]) continue;
                        visited[nr][nc][nk] = true;
                        dq.offer(new Point(nr, nc, nk, now.dist + 1));
                    } else {
                        dq.offer(new Point(now.r, now.c, now.k, now.dist + 1));
                    }
                }else{
                    int nk = now.k;
                    if (visited[nr][nc][nk]) continue;
                    visited[nr][nc][nk] = true;
                    dq.offer(new Point(nr, nc, nk, now.dist + 1));
                }
            }
        }
        return -1;

    }

    private static boolean isWall(int nr, int nc) {
        return arr[nr][nc] == 1;
    }

    private static boolean isDay(Point now) {
        return now.dist % 2 == 1;
    }

    static class Point {
        int r;
        int c;
        int k;
        int dist;

        public Point(int r, int c, int k, int dist) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.dist = dist;
        }
    }
}