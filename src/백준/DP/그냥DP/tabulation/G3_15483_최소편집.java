package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G3_15483_최소편집
 * DP, 편집거리, 레벤슈타인 거리
 */
public class G3_15483_최소편집 {

    static Object solve(BufferedReader br) throws IOException {
        String A = br.readLine();
        String B = br.readLine();

        int n = A.length();
        int m = B.length();

        int[][] dp = new int[n + 1][m + 1];

        // init
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j],
                                    dp[i - 1][j - 1]));
                }
            }
        }

        return dp[n][m];
    }


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
}