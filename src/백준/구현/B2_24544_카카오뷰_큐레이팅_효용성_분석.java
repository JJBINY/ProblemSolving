package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * B2_24544_카카오뷰_큐레이팅_효용성_분석
 * 구현
 */
public class B2_24544_카카오뷰_큐레이팅_효용성_분석 {
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
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] registered = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            registered[i] *= arr[i];
        }
        int totalSum = Arrays.stream(arr).sum();
        int registeredSum = Arrays.stream(registered).sum();
        StringBuilder sb = new StringBuilder();
        return sb.append(totalSum).append("\n")
                .append(totalSum-registeredSum);
    }
}