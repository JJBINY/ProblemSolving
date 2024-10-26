package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 백준.구현.S1_31964_반품_회수
 */
public class S1_31964_반품_회수 {
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
        int[] X = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] T = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int toEnd = Math.max(X[N - 1], T[N - 1]); // 기다리더라도 맨 끝에서 기다리는 게 최선
        T = Arrays.stream(T).map(t -> t - toEnd).toArray();

        // 돌아가는 길 시뮬레이션
        int toStart = 0;
        for (int i = N-1; i >= 0 ; i--) {
            if(toStart < T[i]){
                toStart = T[i]; // 대기
            }
            if(i > 0){
                toStart += X[i] - X[i - 1]; // 이동
            }else{
                toStart += X[i];
            }

        }
        return toEnd+toStart;
    }
}