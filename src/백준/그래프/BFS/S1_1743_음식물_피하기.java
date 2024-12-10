package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * S1_1743_음식물_피하기
 * 그래프, BFS
 */
public class S1_1743_음식물_피하기 {
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

    /*
    음식물 쓰레기 크기 => BFS or  DFS를 통해 한번에 탐색 가능한 가장 많은 위치 수
     */
    static int N, M, K;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static boolean[][] arr, visited;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            arr[r][c] = true;
        }

        // bfs
        int ans = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!arr[i][j]) continue;
                if (visited[i][j]) continue;
                visited[i][j] = true;
                int cnt = bfs(i, j);
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    private static int bfs(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int r = queue.peek()[0];
            int c = queue.pop()[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (!arr[nr][nc]) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
        return cnt;
    }
}