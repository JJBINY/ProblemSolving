package 백준.DP.배낭문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 3067 Coins
 * DP, 냅색
 */
public class G5_3067_Coins {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-- > 0) {
                solve(br);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[] coins = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            coins[i] = parseInt(st.nextToken());
        }
        int M = parseInt(br.readLine());

        int[] dp = new int[M + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = coins[i]; j <= M; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        sb.append(dp[M]).append("\n");
    }
}
