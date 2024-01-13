package 백준.DP.비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * G1 그림교환
 * https://www.acmicpc.net/problem/1029
 */
public class G1_그림교환 {

    static int[][][] dp = new int[15][10][1 << 15];
    static int[][] costs = new int[15][15];
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            costs[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(getCost(0, 0, 1));

    }

    static int cnt;

    static int getCost(int now, int cost, int state) {

        if (dp[now][cost][state] > 0) {
            return dp[now][cost][state];
        }

        dp[now][cost][state] = 1;
        for (int i = 0; i < n; i++) {
            if (costs[now][i] < cost || isVisited(state, i)) {
                continue;
            }
            dp[now][cost][state] = Math.max(dp[now][cost][state],
                    getCost(i, costs[now][i], state | (1 << i)) + 1);
        }
        return dp[now][cost][state];
    }

    private static boolean isVisited(int state, int i) {
        return ((state >> i) & 1) == 1;
    }


}
