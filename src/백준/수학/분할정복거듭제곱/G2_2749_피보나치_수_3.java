package 백준.수학.분할정복거듭제곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G2_2749_피보나치_수_3
 * 수학, 행렬 거듭제곱
 */
public class G2_2749_피보나치_수_3 {
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

    /*
    f(n) = f(n-1) + f(n-2)
    [[f(n+1)], [f(n)]] = [[1, 1], [1, 0]] * [[f(n)], [f(n-1)]]
    = [[1, 1], [1, 0]]^2 * [[f(n-1)], [f(n-2)]]
    = [[1, 1], [1, 0]]^3 * [[f(n-2)], [f(n-3)]]
    ...
    = [[1, 1], [1, 0]]^n * [[f(1)], [f(0)]]
     */

    static long mod = 1_000_000;
    static Object solve(BufferedReader br) throws IOException {
        long n = Long.parseLong(br.readLine());
        long[][] A = {{1, 1}, {1, 0}};
        long[][] pow = Matrix.pow(A, n);
        long f1 = 1;
        long f0 = 0;
        long fn = (pow[1][0] * f1 + pow[1][1] * f0);
        return fn;
    }

    static class Matrix{
        static long[][] pow(long[][] A, long exp){
            if(exp == 1){
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
            int len = A.length;
            long[][] result = new long[len][len];

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int k = 0; k < len; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
            return result;
        }
    }
}