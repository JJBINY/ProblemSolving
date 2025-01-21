package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;


/**
 * S2_18111_마인크래프트
 * 브루트포스, 구현
 */
public class S2_18111_마인크래프트 {

    /*
    A. 좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다. -> 2초 소요
    B. 인벤토리에서 블록 하나를 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다. -> 1초 소요

    1. 집터 아래에 동굴 등 빈 공간은 존재 X
    2. 작업을 시작할 때 인벤토리에는 B개의 블록이 들어 있다
    3. 집터 바깥에서 블록을 가져올 수 없다
        - 처음 들어있던 B개의 블록 혹은 A작업을 통해 제거한 블록만 B작업에 사용 가능
    4. 땅의 높이 = [0,256]

    출력: 땅 고르기에 걸리는 최소 시간과 높이
    - 최소 시간인 경우가 여러개라면 그중 가장 높은 땅의 높이 출력
     */

    static int N, M, B;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // <=500
        M = Integer.parseInt(st.nextToken()); // <=500
        // 500^2 * 256 ~= 6.4*10^7이므로 전체 블록 합은 int 범위 이내
        B = Integer.parseInt(st.nextToken()); // <= 6.4*10^7

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = Integer.MAX_VALUE;
        int height = 0;
        int min = findHeight(arr, Math::min);
        int max = findHeight(arr, Math::max);

        // O(NM): 500x500x256 < 10^8
        for (int h = min; h <= max; h++) { // 256
            int t = 0;
            int cnt = B;
            for (int i = 0; i < N; i++) { // 500
                for (int j = 0; j < M; j++) { // 500
                    int diff = arr[i][j] - h;
                    cnt += diff;

                    if (diff > 0) {
                        t += diff * 2;
                    } else {
                        t -= diff;
                    }
                }
            }
            if (cnt > -1) {
                if(t <= time){
                    time = t;
                    height = h;
                }
            }
        }

        return time + " " + height;
    }

    private static int findHeight(int[][] arr, BiFunction<Integer, Integer, Integer> bfunc) {
        int result = arr[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = bfunc.apply(result, arr[i][j]);
            }
        }
        return result;
    }

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
}