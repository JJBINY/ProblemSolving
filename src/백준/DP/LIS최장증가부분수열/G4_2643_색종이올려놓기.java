package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 2643 색종이 올려 놓기
 * DP, 정렬, LIS
 */
public class G4_2643_색종이올려놓기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        int n = parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            if(a<b){
                arr[i] = new Pair(a, b);
            }else{
                arr[i] = new Pair(b, a);
            }
        }

        Arrays.sort(arr, Comparator
                .comparingInt((Pair p) -> -p.b)
                .thenComparingInt(p->-p.a));

        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(arr[i].a <= arr[j].a && arr[i].b <= arr[j].b){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        } // fori
        System.out.println(ans);
    }

    static class Pair{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
