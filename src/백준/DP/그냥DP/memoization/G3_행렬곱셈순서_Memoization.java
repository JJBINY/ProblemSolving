package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G3 행렬 곱셈 순서 top down
 * https://www.acmicpc.net/problem/11049
 */
public class G3_행렬곱셈순서_Memoization {
    static int[][] dp;
    static Pair[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        nums = new Pair[n + 1];
        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nums[i] = new Pair(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        dp = new int[n + 1][n + 1];
        System.out.println(solve(1, n));
    }

    static int solve(int from, int to) {
        if(from == to) return 0;
        if(dp[from][to]>0) return dp[from][to];

        dp[from][to] = Integer.MAX_VALUE;
        for (int i = 0; i < to-from; i++) {
            int sum = solve(from, from + i) + solve(from + i + 1, to);
            int val = nums[from].r * nums[from + i].c * nums[to].c;
            dp[from][to] = Math.min(dp[from][to], sum + val);
        }

        return dp[from][to];
    }

    static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}