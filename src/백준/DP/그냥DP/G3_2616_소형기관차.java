package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G3 2616 소형기관차
 * dp, 누적합
 */
public class G3_2616_소형기관차 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {

        int n = Integer.parseInt(br.readLine()); //총 객차 수
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine()); // 최대 끌 수 있는 객차 수
        int[] pSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pSum[i] += pSum[i - 1] + arr[i-1];
        }
        int[][] dp = new int[4][n+1]; // 소형기관차 i대를 운용하여 j번째 객차 까지 끄는 경우 최대값

        for (int i = 1; i <= 3; i++) {
            for (int j = m; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + pSum[j] - pSum[j - m]);
            }
        }
        System.out.println(dp[3][n]);
    }
}