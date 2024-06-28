package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S2_10799_쇠막대기
 * 스위핑
 */
public class S2_10799_쇠막대기 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        String s = br.readLine();
        int res = 0;
        int bar = 0;
        for (int i = 0; i < s.length(); i++) {
            if(isLaser(s, i)){
                bar--;
                res += bar;
            }else if(s.charAt(i) == '('){
                bar++;
            }else if(s.charAt(i) == ')'){
                bar--;
                res++;
            }
        }
        return res;
    }

    private static boolean isLaser(String s, int i) {
        return s.charAt(i) == ')' && s.charAt(i - 1) == '(';
    }
}