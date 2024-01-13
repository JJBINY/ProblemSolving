package 백준.DP.비트마스크;

import java.io.IOException;
import java.util.Scanner;

/**
 * G1 계단수
 * https://www.acmicpc.net/problem/1562
 */
public class G1_계단수 {

    public static void main(String[] args) throws IOException {
        int n = new Scanner(System.in).nextInt();
        int[][][] dp = new int[n][10][1024];
        for (int i = 1; i < 10; i++) {
            dp[0][i][1 << i] = 1;
        }

        int mod = 1000000000;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int prevState = 0; prevState < 1024; prevState++) {
                    int nowState = prevState | 1 << j;
                    if(j>0) {
                        dp[i][j][nowState] += dp[i - 1][j - 1][prevState];
                        dp[i][j][nowState] %= mod;
                    }
                    if(j<9) {
                        dp[i][j][nowState] += dp[i - 1][j + 1][prevState];
                        dp[i][j][nowState] %= mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[n-1][i][1023];
            ans %= mod;
        }
        System.out.println(ans);
    }

}