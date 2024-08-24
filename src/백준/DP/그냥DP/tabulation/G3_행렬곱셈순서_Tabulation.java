package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G3 행렬 곱셈 순서 bottom up
 * https://www.acmicpc.net/problem/11049
 */
public class G3_행렬곱셈순서_Tabulation {
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
        for (int i = 1; i < n + 1; i++) { //구간 간격 i
            for (int j = 1; j < n + 1 - i; j++) {//[j,j+i]
                //1~5 = min(1~4+5, 1 + 2~5)
                int a = j;
                int b = j + i;
                dp[a][b] = Integer.MAX_VALUE;
                for (int k = j; k < j+i; k++) {
                    dp[a][b] = Math.min(dp[a][b],
                            dp[a][k] +dp[k+1][b]+ nums[a].r * nums[k].c * nums[b].c);
                }
//                System.out.println(a+","+b+"="+dp[a][b]);
            }
        }

        System.out.println(dp[1][n]);
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