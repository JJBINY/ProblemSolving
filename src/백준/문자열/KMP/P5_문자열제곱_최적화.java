package 백준.문자열.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * P5 문자열 제곱
 * https://www.acmicpc.net/problem/4354
 * ref: https://dingcoding.tistory.com/231
 */
public class P5_문자열제곱_최적화 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s = br.readLine();
            if (s.equals(".")) {
                return;
            }
            int[] pi = getPrefixTable(s);
            int len = s.length();
            int a = len - pi[s.length() - 1];
            System.out.println(len%a==0?len/a:1);
        }

    }
    static int[] getPrefixTable(String pattern){
        int[] pi = new int[pattern.length()];

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j>0 && pattern.charAt(i)!=pattern.charAt(j)){
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

}