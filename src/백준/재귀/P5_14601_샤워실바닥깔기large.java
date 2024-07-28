package 백준.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * P5_14601_샤워실바닥깔기large
 * 구현, 분할정복, 재귀
 */
public class P5_14601_샤워실바닥깔기large {
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

    static int[][] arr;
    static int num;

    static Object solve(BufferedReader br) throws IOException {
        int K = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = parseInt(st.nextToken()) - 1;
        int r = parseInt(st.nextToken()) - 1;
        int N = 1 << K;

        arr = new int[N][N];
        arr[r][c] = -1;
        func(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }

    static void func(int r, int c, int length) {
        int nl = length >> 1;
        num++;
        if (isEmpty(r, c, nl)) arr[r + nl - 1][c + nl - 1] = num;
        if (isEmpty(r, c + nl, nl)) arr[r + nl - 1][c + nl] = num;
        if (isEmpty(r + nl, c, nl)) arr[r + nl][c + nl - 1] = num;
        if (isEmpty(r + nl, c + nl, nl)) arr[r + nl][c + nl] = num;

        if (nl == 1) return;
        func(r, c, nl);
        func(r, c + nl, nl);
        func(r + nl, c, nl);
        func(r + nl, c + nl, nl);
    }

    static boolean isEmpty(int r, int c, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (arr[r + i][c + j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}