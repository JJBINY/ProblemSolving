package 백준.그래프.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S2_1058_친구
 * 최단 경로, 플로이드-워셜
 */
public class S2_1058_친구 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], 1_000_000);
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if(s.charAt(j) == 'Y'){
                    arr[i][j] = 1;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i==j) continue;
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        return Arrays.stream(arr)
                .mapToInt(a -> Math.toIntExact(Arrays.stream(a).filter(x -> x < 3).count()))
                .max().orElseThrow();
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