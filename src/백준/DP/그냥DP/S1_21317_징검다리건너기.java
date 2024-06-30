package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S1_21317_징검다리건너기
 * DP
 */
public class S1_21317_징검다리건너기 {
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
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][3];
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][2]; // [][0] : 아주 큰 점프 사용 안한 경우, [][1] :  사용한 경우
        dp[0][0] = (int) 1e9;
        dp[0][1] = (int) 1e9;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = dp[i - 1][j] + arr[i - 1][1];
                dp[i][j] = Math.min(dp[i][j], dp[i - 2][j] + arr[i - 2][2]);
            }
            if(i>3) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 3][0] + K);
            }
        }
        return Math.min(dp[N][0], dp[N][1]);
    }
}