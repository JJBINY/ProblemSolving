package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 11062 카드 게임
 * DP
 */
public class G3_11062_카드게임 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            int T = 1;
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N;
    static int[] arr;
    static int[][] dp;

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        dp = new int[N][N];
        return func(0, N - 1, 0);
    }

    static int func(int l, int r, int turn) {
        if (l == r) {
            return turn % 2 == 0 ? arr[l] : 0;
        } else if (dp[l][r] != 0) {
            return dp[l][r];
        }

        int lResult = func(l + 1, r, turn + 1);
        int rResult = func(l, r - 1, turn + 1);
        if (turn % 2 == 0) {
            dp[l][r] = Math.max(lResult + arr[l], rResult + arr[r]);
        } else {
            dp[l][r] = Math.min(lResult, rResult);
        }
        return dp[l][r];
    }
}