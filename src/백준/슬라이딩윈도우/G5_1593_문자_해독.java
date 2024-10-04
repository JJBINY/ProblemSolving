package 백준.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * G5_1593_문자_해독
 * 문자열, 슬라이딩 윈도우
 */
public class G5_1593_문자_해독 {
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
        int g = Integer.parseInt(st.nextToken());
        int lenS = Integer.parseInt(st.nextToken());
        String W = br.readLine();
        String S = br.readLine();

        int[] cnt = new int[52];
        Set<Character> set = new HashSet<>();
        for (char c : W.toCharArray()) {
            cnt[getIdx(c)]++;
            set.add(c);
        }

        Arrays.equals(cnt, cnt);
        for (int i = 0; i < g; i++) {
            if (set.contains(S.charAt(i))) cnt[getIdx(S, i)]--;
        }

        int res = 0;
        if (isPossible(cnt)) res++;
        for (int i = g; i < lenS; i++) {
            if (set.contains(S.charAt(i))) cnt[getIdx(S, i)]--;
            if (set.contains(S.charAt(i - g))) cnt[getIdx(S, i-g)]++;
            if (isPossible(cnt)) res++;
        }
        return res;
    }

    static boolean isPossible(int[] cnt) {
        for (int i : cnt) {
            if(i!=0){
                return false;
            }
        }
        return true;
    }

    static int getIdx(String S, int i){
        return getIdx(S.charAt(i));
    }
    static int getIdx(char c) {
        if (isLowerCase(c)) {
            return c - 'a';
        }
        return 26 + c - 'A';
    }

    static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }
}