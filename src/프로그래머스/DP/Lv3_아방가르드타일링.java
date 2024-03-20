package 프로그래머스.DP;

public class Lv3_아방가르드타일링 {
    /**
     * DP, 누적합
     * <p>
     * n(x) : 세로가 3 가로가 x인 판에 타일을 채우는 경우의 수 // dp
     * k(x) : 세로가 3 가로가 x인 판에 세로로 쪼갤 수 없게 타일을 채우는 경우의 수
     * n(x) = k(1)n(x-1) + k(2)n(x-2) + k(3)n(x-3) + ... + k(x-1)n(1) + k(x)
     * <p>
     * k1 = 1
     * k2 = 2
     * k3 = 5
     * k4 = 2
     * k5 = 2
     * k6 = 4
     * k7 = 2
     * k8 = 2
     * k9 = 4
     * ...
     * n(x) = n(x-1) + 2n(x-2) + 5n(x-3) + {2n(x-4) + 2n(x-5) + 4n(x-6) + 224반복... }
     */
    public int solution(int n) {
        int mod = 1_000_000_007;


        long[] dp = new long[100_001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;

        // 224반복 누적합으로 처리
        long[] memo = new long[]{12, 2, 4}; // 3x; 3x+1; 3x+2

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2] + 5 * dp[i - 3] + memo[i % 3]) % mod;
            memo[i % 3] = (memo[i % 3] + 2 * dp[i - 1] + 2 * dp[i - 2] + 4 * dp[i - 3]) % mod;
        }

        return (int) dp[n];
    }
}