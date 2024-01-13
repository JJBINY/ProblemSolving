package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * G5_동전2
 * https://www.acmicpc.net/problem/2294
 */
public class G5_동전2 {

    /*
    1 5 12
    dp[15] = min(dp[14],dp[10],dp[3])+1
    dp[0] = 0
    dp[1] = dp[0]+1
    dp[2] = dp[1]+1
    dp[3] = dp[2]+1
    dp[4] = dp[3]+1
    dp[5] = min(dp[0],dp[4])+1
    dp[6] = ...

     */

    static Set<Integer> coins = new HashSet<>();
    static int[] dp;
    static int MAX_VALUE = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }
        dp = new int[k+1];
        Arrays.fill(dp, MAX_VALUE);

        dp[0] = 0;
        int result = getMinUsedCoinCnt(k);
        System.out.println(result < MAX_VALUE-1 ? result : -1);
    }

    static int getMinUsedCoinCnt(int k){
        if(dp[k]<MAX_VALUE){
            return dp[k];
        }

        for (int coin : coins) {
            if(k-coin>=0){
                dp[k] = Math.min(dp[k], getMinUsedCoinCnt(k - coin) + 1);
            }
        }
        if(dp[k] == MAX_VALUE){
            dp[k] = MAX_VALUE - 1;
        }
        return dp[k];
    }
}
