package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S2_14465_소가_길을_건너간_이유_5
 * 누적합, 슬라이딩 윈도우
 */
public class S2_14465_소가_길을_건너간_이유_5 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] broken = new boolean[N+1];
        for (int i = 1; i <= B; i++) {
            broken[Integer.parseInt(br.readLine())] = true;
        }

        int res = Integer.MAX_VALUE;
        int[] psum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if(broken[i]){
                psum[i] = 1;
            }
            psum[i] += psum[i - 1];
            if(i>=K) res = Math.min(res, psum[i] - psum[i - K + 1]);
        }

        return res;
    }
}