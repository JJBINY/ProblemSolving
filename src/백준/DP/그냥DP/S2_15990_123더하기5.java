package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

/**
 * S2 15990 123더하기5
 * DP
 */
public class S2_15990_123더하기5 {
    /*
    1 : 1
    2 : 2
    3 : 21,
        12,
         3
    4 : 31 121,
        13
    5 : 131,
        212 32,
         23
    6 : 231 2121 321,
        132 312 1212,
        123 213
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        long[][] dp = new long[100001][4];
        long mod = 1_000_000_009;
        // 순서 존재
        // 같은수 연속 사용 X
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = dp[3][2] = dp[3][3] = 1;

        for (int i = 4; i < dp.length; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3])%mod;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3])%mod;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2])%mod;
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = parseInt(br.readLine());
            sb.append(Arrays.stream(dp[n]).sum() % mod).append("\n");
        }
        System.out.println(sb.toString());
    }
}