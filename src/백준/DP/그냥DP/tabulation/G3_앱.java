package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G3 앱
 * https://www.acmicpc.net/problem/7579
 */
public class G3_앱 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n + 1];
        int[] cost = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i + 1] = Integer.parseInt(st.nextToken());
            cost[i + 1] = Integer.parseInt(st2.nextToken());
        }

        int maxCost = Arrays.stream(cost).sum();
        int[][] dp = new int[n + 1][maxCost + 1];
        for (int i = 1; i < dp.length; i++) { // 1~i번 까지의 앱중에서 지울 앱 선택
            for (int j = 0; j < dp[0].length; j++) { //지운 앱의 비용 합이 j이하인 경우
                if(j < cost[i]){ // 이 경우 i번째 앱을 고려할 수 없음
                    dp[i][j]= dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
            }
        }

        while(maxCost>=0 && dp[n][maxCost] >= m){
            maxCost-=1;
        }

        System.out.println(maxCost+1);
    }

}