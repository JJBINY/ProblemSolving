package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S2_19622_회의실 배정 3
 * DP
 */
public class S2_19622_회의실배정3 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int N = parseInt(br.readLine());
        int[] arr = new int[100_001];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            st.nextToken();
            arr[i] = parseInt(st.nextToken());
        }

        int[] dp = new int[100_001];
        dp[1] = arr[1];
        dp[2] = Math.max(arr[1], arr[2]);
        for (int i = 1; i <= N-2; i++) {
            dp[i + 2] = Math.max(dp[i - 1] + arr[i + 1], dp[i] + arr[i + 2]);
        }
        return dp[N];
    }
}