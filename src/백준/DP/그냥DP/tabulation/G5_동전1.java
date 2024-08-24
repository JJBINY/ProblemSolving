package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5_동전1
 * https://www.acmicpc.net/problem/2293
 */
public class G5_동전1 {

    /*
    1 2 5 ->
    d[0] = 1
    d[1] = 1
    d[2] = 1+1
    d[3] = 1+1
    d[4] = 1+2
    d[5] = 1+2+1
    d[6] = 1+3+1
    d[7] = 1+3+2
    d[8] = 1+4+2
    d[9] = 1+4+3
    d[10] = 1+5+4

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n+1];
        for (int i = 0; i < n; i++) {
            coin[i+1] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[k+1];

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
        System.out.println(dp[k]);
    }
}
