package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G1 팰린드롬 분할
 * https://www.acmicpc.net/problem/1509
 */
public class G1_팰린드롬분할_R {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sequence = br.readLine().toCharArray();
        int n = sequence.length;
        boolean[][] dp = new boolean[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            dp[i][i] = true;
            if(i<n && sequence[i-1] == sequence[i]){
                dp[i][i + 1] = true;
            }
        }

        for (int i = 3; i < n+1; i++) {
            for (int j = 1; i+j-1 < n+1; j++) {
                int l = j;
                int r = i + j-1;
                if(sequence[l-1] == sequence[r-1]){
                    dp[l][r] = dp[l + 1][r - 1];
                }
            }
        }

//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < n+1; i++) {
//            for (int j = 1; j < n+1; j++) {
//                if(dp[i][j]) sb.append(1);
//                else sb.append(0);
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
        int[] ans = new int[n+1];
        for (int r = 1; r < n+1; r++) {
            ans[r] = Integer.MAX_VALUE;
            for (int l = 1; l < r+1; l++) {
                if(dp[l][r]){
                    ans[r] = Math.min(ans[r], ans[l - 1] + 1);
                }
            }
        }

        System.out.println(ans[n]);
    }
}