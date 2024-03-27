package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G1 4902 삼각형의 값
 * 누적합, 구현, 완전탐색
 */
public class G1_4902_삼각형의값 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, ans;
    static int[][] arr, pSums;

    static void solve(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        int TC = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            ans = Integer.MIN_VALUE;

            arr = new int[N][];
            pSums = new int[N][];
            input(st);

            for (int i = 0; i < N; i++) { // O(N^3)
                for (int j = 0; j < 2 * i + 1; j += 2) {
                    topDown(i, j);
                }
                for (int j = 1; j < 2 * i + 1; j += 2) {
                    bottomUp(i, j);
                }
            }

            sb.append(TC++).append(". ").append(ans).append("\n");
        } // while TC
        System.out.println(sb.toString());
    }

    private static void bottomUp(int r, int c) {
        int pSum = 0;
        for (int i = r, j = c; i > 0; i--, j -= 2) {
            if (j <= 0 || c >= arr[i].length) return;
            pSum += pSums[i][c] - pSums[i][j] + arr[i][j];
            ans = Math.max(ans, pSum);
        }
    }

    private static void topDown(int r, int c) {
        int pSum = 0;
        for (int i = r, j = c; i < N; i++, j += 2) {
            pSum += pSums[i][j] - pSums[i][c] + arr[i][c];
            ans = Math.max(ans, pSum);
        }
    }

    private static void input(StringTokenizer st) {
        for (int i = 0; i < N; i++) {
            arr[i] = new int[2 * i + 1];
            pSums[i] = new int[2 * i + 1];
            for (int j = 0; j < 2 * i + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                pSums[i][j] = arr[i][j];
                if (j > 0) {
                    pSums[i][j] += pSums[i][j - 1];
                }
            }
        } // for i
    }

}