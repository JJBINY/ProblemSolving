package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 17144 미세먼지 안녕!
 * 구현, 시뮬레이션
 */
public class G4_17144_미세먼지안녕 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int R, C, T;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        T = parseInt(st.nextToken());

        arr = new int[R][C];
        AirCleaner airCleaner = new AirCleaner();
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                arr[r][c] = parseInt(st.nextToken());
                if (arr[r][c] == -1) {
                    airCleaner.init(r);
                }
            }
        }


        for (int t = 0; t < T; t++) {
            int[][] next = new int[R][C];
//            printArr(arr);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    next[r][c] += arr[r][c];
                    if (arr[r][c] == -1) continue;
                    diffuse(next, r, c);
                } // for C
            } // for R
            airCleaner.circulate(next);
            arr = next;
        } // for T

//        printArr(arr);

        int ans = Arrays.stream(arr)
                .mapToInt(as -> Arrays.stream(as)
                        .filter(i -> i > 0).sum())
                .sum();
        System.out.println(ans);
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void diffuse(int[][] next, int r, int c) {
        int d = arr[r][c] / 5;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C || arr[nr][nc] == -1) continue;
            next[r][c] -= d;
            next[nr][nc] += d;
        } //확산
    }

    static class AirCleaner {
        int r1 = -1;
        int r2;


        public void init(int r) {
            if (r1 != -1) {
                return;
            }
            this.r1 = r;
            this.r2 = r + 1;
        }

        public void circulate(int[][] arr) {
            for (int r = r1 - 1; r > 0; r--) {
                arr[r][0] = arr[r - 1][0];
            }
            for (int c = 0; c < C - 1; c++) {
                arr[0][c] = arr[0][c + 1];
            }
            for (int r = 0; r < r1; r++) {
                arr[r][C - 1] = arr[r + 1][C - 1];
            }
            for (int c = C - 1; c > 1; c--) {
                arr[r1][c] = arr[r1][c - 1];
            }
            arr[r1][1] = 0;

            for (int r = r2 + 1; r < R - 1; r++) {
                arr[r][0] = arr[r + 1][0];
            }
            for (int c = 0; c < C - 1; c++) {
                arr[R - 1][c] = arr[R - 1][c + 1];
            }
            for (int r = R - 1; r > r2; r--) {
                arr[r][C - 1] = arr[r - 1][C - 1];
            }
            for (int c = C - 1; c > 1; c--) {
                arr[r2][c] = arr[r2][c - 1];
            }
            arr[r2][1] = 0;
        }

        @Override
        public String toString() {
            return "AirCleaner{" +
                    "r1=" + r1 +
                    ", r2=" + r2 +
                    '}';
        }
    }
}