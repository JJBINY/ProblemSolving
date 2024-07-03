package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * S1_14600_샤워실바닥깔기small
 * 구현
 */
public class S1_14600_샤워실바닥깔기small {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
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
        int K = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = parseInt(st.nextToken()) - 1;
        int y = parseInt(st.nextToken()) - 1;

        int N = 1;
        for (int i = 0; i < K; i++) {
            N *= 2;
        }

        int[][] arr = {
                {1, 1, 2, 2},
                {1, 5, 5, 2},
                {3, 5, 5, 4},
                {3, 3, 4, 4}
        };


        if (y < 2 && x < 2) {
            arr[1][1] = 1;
        } else if (y < 2 && x >= 2) {
            arr[1][2] = 2;
        } else if (y >= 2 && x < 2) {
            arr[2][1] = 3;
        } else {
            arr[2][2] = 4;
        }
        arr[y][x] = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }
}