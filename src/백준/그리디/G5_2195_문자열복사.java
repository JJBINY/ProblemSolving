package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G5 2195 문자열 복사 25m
 * 문자열, 그리디
 */
public class G5_2195_문자열복사 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        String S = br.readLine();
        String P = br.readLine();

        int idx = 0;
        int ans = 0;

        while (idx<P.length()) {
            idx += findMaxMatchCount(S, P, idx);
            ans++;
        }
        System.out.println(ans);
    }

    private static int findMaxMatchCount(String S, String P, int idx) {
        int p = 0;
        int cnt = 0;
        for (int s = 0; s < S.length(); s++) {
            if (idx+p >= P.length()) break;
            if (P.charAt(idx+p) == S.charAt(s)) {
                p++;
            }else{
                cnt = Math.max(cnt, p);
                p = 0;
            }
        }

        return Math.max(cnt, p);
    }
}