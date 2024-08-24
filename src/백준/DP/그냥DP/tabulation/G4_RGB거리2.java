package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.min;

/**
 * G4 도시 분할 계획
 * https://www.acmicpc.net/problem/1647
 */
public class G4_RGB거리2 {

    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n][3][3]; //n번째집, 시작색, 현재색
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dp[0][i], 1001);
            dp[0][i][i] = costs[0][i];
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i + 1][j][0] = min(dp[i][j][1], dp[i][j][2]) + costs[i + 1][0];
                dp[i + 1][j][1] = min(dp[i][j][0], dp[i][j][2]) + costs[i + 1][1];
                dp[i + 1][j][2] = min(dp[i][j][0], dp[i][j][1]) + costs[i + 1][2];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                ans = min(ans, dp[n-1][i][j]);
            }
        }

        System.out.println(ans);
    }
}