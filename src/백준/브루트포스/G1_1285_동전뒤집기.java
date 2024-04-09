package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G1 1285 동전 뒤집기
 * 완전탐색, 비트마스킹, 그리디
 */
public class G1_1285_동전뒤집기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;
    static int[] coins;

    static void solve(BufferedReader br) throws IOException {
        init(br);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << N); i++) {
            int rst = 0;
            for (int j = 0; j < N; j++) {
                int bin = coins[j] ^ i;
                int cnt = 0;
                for (int k = 0; k < N; k++) {
                    if((bin & 1<<k)>0) cnt++;
                }
                rst += Math.min(cnt, N - cnt);
            }
            ans = Math.min(ans, rst);
        }
        System.out.println(ans);
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        coins = new int[N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'T') {
                    coins[i] += 1 << j;
                }
            }
        }
    }
}
