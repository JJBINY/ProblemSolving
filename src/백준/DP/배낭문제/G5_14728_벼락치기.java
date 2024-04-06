package 백준.DP.배낭문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 14728 벼락치기
 * DP, 배낭문제
 */
public class G5_14728_벼락치기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int T = parseInt(st.nextToken());

        int[][] dp = new int[N + 1][T + 1];
        int[] times = new int[N + 1];
        int[] scores = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = parseInt(st.nextToken());
            scores[i] = parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int t = 1; t <= T; t++) {
                dp[i][t] = dp[i - 1][t];
                if (t >= times[i]) {
                    dp[i][t] = Math.max(dp[i][t], dp[i-1][t - times[i]] + scores[i]);
                }
            }
        }
        System.out.println(dp[N][T]);
    }

}