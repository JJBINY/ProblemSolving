package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5_파이프옮기기_1
 * https://www.acmicpc.net/problem/17070
 * DP 말고 DFS, BFS로 구현도 가능할 듯?
 */
public class G5_파이프옮기기_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[][] board = setBoard(N);
        State[][] dp = setDPTable(N, board);

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (board[i][j] != 0) {
                    dp[i][j] = dp[0][0];
                    continue;
                }
                countWay(board, dp, i, j);
            }
        }

        System.out.println(dp[N - 1][N - 1].getThreeWaySum());
    }

    private static int[][] setBoard(int N) throws IOException {
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return board;
    }

    private static State[][] setDPTable(int N, int[][] board) {
        State[][] dp = new State[N][N];
        dp[0][1] = new State(1, 0, 0);
        dp[0][0] = new State(0, 0, 0);
        for (int i = 1; i < N; i++) {
            if (i != 1) {
                dp[0][i] = board[0][i] == 0 ? dp[0][i-1] : dp[0][0];
            }
            dp[i][0] = dp[0][0];
        }
        return dp;
    }

    private static void countWay(int[][] board, State[][] dp, int i, int j) {
        int diagonal;
        int horizontal;
        int vertical;
        vertical = dp[i - 1][j].vertical + dp[i - 1][j].diagonal;
        horizontal = dp[i][j - 1].horizontal + dp[i][j - 1].diagonal;
        diagonal = board[i][j - 1] + board[i - 1][j] == 0 ? dp[i - 1][j - 1].getThreeWaySum() : 0;
        dp[i][j] = new State(horizontal, vertical, diagonal);
    }

    static class State {
        int horizontal; //가로
        int vertical; //세로
        int diagonal; //대각선

        public State(int horizontal, int vertical, int diagonal) {
            this.horizontal = horizontal;
            this.vertical = vertical;
            this.diagonal = diagonal;
        }

        public int getThreeWaySum() {
            return horizontal + vertical + diagonal;
        }
    }
}