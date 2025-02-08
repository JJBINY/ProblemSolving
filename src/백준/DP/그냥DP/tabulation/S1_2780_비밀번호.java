package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S1_2780_비밀번호
 * DP
 */
public class S1_2780_비밀번호 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][10];
        int mod = 1234567;
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (isAdjacent(j, k)) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                    }
                }
            }
        }
        return Arrays.stream(dp[N - 1]).reduce(0, (a, b) -> (a + b) % mod);
    }

    static boolean isAdjacent(int a, int b) {
        if (a % 3 == b % 3 && Math.abs(a - b) == 3) {
            return true;
        } else if (a / 3 == b / 3 && Math.abs(a - b) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
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