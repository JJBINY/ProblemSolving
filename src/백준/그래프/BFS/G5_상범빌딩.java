package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G5 상범 빌딩
 * https://www.acmicpc.net/problem/6593
 */
public class G5_상범빌딩 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = parseInt(st.nextToken());
            int R = parseInt(st.nextToken());
            int C = parseInt(st.nextToken());
            if (L * R * C == 0) break;

            Queue<Point> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[L][R][C];
            char[][][] arr = new char[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        arr[i][j][k] = line.charAt(k);
                        if (arr[i][j][k] == 'S') {
                            queue.add(new Point(i, j, k, 0));
                        }
                    }
                }
                br.readLine(); //공백 처리
            }

            int[] dl = new int[]{0, 0, 0, 0, 1, -1};
            int[] dr = new int[]{0, 0, 1, -1, 0, 0};
            int[] dc = new int[]{1, -1, 0, 0, 0, 0};
            int cnt = -1;
            while (!queue.isEmpty()) {
                Point now = queue.poll();
                for (int i = 0; i < 6; i++) {
                    int nl = now.l + dl[i];
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (arr[nl][nr][nc] == '#') continue;
                    if (visited[nl][nr][nc]) continue;
                    visited[nl][nr][nc] = true;

                    if (arr[nl][nr][nc] == 'E') {
                        cnt = now.cnt + 1;
                        break;
                    }
                    queue.add(new Point(nl, nr, nc, now.cnt + 1));
                }
            }

            if (cnt > -1) {
                ans.append(String.format("Escaped in %d minute(s).\n", cnt));
            } else {
                ans.append("Trapped!\n");
            }
        }
        System.out.println(ans);
    }

    static class Point {
        final int l;
        final int r;
        final int c;
        final int cnt;

        public Point(int l, int r, int c, int cnt) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

    }
}
