package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * P3_1126_같은_탑
 * DP
 */
public class P3_1126_같은_탑 {
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
        int N = Integer.parseInt(br.readLine());

        int[] h = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        if (N < 2) {
            return -1;
        }

        int maxH = Arrays.stream(h).sum();
        int res = -1;

        int[][] dp = new int[N + 1][maxH + 1]; // i, d
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= N; i++) { // i 번째 조각까지 고려했을 때,
            for (int d = 0; d <= maxH; d++) { // 두 탑의 높이 차가 d인 경우 처리
                // i번째 조각 사용 안 하는 경우
                dp[i][d] = Math.max(dp[i][d], dp[i - 1][d]);

                // i번째 조각을 더 낮은 탑에 쌓는 경우 (높이 역전 안됨)
                int diff = d + h[i]; // 차이가 작아짐 d+h[i] -> d
                if (diff <= maxH && dp[i - 1][diff] > -1) {
                    int highH = dp[i - 1][diff];
                    dp[i][d] = Math.max(dp[i][d], highH);
                }

                if (d >= h[i]) {
                    // i번째 조각을 더 높은 탑에 쌓는 경우
                    diff = d - h[i]; // 차이가 커짐 d-h[i] -> d
                    if (dp[i - 1][diff] > -1) {
                        int highH = dp[i - 1][diff];
                        dp[i][d] = Math.max(dp[i][d], highH + h[i]);
                    }
                } else {
                    // i번째 조각을 더 낮은 탑에 쌓는 경우2
                    // => 낮은 탑 높은 탑 높이가 역전되는 경우
                    diff = h[i] - d;
                    if (dp[i - 1][diff] > -1) {
                        int highH = dp[i - 1][diff];
                        int lowH = highH - diff;
                        dp[i][d] = Math.max(dp[i][d], lowH+h[i]);
                    }
                }
            } // for d
            res = Math.max(res, dp[i][0]);
        } // for i

        return res == 0 ? -1 : res;
    }
}

/*
input
3
1 2 1

answer
2
 */