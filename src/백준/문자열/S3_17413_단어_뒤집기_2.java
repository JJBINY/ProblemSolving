package 백준.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S3_17413_단어_뒤집기_2
 * 문자열, 구현
 */
public class S3_17413_단어_뒤집기_2 {
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

    /*
        1. 단어 태그 구분
        2. 단어만 뒤집기
     */
    static Object solve(BufferedReader br) throws IOException {
        String S = br.readLine();
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        boolean tag = false;
        for (char c : S.toCharArray()) {
            if(c == ' ' && !tag){ // 단어
                result.append(sb.reverse());
                result.append(c);
                sb = new StringBuilder();
            }else if(c == '<') {
                result.append(sb.reverse());
                result.append(c);
                sb = new StringBuilder();
                tag = true;
            }else if(c == '>') {
                result.append(sb);
                result.append(c);
                sb = new StringBuilder();
                tag = false;
            }else{
                sb.append(c);
            }
        }
        result.append(sb.reverse());
        return result.toString();
    }
}