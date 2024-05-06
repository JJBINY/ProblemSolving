package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 1600 말이 되고픈 원숭이
 * BFS
 */
public class G3_1600_말이되고픈원숭이 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int K = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = parseInt(st.nextToken());
        int H = parseInt(st.nextToken());
        int[][] arr = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(K, W, H, arr));
    }

    private static int bfs(int K, int W, int H, int[][] arr) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int[] hdr = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] hdc = {-1, 1, -2, 2, -2, 2, -1, 1};

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(0, 0, 0, 0));
        boolean[][][] visited = new boolean[H][W][K + 1];
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            State now = q.poll();
            int r = now.r;
            int c = now.c;
            int k = now.k;
            if(now.r == H -1 && now.c == W -1){
                return now.move;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=H || nc<0 || nc>=W) continue;
                if(arr[nr][nc] == 1 ) continue;
                if (visited[nr][nc][k]) continue;
                visited[nr][nc][k] = true;
                q.offer(new State(nr, nc, k, now.move + 1));
            }
            if(k >=K) continue;
            for (int i = 0; i < 8; i++) {
                int nr = r + hdr[i];
                int nc = c + hdc[i];
                if(nr<0||nr>=H || nc<0 || nc>=W) continue;
                if(arr[nr][nc] == 1 ) continue;
                if (visited[nr][nc][k+1]) continue;
                visited[nr][nc][k+1] = true;
                q.offer(new State(nr, nc, k+1, now.move + 1));
            }
        } // while

        return -1;
    }

    static class State{
        int r;
        int c;
        int k;
        int move;

        public State(int r, int c, int k, int move) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.move = move;
        }
    }
}