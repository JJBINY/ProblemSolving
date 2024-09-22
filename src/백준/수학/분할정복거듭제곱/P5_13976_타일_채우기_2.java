package 백준.수학.분할정복거듭제곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * P5_13976_타일_채우기_2
 * DP, 행렬 거듭제곱
 */
public class P5_13976_타일_채우기_2 {
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

    static int mod = 1_000_000_007;

    static Object solve(BufferedReader br) throws IOException {

        /*
            a -> f(x) = 3f(x-2) + 2f(x-4) + 2f(x-6) + ...
            b -> f(x-2) = 3f(x-4) + 2f(x-6) + 2f(x-8) + ...
            a - b -> f(x) - f(x-2) = 3f(x-2) - f(x-4)

            => f(x) = 4*f(x-2) - f(x-4)

            [[f(n)],[f(n-2)]] = [[4,-1],[1,0]] * [[f(n-2)],[f(n-4)]]
            =  [[4,-1],[1,0]]^2 * [[f(n-4)],[f(n-6)]]
            ...
            = [[4,-1],[1,0]]^(n/2-1) * [[f(2)],[f(0)]]
            = [[4,-1],[1,0]]^(n/2-1) * [[3],[1]]

            f(0) = 1, f(2) = 3
         */

        long N = Long.parseLong(br.readLine());
        if(N%2!=0){
            return 0;
        }

        long[][] A = {{4, -1}, {1, 0}};
        long[][] pow = Matrix.pow(A, N /2-1);

//        for (long[] longs : pow) {
//            System.out.println(Arrays.toString(longs));
//        }

        // 모듈러 관련 참고: https://www.acmicpc.net/board/view/130299
        return (pow[0][0] * 3 + pow[0][1]) % mod;
    }

    static class Matrix{
        static long[][] pow(long[][] A, long exp){
            if(exp == 0){
                return new long[][]{{1, 0}, {0, 1}}; // 단위 행렬
            } else if(exp == 1){
                return A;
            }

            long[][] tmp = pow(A, exp / 2);
            if (exp % 2 == 0) {
                return multiply(tmp, tmp);
            }else{
                return multiply(multiply(tmp, tmp), A);
            }
        }

        static long[][] multiply(long[][] A, long[][] B){
            long[][] result = {{0, 0}, {0, 0}};

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                        result[i][j] = (result[i][j] +mod)%mod;
                    }
                }
            }
            return result;
        }
    }
}