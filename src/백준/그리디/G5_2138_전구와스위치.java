package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5 2138 전구와 스위치
 * 그리디
 */
public class G5_2138_전구와스위치 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] S = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int[] clone = S.clone();
        int[] T = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        // 000 or 110
        // 010
        int ans = simulate(S, T);
        if (check(S, T)) {
            System.out.println(ans);
            return;
        }

        S = clone;
        toggle(S, 0);
        toggle(S, 1);
        ans = simulate(S, T)+1;
        if (check(S, T)) {
            System.out.println(ans);
        }else{
            System.out.println(-1);
        }
    }

    private static int simulate(int[] S, int[] T) {
        int ans = 0;
        for (int i = 1; i < S.length; i++) {
            if (S[i - 1] != T[i - 1]) {
                ans++;
                toggle(S, i - 1);
                toggle(S, i);
                if(i< S.length -1) toggle(S, i + 1);
            }
        }
        return ans;
    }

    private static boolean check(int[] S, int[] T) {
        for (int i = 0; i < S.length; i++) {
            if (S[i] != T[i]) return false;
        }
        return true;
    }

    private static void toggle(int[] S, int i) {
        S[i] = (S[i] + 1) % 2;
    }
}