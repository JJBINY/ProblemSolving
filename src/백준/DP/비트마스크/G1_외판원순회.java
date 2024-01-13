package 백준.DP.비트마스크;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * G1 외판원순회 tabulation말고 dfs+memoization쓰면 좀 더 쉬운듯
 * https://www.acmicpc.net/problem/2098
 */
public class G1_외판원순회 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] weight = new int[n][n];
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                weight[i][j] = scanner.nextInt();
                if(weight[i][j] == 0) weight[i][j] = 20000000;
            }
        }

        int numOfStates = (int) Math.pow(2, n);
        int[][][] dp = new int[n][n][numOfStates]; //방문순서,도시,상태

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], 20000000);
            }
        }
        dp[0][0][1] = 0;

        for (int i = 1; i < n; i++) { //방문순서
            for (int now = 1; now < n; now++) {
                for (int prev = 0; prev < n; prev++) {
                    if (now == prev) continue;
                    for (int prevState = 1; prevState < numOfStates; prevState++) {
                        if(((prevState>>now)&1) == 1 ) continue; //현재 도시를 이미 방문한 상태

                        //현재 도시를 방문하지 않은 상태
                        int nowState = prevState | 1 << now;
                        dp[i][now][nowState] = Math.min(dp[i][now][nowState],
                                dp[i - 1][prev][prevState] + weight[prev][now]);
                    }
                }
            }
        }

        int ans = 20000000;
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, dp[n - 1][i][numOfStates-1] + weight[i][0]);
        }
        System.out.println(ans);
    }

}