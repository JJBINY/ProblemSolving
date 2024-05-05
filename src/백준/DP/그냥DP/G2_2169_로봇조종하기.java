package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 2169 로봇 조종하기
 * DP
 */
public class G2_2169_로봇조종하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M;
    static int[][] arr;
    static int[][][] dp;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0};
    static int[] dc = {1, 0, -1};
    static int INF = 1_000_000;

    static void solve(BufferedReader br) throws IOException {
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

        dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -INF);
            }
        }
        visited = new boolean[N][M];
        System.out.println(func(0, 0, 0));
    }
    static int func(int r, int c, int d){
        if(r == N-1 && c == M-1){
            return arr[r][c];
        }
        if (dp[r][c][d] > -INF) {
            return dp[r][c][d];
        }

        visited[r][c] = true;
        for (int nd = 0; nd < 3; nd++) {
            int nr = r + dr[nd];
            int nc = c + dc[nd];
            if(nr<0||nr>=N || nc<0 || nc>=M) continue;
            if(visited[nr][nc]) continue;
            dp[r][c][d] = Math.max(dp[r][c][d], func(nr, nc, nd) + arr[r][c]);
        }
        visited[r][c] = false;

        return dp[r][c][d];
    }
}