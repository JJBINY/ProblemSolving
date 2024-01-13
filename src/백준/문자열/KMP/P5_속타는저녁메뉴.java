package 백준.문자열.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * P5 속타는 저녁 메뉴
 * https://www.acmicpc.net/problem/11585
 */
public class P5_속타는저녁메뉴 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int denominator = Integer.parseInt(br.readLine());
        String pattern = br.readLine().replaceAll(" ","");
        String s = br.readLine().replaceAll(" ","");

        int numerator=kmp(s + s.substring(0,s.length()-1), pattern).size();
        int gcd = gcd(denominator, numerator);
        numerator/=gcd;
        denominator/=gcd;
        System.out.println(numerator+"/"+denominator);
        br.close();
    }

    static int gcd(int p, int q){
        if(q==0) return p;
        return gcd(q, p % q);
    }

    static List<Integer> kmp(String text, String pattern) {
        int[] pi = getPrefixTable(pattern);

        List<Integer> indices = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    indices.add(i - j);
                    j = pi[j];
                    continue;
                }
                j++;
            }
        }
        return indices;
    }

    static int[] getPrefixTable(String pattern) {
        int[] pi = new int[pattern.length()];

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }


}