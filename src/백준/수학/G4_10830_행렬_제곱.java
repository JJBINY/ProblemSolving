package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G4_10830_행렬_제곱
 * 수학, 행렬 거듭제곱
 */
public class G4_10830_행렬_제곱 {
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

    static int N;
    static int mod = 1_000;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % mod;
            }
        }

        int[][] result = Matrix.pow(A, B);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }

    static class Matrix{
        static int[][] pow(int[][] A, long exp){
            if(exp==1){
                return A;
            }

            int[][] tmp = pow(A, exp / 2);
            if (exp % 2 == 0) {
                return multiply(tmp, tmp);
            }else{
                return multiply(multiply(tmp, tmp), A);
            }
        }

        static int[][] multiply(int[][] A, int[][] B){
            int[][] result = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                    result[i][j] %= mod;
                }
            }
            return result;
        }
    }
}