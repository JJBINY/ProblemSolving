package 백준.수학.순열조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 10164 격자상의 경로
 * 수학, 조합, DP
 */
public class S1_10164_격자상의경로 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());

        int[][] dp = new int[N][M];

        Arrays.fill(dp[0], 1);
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <N ; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        if(K==0){
            System.out.println(dp[N - 1][M - 1]);
        }else{
            int r = (K-1)/M;
            int c = (K-1)%M;

            System.out.println(dp[r][c] * dp[N - 1 - r][M - 1 - c]);
        }

    }
}