package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S1 14716 현수막 10m
 * BFS
 */
public class S1_14716_현수막 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = parseInt(st.nextToken());
        int N = parseInt(st.nextToken());
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int ans = 0;

        boolean[][] visited = new boolean[M][N];
        int[] dr = new int[]{1, -1, 0, 0, 1, -1, -1, 1};
        int[] dc = new int[]{0, 0, -1, 1, 1, -1, 1, -1};
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == 0) continue;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i * N + j);
                visited[i][j] = true;
                int cnt = 0;
                while (!queue.isEmpty()) {
                    int r = queue.peek() / N;
                    int c = queue.poll() % N;
                    cnt++;
                    for (int k = 0; k < 8; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                        if (arr[nr][nc] == 0) continue;
                        if (visited[nr][nc]) continue;
                        visited[nr][nc] = true;

                        queue.offer(nr * N + nc);
                    }
                } //while

                if (cnt > 0) {
                    ans++;
                }
            } //bfs
        } //for

        System.out.println(ans);
    }
}