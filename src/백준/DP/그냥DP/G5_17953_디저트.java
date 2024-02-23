package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G5 17953 디저트
 * DP
 */
public class G5_17953_디저트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());

            int[][] arr = new int[N][M];
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                for (int n = 0; n < N; n++) {
                    arr[n][m] = parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[N][M];
            for (int i = 0; i < M; i++) {
                dp[0][i] = arr[0][i];
            }

            for (int n = 1; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    dp[n][m] = dp[n - 1][m] + arr[n][m] / 2;
                    for (int i = 0; i < M; i++) {
                        if (i == m) continue;
                        dp[n][m] = Math.max(dp[n][m], dp[n - 1][i] + arr[n][m]);
                    }
                }
            }

            System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
