package SWEA.DP.배낭문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
/**
 * 5215. 햄버거 다이어트 D3
 * DP,배낭문제
 */
public class D3_5215_햄버거다이어트 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = parseInt(br.readLine());
            //			int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    static int N,L;

    public static Object solve(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        L = parseInt(st.nextToken());
        int[] scores = new int[N+1];
        int[] cal = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            scores[i] = parseInt(st.nextToken());
            cal[i] = parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][L+1];
        for (int n = 1; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                dp[n][l] = dp[n-1][l];
                if(l>=cal[n]) {
                    dp[n][l] = Math.max(dp[n][l], dp[n-1][l-cal[n]] + scores[n]);
                }
            } // for L
        } // for N
        return dp[N][L];
    }
}
