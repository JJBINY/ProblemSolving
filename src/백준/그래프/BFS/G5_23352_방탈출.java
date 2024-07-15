package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5_23352_방탈출
 * BFS, 브루트포스
 */
public class G5_23352_방탈출 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M, len, result;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) continue;
                bfs(i, j);
            }
        }
        return result;
    }

    private static void bfs(int sr, int sc) {
        int val = arr[sr][sc];

        Deque<Piece> dq = new ArrayDeque();
        dq.offer(new Piece(sr, sc, 1));

        boolean[][] visited = new boolean[N][M];
        visited[sr][sc] = true;

        while (!dq.isEmpty()) {
            Piece now = dq.poll();
            if (now.len >= len) {
                int res = val + arr[now.r][now.c];

                if (now.len == len)
                    result = Math.max(result, res);
                else {
                    result = res;
                    len = now.len;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (arr[nr][nc] == 0) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                dq.offer(new Piece(nr, nc, now.len + 1));
            }
        }
    }

    static class Piece {
        int r;
        int c;
        int len;

        public Piece(int r, int c, int len) {
            this.r = r;
            this.c = c;
            this.len = len;
        }
    }
}