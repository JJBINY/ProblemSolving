package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 3114 사과와 바나나
 * DP, 누적합
 */
public class G2_3114_사과와바나나 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[][] dp, apples, bananas;
    static int R, C;

    static void solve(BufferedReader br) throws IOException {
        //input
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        apples = new int[R + 1][C + 1];
        bananas = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                String tree = st.nextToken();
                if (tree.charAt(0) == 'A') {
                    apples[i][j] = parseInt(tree.substring(1));
                } else {
                    bananas[i][j] = parseInt(tree.substring(1));
                }

                apples[i][j] += apples[i-1][j];
                bananas[i][j] += bananas[i-1][j];
            }
        }

        //solution
        dp = new int[R + 1][C + 1];
        for (int i = 0; i <= R; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[1][1] = apples[R][1]-apples[1][1];

        System.out.println(memoization(R, C));
//
//        for (int i = 0; i <= R; i++) {
//            for (int j = 0; j <= C; j++) {
//                System.out.print(dp[i][j]+ " ");
//            }
//            System.out.println();
//        }

    }

    static int memoization(int r, int c) {
        if (r * c == 0) return 0;
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        int underApple = apples[R][c] - apples[r][c];
        int overBanana = bananas[r - 1][c];
        int nowApple = apples[r][c] - apples[r - 1][c];
        if(c-1>0) {
            // 오른쪽 이동
            dp[r][c] = Math.max(dp[r][c], memoization(r, c - 1) + underApple + overBanana); // + 아래 사과 + 위 바나나
            //대각선 이동
            dp[r][c] = Math.max(dp[r][c], memoization(r - 1, c - 1) + underApple + overBanana);// + 아래 사과 + 위 바나나
        }
        // 아래 이동
        dp[r][c] = Math.max(dp[r][c], memoization(r - 1, c) - nowApple); // - 현재 사과

        return dp[r][c];
    }

}