package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 25682 체스판 다시 칠하기 2
 * 누적합
 */
public class G5_25682_체스판다시칠하기2 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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

    static int N, M, K;
    static String[] arr;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        arr = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = br.readLine();
        }

        int result = Math.min(getResult('B'), getResult('W'));

        return result;
    }

    static int getResult(char start){
        int[][] pSums = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                pSums[i][j] = pSums[i][j - 1];
                if (!isCorrect(arr[i].charAt(j-1), i, j, start)) {
                    pSums[i][j]++;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                pSums[i][j] += pSums[i-1][j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int cnt = pSums[i][j] - pSums[i - K][j] - pSums[i][j - K] + pSums[i - K][j - K];
                result = Math.min(result, cnt);
            }
        }
        return result;
    }

    private static boolean isCorrect(char s, int r, int c, char start) {
        if((r + c) % 2 == 0 && s == start){
            return true;
        } else if ((r + c) % 2 == 1 && s != start) {
            return true;
        }
        return false;
    }
}