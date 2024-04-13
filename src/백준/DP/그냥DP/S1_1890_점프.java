package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S1 1890 점프
 * DP
 */
public class S1_1890_점프 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;
    static int[][] arr;
    static long[][] dp;

    static void solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0 || arr[i][j] == 0) continue;
                int nr = i + arr[i][j];
                int nc = j + arr[i][j];
                if (nr < N) {
                    dp[nr][j] += dp[i][j];
                }
                if (nc < N) {
                    dp[i][nc] += dp[i][j];
                }
            } // for j
        } // for i

        System.out.println(dp[N - 1][N - 1]);
    }
}