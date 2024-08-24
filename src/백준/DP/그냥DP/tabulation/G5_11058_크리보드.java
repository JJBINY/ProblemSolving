package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Integer.parseInt;


/**
 * G5 11058 크리보드
 * DP
 */
public class G5_11058_크리보드 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve2(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());

        long[] dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if(i<7) continue;

            for (int j = 3; j <i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j-1));
            }
        }

        System.out.println(dp[N]);
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        Pair[][] dp = new Pair[101][3]; //A, Ctrl-A+C+V, Ctrl-V

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        dp[0][0] = new Pair(0, 0);
        dp[1][0] = new Pair(1, 0);
        dp[2][0] = new Pair(2, 0);

        for (int i = 3; i <= N; i++) {
            dp[i][0] = getMaxNum(dp[i - 1]);
            dp[i][0].num++;

            dp[i][1] = getMaxNum(dp[i - 3]);
            dp[i][1].buffer = dp[i][1].num;
            dp[i][1].num *= 2;

            dp[i][2] = new Pair(0, 0);
            for (int j = 0; j < 3; j++) {
                long a = dp[i - 1][j].num + dp[i - 1][j].buffer;
                if(dp[i][2].num < a){
                    dp[i][2].num = a;
                    dp[i][2].buffer = dp[i - 1][j].buffer;
                }else if(dp[i][2].num == a && dp[i][2].buffer < dp[i-1][j].buffer){
                    dp[i][2].buffer = dp[i - 1][j].buffer;
                }
            }
        }

        System.out.println(getMaxNum(dp[N]).num);
    }

    static class Pair{
        long num;
        long buffer;

        public Pair(long num, long buffer) {
            this.num = num;
            this.buffer = buffer;
        }

    }

    static Pair getMaxNum(Pair[] pairs){
        Pair pair = Arrays.stream(pairs).max(Comparator.comparingLong(p -> p.num)).get();
        return new Pair(pair.num,pair.buffer);
    }

}
