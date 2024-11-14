package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S1_16924_십자가_찾기
 * 구현, 시뮬레이션
 */
public class S1_16924_십자가_찾기 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '*') {
                    arr[i][j] = 1;
                }
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        // (0,0) 부터 (N-1, M-1)까지 모든 점을 한 번씩만 고려하면 k <= N*M을 만족한다.
        // (i,j) 일 때, 최대 십자가 크기 s = min(i, N-i, j, M-j)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) continue;
                int s = min(i, j, N - i-1, M - j-1);
                while (s > 0) {
                    if (canDraw(arr, i, j, s)) {
                        draw(arr, i, j, s);
                        cnt++;
                        sb.append(i+1).append(" ")
                                .append(j+1).append(" ")
                                .append(s).append("\n");
                        break;
                    }
                    s--;
                }
            }
        }
//        Arrays.stream(arr).forEach(ints -> System.out.println(Arrays.toString(ints)));
//        System.out.println("cnt = " + cnt);

        if (Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .anyMatch(i -> i == 1)) {
            return -1;
        }
        return sb.insert(0, cnt + "\n");
    }

    private static boolean canDraw(int[][] arr, int r, int c, int s) {
        for (int i = -s; i <= s; i++) {
            if (arr[r + i][c] == 0) return false;
            if (arr[r][c+i] == 0) return false;
        }
        return true;
    }

    private static void draw(int[][] arr, int r, int c, int s) {
        for (int i = -s; i <= s; i++) {
            arr[r+i][c]++;
            arr[r][c + i]++;
        }
    }

    static int min(int... nums) {
        return Arrays.stream(nums).min().getAsInt();
    }

}