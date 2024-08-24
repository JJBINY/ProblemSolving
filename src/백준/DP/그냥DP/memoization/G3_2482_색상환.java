package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G3 2482 색상환
 * DP
 */
public class G3_2482_색상환 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[][] dp;

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        dp = new int[N + 1][K + 1];
        System.out.println(func(N, K));
    }

    static int func(int n, int k) {
        if (k > n / 2) {
            return 0;
        } else if (k == 1) {
            return n;
        } else if (dp[n][k] > 0) {
            return dp[n][k];
        }

        // func(n-1,k) : 1~n-1에서 k개 뽑은 경우
        // func(n-2,k-1) : 1과 n-1을 제외한 2~n-2에서 k-1개 뽑은 경우; 1과 n-1을 뽑지 않았으므로 원형 생각할 필요 없음
        return dp[n][k] = (func(n - 2, k - 1) + func(n - 1, k)) % 1_000_000_003;
    }
}