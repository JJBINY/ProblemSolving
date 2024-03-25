package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G2 2208 보석줍기
 * 누적합, DP
 */
public class G2_2208_보석줍기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] pSum = new int[N+1];

        int[] dp = new int[N+1];
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            pSum[i] = Integer.parseInt(br.readLine());
            pSum[i] += pSum[i - 1];
            dp[i] = Math.min(dp[i-1], pSum[i]);
            if(i>=M){
                ans = Math.max(ans, pSum[i]);
                ans = Math.max(ans, pSum[i] - dp[i - M]);
            }
        }

        System.out.println(ans);
    }
}