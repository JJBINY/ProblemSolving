package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 2229 조 짜기
 * DP
 */
public class G5_2229_조짜기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[] dp, arr;
    static int[][] minDp, maxDp;
    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        if(N==1){
            System.out.println(0);
            return;
        }

        dp = new int[N+1];
        minDp = new int[N+1][N+1];
        maxDp = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(minDp[i], -1);
            Arrays.fill(maxDp[i], -1);
        }

        for (int i = 2; i <= N; i++) {
            int min = arr[i];
            int max = arr[i];
            for (int j = i; j > 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max - min);
            }
        }

        System.out.println(dp[N]);
    }
}