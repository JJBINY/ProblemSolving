package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G4_17069_파이프_옮기기_2
 * DP
 */
public class G4_17069_파이프_옮기기_2 {
    static int[][] arr;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1L; // init
                }
            }
        }
        dp[0][1][0] = 1L;
        long ans = func(N - 1, N - 1, 0) + func(N - 1, N - 1, 1) + func(N - 1, N - 1, 2);

        System.out.println(ans);
    }

    static long func(int r, int c, int d) {
        if (r < 0 || c < 0) {
            return 0L;
        }
        if (dp[r][c][d] > -1) {
            return dp[r][c][d];
        }

//		System.out.println(String.format("from %d %d %d, %d",r,c,d,dp[r][c][d]));

        dp[r][c][d] = 0;
        if (arr[r][c] > 0) {
            return 0L;
        }

        if (d == 0) {
            dp[r][c][d] = func(r, c - 1, 0) + func(r, c - 1, 1);
        } else if (d == 1) {
            if (r < 1 || c < 2) {
                return 0;
            } else if (arr[r - 1][c] > 0 || arr[r][c - 1] > 0) {
                return 0;
            }
            dp[r][c][d] = func(r - 1, c - 1, 0) + func(r - 1, c - 1, 1) + func(r - 1, c - 1, 2);
        } else if (d == 2) {
            dp[r][c][d] = func(r - 1, c, 1) + func(r - 1, c, 2);
        }

//		System.out.println(String.format("to %d %d %d, %d",r,c,d,dp[r][c][d]));
        return dp[r][c][d];
    }
}

