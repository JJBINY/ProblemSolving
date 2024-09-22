package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5_13398_연속합_2
 * DP
 */
public class G5_13398_연속합_2 {
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

    static Object solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[n][2]; // 0: 제거 안한 경우, 1: 제거 한 경우
        dp[0][0] = arr[0];
        int ans = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            // {숫자 하나를 제거한 경우의 최대}와 {i번째를 제거한 경우} 중 최대를 구함
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }

        return ans;
    }
}