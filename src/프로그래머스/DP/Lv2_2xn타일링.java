package 프로그래머스.DP;

public class Lv2_2xn타일링 {
    //dp
    public int solution(int n) {

        if (n == 1) {
            return 1;
        }

        int mod = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }
}
