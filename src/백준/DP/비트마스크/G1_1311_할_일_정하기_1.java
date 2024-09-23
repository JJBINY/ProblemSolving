package 백준.DP.비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G1_1311_할_일_정하기_1
 * DP, 비트마스킹
 */
public class G1_1311_할_일_정하기_1 {
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

    static int N, maxState;
    static int[][] costs;
    static int[][] dp;

    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxState = (1 << N) - 1;
        dp = new int[N][maxState + 1];
        return func(0, 0);
    }

    /**
     * @param p     사람
     * @param state 처리된 일의 상태
     * @return 이미 처리된 일의 상태가 state(비트필드)인 상황에서 p번째 사람이 일을 처리한 경우의 최솟값
     */
    private static int func(int p, int state) {
        if (p == N) {
            return 0;
        }else if (dp[p][state] > 0) {
            return dp[p][state];
        }

        dp[p][state] = Integer.MAX_VALUE;
        for (int task = 0; task < N; task++) {
            if ((state & 1 << task) > 0) continue;
            dp[p][state] = Math.min(dp[p][state],
                    func(p + 1, state | 1 << task) + costs[p][task]);
        }
        return dp[p][state];
    }

}