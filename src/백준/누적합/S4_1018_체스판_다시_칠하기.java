package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S4_1018_체스판_다시_칠하기
 * 누적합
 */
public class S4_1018_체스판_다시_칠하기 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                board[i][j] = c[j-1];

            }
        }

        return Math.min(getRes(N, M, board, 0)
                , getRes(N, M, board, 1));
    }

    static int getRes(int N, int M, char[][] board, int t) {
        int[][] dp = new int[N+1][M+1];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + getVal(i, j, board[i][j], t);
                if (i > 7 && j > 7) {
                    res = Math.min(dp[i][j] - dp[i-8][j] - dp[i][j-8] + dp[i - 8][j - 8], res);
                }
            }
        }
        return res;
    }

    static int getVal(int i, int j, char c, int t) {
        char[] targets = {'W', 'B'};
        return c == targets[(i + j + t) % 2] ? 0 : 1;
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