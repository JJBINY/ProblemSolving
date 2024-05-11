package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * G1 2718 타일채우기
 * DP
 */
public class G1_2718_타일채우기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int T = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        while (T-- > 0) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int N = list.stream().mapToInt(Integer::valueOf).max().getAsInt();
        N = Math.max(N, 4);
        int[] dp = new int[N + 1];

        dp[1] = 1;
        dp[2] = 5;
        dp[3] = 11;
        dp[4] = dp[3] + 4*dp[2] + 2*dp[1] +3 ;
        int[] memo = {3, 2};
        for (int i = 5; i <= N; i++) {
            memo[i % 2] += (3 * dp[i - 4]) + (2 * dp[i - 5]);
            dp[i] = dp[i - 1] + (4 * dp[i - 2]) + (2 * dp[i - 3]) + memo[i % 2];
        }

        StringBuilder sb = new StringBuilder();
        for (int n : list) {
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);

    }
}