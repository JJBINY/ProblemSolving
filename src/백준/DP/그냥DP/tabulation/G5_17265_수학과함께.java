package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * G5 17265 수학과함께
 * DP
 */
public class G5_17265_수학과함께 {
    static String[][] operations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());

        Pair[][] dp = new Pair[N][N];
        int[][] nums = new int[N][N];
        operations = new String[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dp[i][j] = new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
                if((i+j)%2==0){
                    nums[i][j] = parseInt(st.nextToken());
                }else{
                    operations[i][j] = st.nextToken();
                }
            }
        }


        dp[0][0].a = dp[0][0].b = nums[0][0];

        int[] dr = new int[]{0, -1};
        int[] dc = new int[]{-1, 0};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    int pr = i + dr[k];
                    int pc = j + dc[k];
                    if(pr<0 || pr>=N||pc<0||pc>=N) continue;
                    if ((i + j) % 2 == 0) {
                        dp[i][j].a = max(dp[i][j].a, calculate(dp[pr][pc].a,nums[i][j], operations[pr][pc]));
                        dp[i][j].b = min(dp[i][j].b, calculate(dp[pr][pc].b,nums[i][j], operations[pr][pc]));
                    } else {
                        //연산자인경우 값만 저장
                        dp[i][j].a = max(dp[i][j].a, dp[pr][pc].a);
                        dp[i][j].b = min(dp[i][j].b, dp[pr][pc].b);
                    }
                }
            }
        }

        System.out.println(dp[N-1][N-1].a +" "+ dp[N-1][N-1].b);
        br.close();
    }

    private static int calculate(int a, int b, String operation) {
        switch (operation){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                throw new IllegalArgumentException();
        }
    }


    static class Pair{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
