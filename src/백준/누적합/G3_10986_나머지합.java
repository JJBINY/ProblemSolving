package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G3_10986_나머지합
 * 누적합, 조합
 */
public class G3_10986_나머지합 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] pSum = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        long[] cnt = new long[M];
        for (int i = 0; i <= N; i++) {
            cnt[(int)(pSum[i]%M)]++;
        }

        long ans = 0;
        for(int i = 0;i<M; i++){
//            System.out.println("cnt["+i+"] = " + cnt[i]);
            if(cnt[i]<2) continue;
            ans += cnt[i]*(cnt[i]-1)/2;
        }
        System.out.println(ans);
    }

}