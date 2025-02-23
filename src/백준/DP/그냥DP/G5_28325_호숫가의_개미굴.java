package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 백준.DP.그냥DP.G5_28325_호숫가의_개미굴
 */
public class G5_28325_호숫가의_개미굴 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i + 1] = Long.parseLong(st.nextToken());
        }
        long[][][] dp = new long[2][N + 1][2]; // [1번이 큰방, 쪽방][n번째방][큰방, 쪽방]
        dp[0][1][0] = 1;
        dp[1][1][1] = arr[1];
        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 2; j++) {
                dp[j][i][0] = dp[j][i - 1][1] + 1;
                dp[j][i][1] = Math.max(dp[j][i - 1][0], dp[j][i - 1][1]) + arr[i];
            }
        }

        return Math.max(dp[1][N][1], Math.max(dp[0][N][1], dp[1][N][0]));
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