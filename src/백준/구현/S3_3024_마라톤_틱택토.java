package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S3_3024_마라톤_틱택토
 * 구현
 */
public class S3_3024_마라톤_틱택토 {
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
    static char[][] b;

    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        b = new char[N][N];
        for (int i = 0; i < N; i++) {
            b[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i][j] == '.') continue;

                if (i + 2 < N) {
                    if (b[i][j] == b[i + 1][j] && b[i][j] == b[i + 2][j]) {
                        return b[i][j];
                    }
                }
                if (j + 2 < N) {
                    if (b[i][j] == b[i][j + 1] && b[i][j] == b[i][j + 2]) {
                        return b[i][j];
                    }
                }
                if (i + 2 < N && j + 2 < N) {
                    if (b[i][j] == b[i + 1][j + 1] && b[i][j] == b[i + 2][j + 2]) {
                        return b[i][j];
                    }
                }
                if (i + 2 < N && j - 2 >= 0) {
                    if (b[i][j] == b[i + 1][j - 1] && b[i][j] == b[i + 2][j - 2]) {
                        return b[i][j];
                    }
                }
            }
        }

        return "ongoing";
    }
}