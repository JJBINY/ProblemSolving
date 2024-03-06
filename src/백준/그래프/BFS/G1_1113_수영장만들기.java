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
 * G1 1113 수영장 만들기
 * 구현, BFS
 */
public class G1_1113_수영장만들기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;
    static int[][] arr;
    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int ans = 0;
        for (int height = 2; height < 10; height++) {
            boolean[][] visited = new boolean[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (arr[i][j] >= height || visited[i][j]) continue;
                    ans += bfs(height, visited, i, j);
                }
            }

        }

        System.out.println(ans);
    }

    private static int bfs(int height, boolean[][] visited, int i, int j) {
        Queue<Integer> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.offer(i * M + j);

        int cnt = 1;
        boolean isGround = false;
        while (!queue.isEmpty()) {
            int r = queue.peek() / M;
            int c = queue.poll() % M;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if (arr[nr][nc] < height) {
                    if (nr * nc == 0 || nr == N - 1 || nc == M - 1) {
                        isGround = true;
                    }
                    queue.offer(nr * M + nc);
                    cnt++;
                }
            }
        }
        if (isGround) {
            cnt = 0;
        }
        return cnt;
    }
}