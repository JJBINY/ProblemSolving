package 백준.DP.배낭문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G4_1106_호텔
 * DP, 배낭문제
 */
public class G4_1106_호텔 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][2];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            arr[i][0] = cost;
            arr[i][1] = val;
        }

        int[] dp = new int[C + 100];
        Arrays.fill(dp, 1_000_000);
        dp[0] = 0;
        for (int n = 1; n < N + 1; n++) {
            int cost = arr[n][0];
            int val = arr[n][1];

            for (int c = val; c < C + 100; c++) {
                dp[c] = Math.min(dp[c], dp[c - val] + cost);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int c = C; c < C + 100; c++) {
            res = Math.min(res, dp[c]);
        }

        return res;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}