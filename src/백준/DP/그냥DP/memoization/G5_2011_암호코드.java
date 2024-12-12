package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5_2011_암호코드
 * DP, Memoization
 */
public class G5_2011_암호코드 {
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

    static int N, mod = 1_000_000;
    static int[] dp;
    static int[] arr;

    static Object solve(BufferedReader br) throws IOException {
        arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        N = arr.length;
        dp = new int[N];
        Arrays.fill(dp, -1);
        return func(0);
    }

    static int func(int idx) {
        if (idx >= N) return 1;
        else if (dp[idx] > -1) return dp[idx];
        else if (arr[idx] == 0) return dp[idx] = 0;

        dp[idx] = func(idx + 1);
        if (idx < N - 1) {
            int tmp = 10 * arr[idx] + arr[idx + 1];
            if (10 <= tmp && tmp <= 26) {
                dp[idx] += func(idx + 2);
            }
        }
        return dp[idx] % mod;
    }
}