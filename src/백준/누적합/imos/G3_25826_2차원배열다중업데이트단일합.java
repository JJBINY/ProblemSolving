package 백준.누적합.imos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 25826 2차원 배열 다중 업데이트 단일 합
 * 누적합, imos
 */
public class G3_25826_2차원배열다중업데이트단일합 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int[][] arr = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        long[][] pSums = new long[N+1][N+1];
        for (int i = 0; i < M-1; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int r1 = parseInt(st.nextToken());
            int c1 = parseInt(st.nextToken());
            int r2 = parseInt(st.nextToken());
            int c2 = parseInt(st.nextToken());
            int k = parseInt(st.nextToken());
            pSums[r1][c1] += k;
            pSums[r1][c2 + 1] -= k;
            pSums[r2 + 1][c1] -= k;
            pSums[r2 + 1][c2 + 1] += k;
        }

        sweep( 0, 1, pSums);
        sweep(1, 0, pSums);

//        printArr(pSums);

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        int r1 = parseInt(st.nextToken());
        int c1 = parseInt(st.nextToken());
        int r2 = parseInt(st.nextToken());
        int c2 = parseInt(st.nextToken());
        long ans = 0;
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                ans += pSums[i][j] + arr[i][j];
            }
        }
        System.out.println(ans);
    }

    private static void sweep(int dr, int dc, long[][] arr) {
        for (int i = dr; i < arr.length; i++) {
            for (int j = dc; j < arr.length; j++) {
                arr[i][j] += arr[i-dr][j-dc];
            }
        }
    }

    private static void printArr(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print((arr[i][j]>=0?" ":"") + arr[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}