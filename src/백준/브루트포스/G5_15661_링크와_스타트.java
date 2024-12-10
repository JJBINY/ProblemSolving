package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G5_15661_링크와_스타트
 * 브루트포스, 브루트포스
 */
public class G5_15661_링크와_스타트 {
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

    static int N, result;
    static int[][] arr;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            func(i,1 << i);
        }
        return result;
    }

    static void func(int seq, int state) {
        if (state == 0 || state == 1 << N) return;
        int diff = calculate(state);
        result = Math.min(diff, result);
        for (int i = seq+1; i < N; i++) {
            if ((state & 1 << i) > 0) continue;
            func(i,state | 1 << i);
        }
    }

    private static int calculate(int state) {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((state & 1 << i) > 0 && (state & 1 << j) > 0) {
                    start += arr[i][j] + arr[j][i];
                } else if ((state & 1 << i) == 0 && (state & 1 << j) == 0) {
                    link += arr[i][j] + arr[j][i];
                }
            }
        }
        return Math.abs(start - link);
    }
}