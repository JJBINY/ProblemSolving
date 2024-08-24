package 백준.DP.그냥DP.tabulation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G4 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 * 주의*
 * 12, 12 -> 회문
 * 12, 21 -> 회문아님
 * */
public class G4_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        init(n, dp, sequence);

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                int r = j;
                int c = i+j;
                if(sequence[r] != sequence[c]) continue;
                dp[r][c] = dp[r + 1][c - 1];
            }
        }

        int m = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bw.write(dp[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void init(int n, int[][] dp, int[] sequence) {
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i< n -1 && sequence[i] == sequence[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }
    }
}