package 백준.수학.정수론.포함배제의원리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.pow;


/**
 * G2_16565_N포커
 * 수학, 정수론, 포함 배제의 원리, DP, 조합
 */
public class G2_16565_N포커 {
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

    static int mod = 10_007;
    static int[][] dp = new int[53][53];

    static Object solve(BufferedReader br) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        /*
        정답 = 포카드가 가능한 13개 셋에서 각각의 셋이 나올 확률의 합집합
        -> 포함 배제의 원리 적용하여 구함

         */
        for (int i = 1; i <= 13 && 4 * i <= N; i++) {
            ans += pow(-1, i + 1) * (combi(13, i) * combi(52 - 4 * i, N - 4 * i)) % mod;
            ans = (ans + mod) % mod; // 모듈러 연산 음수일 경우 처리
        }
        return ans;
    }

    static int combi(int n, int r) {
        if (dp[n][r] != 0) return dp[n][r];
        if (r == 0 || n == r) return dp[n][r] = 1;
        return dp[n][r] = (combi(n - 1, r) + combi(n - 1, r - 1)) % mod;
    }
}