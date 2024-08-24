package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S2 1965 상자넣기
 * DP
 */
public class S2_1965_상자넣기 {

    static int[] dp;
    static int[] boxes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new int[N];
        boxes = new int[N];
        for (int i = 0; i < N; i++) {
            boxes[i] = parseInt(st.nextToken());
        }
        br.close();
        dp[0] = 1;
        int ans =0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, count(i));
        }
        System.out.println(ans);
    }
    
    static int count(int now){
        if(dp[now]>0){
            return dp[now];
        }
        for (int prev = 0; prev < now; prev++) {
            if(boxes[now] > boxes[prev]) dp[now] = Math.max(dp[now], count(prev) + 1);
        }
        return dp[now] = Math.max(dp[now], 1);
    }
}
