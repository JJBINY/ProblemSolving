package 백준.누적합.imos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * P3 5541 Nails
 * 누적합, imos
 */
public class P3_5541_Nails {
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
        int[][] arr = new int[N+3][N+3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = parseInt(st.nextToken());
            int B = parseInt(st.nextToken());
            int X = parseInt(st.nextToken());
            arr[A][B]++;
            arr[A][B + 1]--;
            arr[A + X + 1][B]--;
            arr[A + X + 1][B + X + 2]++;
            arr[A + X + 2][B + 1]++;
            arr[A + X + 2][B + X + 2]--;
        }


        sweep(arr, 1, 0);
        sweep(arr, 0, 1);
        sweep(arr, 1, 1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(arr[i][j]>0) ans++;
            }
        }
        System.out.println(ans);
    }

    private static void sweep(int[][] arr, int dx, int dy) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                arr[i][j] += arr[i - dx][j - dy];
            }
        }
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print((arr[i][j]>=0?" ":"") + arr[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}