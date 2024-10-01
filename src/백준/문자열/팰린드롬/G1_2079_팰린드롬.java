package 백준.문자열.팰린드롬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G1_2079_팰린드롬
 * DP
 */
public class G1_2079_팰린드롬 {
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
        char[] sequence = br.readLine().toCharArray();
        int n = sequence.length;
        boolean[][] dp = new boolean[n + 1][n + 1]; // dp[i][j] : i부터 길이j인 부분문자열이 팰린드롬인지

        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = true;
            if (i < n && sequence[i - 1] == sequence[i]) {
                dp[i][i + 1] = true;
            }
        }

        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; i + j - 1 < n + 1; j++) {
                int l = j;
                int r = i + j - 1;
                if (sequence[l - 1] == sequence[r - 1]) {
                    dp[l][r] = dp[l + 1][r - 1];
                }
            }
        }

        int[] ans = new int[n + 1];
        for (int r = 1; r < n + 1; r++) {
            ans[r] = Integer.MAX_VALUE;
            for (int l = 1; l < r + 1; l++) {
                if (dp[l][r]) {
                    ans[r] = Math.min(ans[r], ans[l - 1] + 1);
                }
            }
        }

        return ans[n];
    }
}