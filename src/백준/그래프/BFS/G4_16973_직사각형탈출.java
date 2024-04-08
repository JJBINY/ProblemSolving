package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 16937 직사각형 탈출
 * BFS, 누적합
 */
public class G4_16973_직사각형탈출 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M, H, W, sr, sc, fr, fc;
    static int[][] arr, pSumsR, pSumsC;

    static void solve(BufferedReader br) throws IOException {
        input(br);
        initPrefixSums();
        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<Rectangle> q = new LinkedList<>();
        visited[sr][sc] = true;
        q.offer(new Rectangle(sr, sc, 0));
        while (!q.isEmpty()){
            Rectangle now = q.poll();

            if(now.r == fr && now.c == fc){
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int nr2 = nr + H-1;
                int nc2 = nc + W-1;

                if(nr<=0||nc<=0||nr2>N||nc2>M) continue;
                if(visited[nr][nc]) continue;
                if (!check(nr, nc, nr2, nc2)) continue;
                visited[nr][nc] = true;
                q.offer(new Rectangle(nr, nc, now.cnt + 1));
            }
        }
        return -1;
    }
    static boolean check(int r1, int c1, int r2, int c2){
        if(pSumsR[r2][c1] - pSumsR[r1-1][c1] >0) return false;
        if(pSumsR[r2][c2] - pSumsR[r1-1][c2] >0) return false;
        if(pSumsC[r1][c2] - pSumsC[r1][c1-1] >0) return false;
        if(pSumsC[r2][c2] - pSumsC[r2][c1-1] >0) return false;
        return true;
    }

    private static void initPrefixSums() {
        pSumsR = new int[N + 1][M + 1];
        pSumsC = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                pSumsR[i][j] = arr[i][j] + pSumsR[i - 1][j];
                pSumsC[i][j] = arr[i][j] + pSumsC[i][j - 1];
            }
        }
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = parseInt(st.nextToken());
        W = parseInt(st.nextToken());
        sr = parseInt(st.nextToken());
        sc = parseInt(st.nextToken());
        fr = parseInt(st.nextToken());
        fc = parseInt(st.nextToken());
    }

    static class Rectangle{
        int r;
        int c;
        int cnt;

        public Rectangle(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

}