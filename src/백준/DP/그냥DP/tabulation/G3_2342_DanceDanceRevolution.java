package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G3_2342_DanceDanceRevolution
 */
public class G3_2342_DanceDanceRevolution {
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

    /*
    각 경로 별 드는 힘이 다름
        -> 가중치가 있는 최단 경로
        -> 다익스트라?
    but 양 발을 모두 고려해야 함
        -> dp
     */
    static Object solve(BufferedReader br) throws IOException {
        // 최대 10^5개의 입력
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = inputs.length - 1; // 마지막 입력 0

        int[][][] dp = new int[N][5][5]; // n번째 입력, L, R
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = 1_000_000;
                }
            }
        }

        dp[0][inputs[0]][0] = 2;
        dp[0][0][inputs[0]] = 2;
        for (int i = 0; i < N - 1; i++) {
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    // (i,l,r) 로부터 발 이동
                    int next = inputs[i + 1];

                    // 왼발 이동
                    if (r != next) {
                        dp[i + 1][next][r] = Math.min(
                                dp[i + 1][next][r],
                                dp[i][l][r] + getCost(l, next));
                    }

                    //오른발 이동
                    if (l != next) {
                        dp[i + 1][l][next] = Math.min(
                                dp[i + 1][l][next],
                                dp[i][l][r] + getCost(r, next));
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                ans = Math.min(ans, dp[N - 1][l][r]);
            }
        }
        return ans;
    }

    static int getCost(int from, int to) {
        int cost;
        if (from == to) {
            cost = 1;
        } else if (from == 0) {
            cost = 2;
        } else if ((from + to) % 2 != 0) { //두 합이 홀수면 인접
            cost = 3;
        } else {
            cost = 4;
        }
//        System.out.println(String.format("%d => %d then cost = %d", from, to, cost));
        return cost;
    }
}