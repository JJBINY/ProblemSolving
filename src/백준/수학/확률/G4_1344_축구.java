package 백준.수학.확률;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G4_1344_축구
 * 수학, 확률론, DP
 */
public class G4_1344_축구 {
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
        double p0 = Double.parseDouble(br.readLine()) / 100;
        double p1 = Double.parseDouble(br.readLine()) / 100;
        double[] p = {p0, p1};
        double[][][] dp = new double[2][19][19]; // 팀, 간격, 골

        dp[0][0][0] = 1;
        dp[1][0][0] = 1;
        for (int t = 0; t < 2; t++) { // 팀
            for (int i = 1; i <= 18; i++) { // 간격
                for (int j = 0; j <= i; j++) { //골
                    dp[t][i][j] = dp[t][i - 1][j] * (1 - p[t]); // 골x
                    if (j > 0) {
                        dp[t][i][j] += dp[t][i - 1][j - 1] * p[t]; // 골o
                    }
                } // for j
            } // for i
        } // for t

        double[] nonPrimeProbs = new double[2];
        for (int goal = 0; goal <= 18; goal++) {
            if (isPrime(goal)) continue;
            for (int t = 0; t < 2; t++) {
                nonPrimeProbs[t] += dp[t][18][goal];
            }
        }
        return 1 - nonPrimeProbs[0] * nonPrimeProbs[1];
    }

    static boolean isPrime(int n) {
        if(n<2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}