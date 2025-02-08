package 백준.이분탐색.파라메트릭서치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S2_13702_이상한_술집
 * 이분탐색, 매개 변수 탐색
 */
public class S2_13702_이상한_술집 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 10^5
        int K = Integer.parseInt(st.nextToken()); // 10^6, 항상 N<=K
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        long lo = 0;
        long hi = Integer.MAX_VALUE;
        while (lo + 1 < hi) {
            long mid = lo + hi >> 1;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += arr[i] / mid;
            }
            if(cnt < K){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return lo;
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