package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

/**
 * S2 9658 돌 게임 4
 * DP
 */
public class S2_9658_돌게임4 {
    /*
    1 3 4
    N=1, CY win -> dp[1] = false
    N=2, SK; dp[2] = true
    N=3, CY; dp[3] = false
    N=4, SK; dp[4] = !(dp[3] && dp[1])
    N=5, SK; dp[5] = !(dp[4] && dp[2] && dp[1])
    N=6, SK; dp[6] = !(dp[5] && dp[3] && dp[2])
    N=7, SK; dp[7] = !(dp[6] && dp[4] && dp[3])
    N=8, CY; dp[8] = !(dp[7] && dp[5] && dp[4])
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        br.close();
        boolean[] dp = new boolean[1001];
        dp[2] = true;
        dp[4] = true;
        int[] di = new int[]{1, 3, 4};
        for (int i = 5; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i] = dp[i] || !dp[i - di[j]];
            }
        }
        System.out.println(dp[N] ? "SK" : "CY");
//        System.out.println((N % 7 == 1 || N % 7 == 3) ? "CY" : "SK");
    }


    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }
    }

}
