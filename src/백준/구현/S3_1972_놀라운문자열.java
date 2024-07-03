package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


/**
 * S3_1972_놀라운문자열
 * 구현, 문자열, 자료구조, 집합(set)
 */
public class S3_1972_놀라운문자열 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            String input;
            while (!(input = br.readLine()).equals("*")){
                ans.append(input);
                ans.append(solve(input));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(String s) throws IOException {
        int N = s.length();
        for (int d = 1; d < N; d++) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < s.length()-d; i++) {
                String pair = new StringBuilder().append(s.charAt(i)).append(s.charAt(i + d)).toString();
                if(!set.add(pair)){
                    return " is NOT surprising.";
                }
            }
        }
        return " is surprising.";
    }

}