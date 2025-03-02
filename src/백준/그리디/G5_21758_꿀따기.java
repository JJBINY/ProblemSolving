package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5_21758_꿀따기
 * 그리디
 */
public class G5_21758_꿀따기 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] psum = new int[N];
        psum[0] = arr[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            psum[i] = arr[i] + psum[i - 1];
            min = Math.min(min, psum[i] + arr[i]);
        }
        int ans = 2 * psum[N - 1] - arr[0] - min;

        psum[N-1] = arr[N-1];
        min = Integer.MAX_VALUE;
        for (int i = N-2; i > -1; i--) {
            psum[i] = arr[i] + psum[i + 1];
            min = Math.min(min, psum[i] + arr[i]);
        }
        ans = Math.max(ans, 2 * psum[0] - arr[N - 1] - min);
        ans = Math.max(ans, Arrays.stream(arr).max().getAsInt()*2);
        return ans;
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