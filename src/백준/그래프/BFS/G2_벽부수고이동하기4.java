package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G2 벽 부수고 이동하기 4
 * https://www.acmicpc.net/problem/16946
 */
public class G2_벽부수고이동하기4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


//        int[][] arr = new int[n][m];
        char[][] arr = new char[n][m];
        List<Pair> walls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
//            String[] split = br.readLine().split("");
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '1') {
                    walls.add(new Pair(i, j));
                }
            }
        }

        int[][] check = new int[n][m];

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        boolean[][] visited = new boolean[n][m];
        Map<Integer, Integer> groups = new HashMap<>();
        int group = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == '1') continue;
                Queue<Pair> queue = new LinkedList<>();
                queue.add(new Pair(i, j));
                visited[i][j] = true;
                int cnt = 0;
                while (!queue.isEmpty()) {
                    Pair now = queue.poll();
                    check[now.a][now.b] = group;
                    cnt += 1;
                    for (int k = 0; k < 4; k++) {
                        int nr = now.a + dr[k];
                        int nc = now.b + dc[k];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if (arr[nr][nc] == '1') continue;
                        if (visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        queue.add(new Pair(nr, nc));
                    }
                }
                groups.put(group++, cnt);
            }
        }

        int[][] ans = new int[n][m];

        for (Pair wall : walls) {
            int cnt = 1;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                int nr = wall.a + dr[i];
                int nc = wall.b + dc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (arr[nr][nc] == '1') continue;
                if (set.contains(check[nr][nc])) continue;
                set.add(check[nr][nc]);
                cnt += groups.get(check[nr][nc]);
            }
            ans[wall.a][wall.b] = cnt % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}