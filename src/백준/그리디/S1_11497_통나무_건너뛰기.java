package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S1_11497_통나무_건너뛰기
 * 그리디, 정렬
 */
public class S1_11497_통나무_건너뛰기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
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
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int ans = Math.max(arr[1] - arr[0], arr[N - 1] - arr[N - 2]);
        for (int i = 2; i < N; i ++) {
            ans = Math.max(ans, arr[i] - arr[i - 2]);
        }
        return ans;
    }
}