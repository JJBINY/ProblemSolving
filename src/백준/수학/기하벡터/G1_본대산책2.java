package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G1 본대 산책 2
 * https://www.acmicpc.net/problem/12850
 */
public class G1_본대산책2 {

    static long[][] matrix = new long[][]{
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0}};

    static long[][] multifly(long[][] a, long[][] b){
        long[][] res = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    res[i][j] += a[i][k]*b[k][j];
                }
                res[i][j] %= 1000000007;
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        long[][] res = new long[8][8];
        for (int i = 0; i < 8; i++) {
            res[i][i] = 1;
        }

        while (d > 0) {
            if ((d & 1) == 1) {
                res = multifly(res, matrix);
            }
            matrix = multifly(matrix, matrix);
            d >>= 1;
        }
        System.out.println(res[0][0]);
    }
}
