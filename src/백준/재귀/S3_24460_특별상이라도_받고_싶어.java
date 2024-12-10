package 백준.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S3_24460_특별상이라도_받고_싶어
 * 재귀, 분할정복
 */
public class S3_24460_특별상이라도_받고_싶어 {
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
    static int[][] arr;

    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return func(0, 0, N);
    }

    static int func(int r, int c, int size) {
        if (size == 1) {
            return arr[r][c];
        }
        int ns = size / 2;
        int f1 = func(r, c, ns);
        int f2 = func(r + ns, c, ns);
        int f3 = func(r, c + ns, ns);
        int f4 = func(r + ns, c + ns, ns);
        return getSecond(f1, f2, f3, f4);
    }

    static int getSecond(int... x) {
        Arrays.sort(x);
        return x[1];
    }
}