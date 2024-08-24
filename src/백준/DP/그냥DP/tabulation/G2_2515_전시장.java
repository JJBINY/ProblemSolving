package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2_2515_전시장
 * DP
 */
public class G2_2515_전시장 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int S = parseInt(st.nextToken());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int H = parseInt(st.nextToken());
            int C = parseInt(st.nextToken());
            arr[i][0] = H;
            arr[i][1] = C;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[N];
        int ans = arr[0][1];
        dp[0] = arr[0][1];
        int pMax = 0;
        int x = 0;
        for (int i = 1; i < N; i++) {
            while (arr[x][0] <= arr[i][0] -S){
                pMax = Math.max(pMax, dp[x]);
                x++;
            }
            dp[i] = pMax + arr[i][1];
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}