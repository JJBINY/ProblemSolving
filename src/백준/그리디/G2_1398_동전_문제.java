package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G2_1398_동전_문제
 * DP, 그리디
 */
public class G2_1398_동전_문제 {
    static int[] dp = new int[100];

    static Object solve(BufferedReader br) throws IOException {
        long price = Long.parseLong(br.readLine());

        int res = 0;
        while (price > 0) {
            res += dp[(int) (price % 100)];
            price /= 100;
        }

        return res;
    }

    public static void main(String[] args) {
        init();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        Arrays.fill(dp, 100);
        dp[0] = 0;
        int[] values = {1, 10, 25};
        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                if(i-values[j]<0){
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - values[j]] + 1);
            }
        }
    }
}