package 프로그래머스.DP;

public class Lv2_멀리뛰기 {
    // dp
    public long solution(int n) {

        if (n == 1) {
            return 1;
        }
        long dp[] = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        int mod = 1234567;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }
}