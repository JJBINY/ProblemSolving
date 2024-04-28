package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 14442 벽부수고이동하기2
 * BFS
 */
public class G3_14442_벽부수고이동하기2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        // bfs
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][K+1];
        queue.offer(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int r = poll[0];
            int c = poll[1];
            int k = poll[2];
            int dist = poll[3];

            if(r == N-1 && c == M-1){
                System.out.println(dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0|| nr>=N || nc<0 || nc>=M) continue;
                int nk = k + (arr[nr][nc] == 1 ? 1 : 0);
                if(nk>K) continue;
                if(visited[nr][nc][nk]) continue;
                visited[nr][nc][nk] = true;
                queue.offer(new int[]{nr, nc, nk, dist + 1});
            }
        } // while

        System.out.println(-1);
    }
}