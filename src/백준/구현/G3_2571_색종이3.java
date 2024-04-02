package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 2571 색종이 - 3
 * 구현, 누적합, 완전탐색
 */
public class G3_2571_색종이3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[][] arr = new int[102][102];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken());
            int c = parseInt(st.nextToken());
            arr[r][c]++;
            arr[r + 10][c]--;
            arr[r][c+10]--;
            arr[r + 10][c + 10]++;
        }

        sweep(1, 0, arr);
        sweep(0, 1, arr);
        flatten(arr);

        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if(arr[i][j] == 0) continue;
                arr[i][j] += arr[i - 1][j];
            }
        }

        int ans = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                int h = 100;
                for (int k = j; k <= 100; k++) {
                    if(arr[i][k] == 0) break;
                    h = Math.min(h, arr[i][k]);
                    ans = Math.max(ans, h * (k - j + 1));
                }
            }
        }


        System.out.println(ans);
    }

    private static void flatten(int[][] arr) {
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if(arr[i][j]>0){
                    arr[i][j] = 1;
                }
            }
        }
    }

    static void sweep(int dr, int dc, int[][] arr){
        for (int i = dr; i < arr.length; i++) {
            for (int j = dc; j < arr.length; j++) {
                arr[i][j] += arr[i - dr][j - dc];
            }
        }
    }

    private static void printArr(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print((arr[i][j] >= 0 ? " " : "") + arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}