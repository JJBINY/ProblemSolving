package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * S4_2960_에라토스테네스의_체
 * 소수 판정, 에라토스테네스의 체
 */
public class S4_2960_에라토스테네스의_체 {
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

        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true); // -> 1

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if(isPrime[i] && --K==0){
                return i; // -> 2
            }
            isPrime[i] = false;

            for (int j = i * i; j <= N; j += i) {
                if(isPrime[j] && --K == 0) return j; // -> 3
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= N; i++) {
            if(isPrime[i] && --K == 0) return i; // -> 3
        }

        return -1;
    }


}