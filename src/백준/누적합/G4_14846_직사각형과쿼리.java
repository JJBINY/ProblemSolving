package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G4 14846 직사각형과 쿼리
 * 누적합, DP
 */
public class G4_14846_직사각형과쿼리 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][][] pSum = new int[N + 1][N + 1][11];
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                for (int k = 1; k <= 10; k++) {
                    pSum[i][j][k] = pSum[i][j - 1][k] + pSum[i - 1][j][k] - pSum[i - 1][j - 1][k];
                }
                pSum[i][j][num] += 1;
            }
        } //fori

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int res = 0;

            for (int i = 1; i <= 10; i++) {
                if (pSum[x2][y2][i] - pSum[x2][y1-1][i] - pSum[x1-1][y2][i] + pSum[x1 - 1][y1 - 1][i] > 0) {
                    res++;
                }
            }
            sb.append(res).append("\n");
        } //while Q

        System.out.println(sb.toString());
    }
}