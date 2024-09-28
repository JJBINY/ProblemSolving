package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * P4_18015_어려운_계단_수
 * DP
 */
public class P4_18015_어려운_계단_수 {
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

    static int N, B;
    static int mod = 1_000_000_000;
    static int[][][][] dp;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        dp = new int[N][B][2][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < B; j++) {
                Arrays.fill(dp[i][j][0], -1);
                Arrays.fill(dp[i][j][1], -1);
            }
        }
        int ans = 0;
        for (int i = 1; i < B; i++) {
            int e = (i == B - 1) ? 1 : 0;
            ans = (ans + func(0, i, 0, e)) % mod;
        }
        return ans;
    }

    static int func(int idx, int num, int s, int e) {
        if (dp[idx][num][s][e] > -1) return dp[idx][num][s][e];
        if (idx == N - 1) return s & e;
        if (e == 0 && N - idx < B - num) return dp[idx][num][s][e] = 0;
        if (s == 0 && N - idx < num) return dp[idx][num][s][e] = 0;

        int res = 0;

        if (num > 0) {
            res += func(idx + 1, num - 1, (num == 1 ? 1 : s), e);
            res %= mod;
        }

        if (num < B - 1) {
            res += func(idx + 1, num + 1, s, (num == (B - 2) ? 1 : e));
            res %= mod;
        }

        return dp[idx][num][s][e] = res;
    }
}