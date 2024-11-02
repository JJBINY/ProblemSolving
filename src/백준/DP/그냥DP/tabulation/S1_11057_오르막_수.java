package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S1_11057_오르막_수
 * DP
 */
public class S1_11057_오르막_수 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int mod = 10_007;
        int[][] dp = new int[N+1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j]+dp[i - 1][k])%mod;
                }
            }
        }

        return Arrays.stream(dp[N]).sum() % mod;
    }
}