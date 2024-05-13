package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S3 14501 퇴사
 * DP
 */
public class S3_14501_퇴사 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int N = parseInt(br.readLine());
        int[] T = new int[N];
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = parseInt(st.nextToken());
            P[i] = parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
            if(i+T[i]<=N) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }
        return dp[N];
    }

}