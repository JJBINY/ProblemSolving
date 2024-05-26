package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S1_1080_행렬
 * 그리디
 */
public class S1_1080_행렬 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] a = new int[N][M];
        int[][] b = new int[N][M];
        for (int i = 0; i < N; i++) {
            a[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < N; i++) {
            b[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0;
        for (int i = 0; i <= N-3; i++) {
            for (int j = 0; j <= M-3; j++) {
                if(a[i][j]%2 == b[i][j]) continue;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        a[i + k][j + l]++;
                    }
                }
                cnt++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = Math.max(0,M-2); j < M; j++) {
                if(a[i][j]%2!=b[i][j]){
                    return -1;
                }
            }
        }

        for (int i = Math.max(0,N-2); i <N ; i++) {
            for (int j = 0; j < M; j++) {
                if(a[i][j]%2!=b[i][j]){
                    return -1;
                }
            }
        }
        return cnt;
    }
}