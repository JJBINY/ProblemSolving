package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S4_1049_기타줄
 * DP
 */
public class S4_1049_기타줄 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int[] arr = new int[M];
        int ea = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = parseInt(st.nextToken());
            ea = Math.min(ea, parseInt(st.nextToken()));
        }

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + ea;
            for (int j = 0; j < M; j++) {
                dp[i] = Math.min(dp[i], dp[Math.max(0,i - 6)] + arr[j]);
            }
        }
        return dp[N];
    }
}