package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * G4_2253_점프
 * DP
 */
public class G4_2253_점프 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int tc = 1;
//            int T = Integer.parseInt(br.readLine());
//            long s = System.currentTimeMillis();
            for (int i = 1; i <= tc; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
//            long e = System.currentTimeMillis();
//            System.out.println((e - s)/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }

        // 1+2+...+v <= N -> v(v+1) <= 2n -> sqrt(v^2+v) <= sqrt(2N) -> v < sqrt(2N)
        int maxV = (int) Math.sqrt(2*N);
        int INF = 1_000_000;
        int[][] dp = new int[N + 1][maxV + 1];
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[1][0] = 0;
        for (int i = 2; i <= N; i++) {
            if(set.contains(i)) continue;
            for (int v = 1; v <= Math.min(i, maxV); v++) {
                dp[i][v] = Math.min(dp[i][v], dp[i - v][v - 1] + 1);
                dp[i][v] = Math.min(dp[i][v], dp[i - v][v] + 1);
                if(v < maxV) dp[i][v] = Math.min(dp[i][v], dp[i - v][v+1] + 1);
            }
        }

        int ans = Arrays.stream(dp[N]).min().getAsInt();
        return ans == INF ? -1 : ans;
    }
}