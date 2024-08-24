package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G2 1983 숫자 박스
 * DP
 */
public class G2_1983_숫자박스 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Integer> A = new ArrayList<>();
    static List<Integer> B = new ArrayList<>();
    static int N;
    static int dp[][][];
    static int INF = (int) 1e6;

    static void solve(BufferedReader br) throws IOException {
        init(br);
        System.out.println(func(A.size() - 1, B.size() - 1, N - 1));
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        input(br, A);
        input(br, B);
        dp = new int[A.size()][B.size()][N];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -INF);
            }
        }
    }

    private static void input(BufferedReader br, List<Integer> list) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) continue;
            list.add(num);
        }
    }

    static int func(int i, int j, int k) {

        if (i > k || j > k) {
            return -INF;
        }

        if (i < 0 || j < 0 || k < 0) {
            return 0;
        }

        if (dp[i][j][k] != -INF) {
            return dp[i][j][k];
        }

        dp[i][j][k] = Math.max(func(i - 1, j, k - 1), func(i, j - 1, k - 1));
        return dp[i][j][k] = Math.max(dp[i][j][k], func(i - 1, j - 1, k - 1) + A.get(i) * B.get(j));
    }
}