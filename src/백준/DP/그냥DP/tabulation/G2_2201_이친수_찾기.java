package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G2_2201_이친수_찾기
 * DP
 */
public class G2_2201_이친수_찾기 {
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

    static Object solve(BufferedReader br) throws IOException {
        long K = Long.parseLong(br.readLine());
        if(K==1) return 1;

        // dp[len] = 첫 비트가 1로 시작하는 길이가 len인 이친수 개수 = 피보나치
        long[] dp = new long[100];
        dp[1] = 1;
        dp[2] = 2;
        int len = 2;
        while (dp[len] <= K) {
            dp[len+1] = dp[len] + dp[len-1];
            len++;
        }
        // dp[len] > K -> K번째 이친수는 len-1개의 bit로 이루어져 있다.
//        System.out.println("dp[len] = " + dp[len]);

        StringBuilder sb = new StringBuilder();
        long cnt = 0;
        for (int i = len-1; i > 0; i--) {
            if (cnt+dp[i] <= K) {
                sb.append(1);
                cnt += dp[i];
            } else {
                sb.append(0);
            }
        }

        return sb;
    }
}