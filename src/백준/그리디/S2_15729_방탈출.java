package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S2_15729_방탈출
 * 그리디
 */
public class S2_15729_방탈출 {

    static Object solve(BufferedReader br) throws IOException {
        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 다 0으로 만들기
        for (int i = 0; i < N; i++) {
            if(arr[i] == 0){
                continue;
            }
            ans++;
            if(i+1<N) arr[i+1] = togle(arr[i + 1]);
            if(i+2<N) arr[i+2] = togle(arr[i + 2]);
        }
        return ans;
    }

    private static int togle(int state) {
        return state == 0 ? 1 : 0;
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