package 백준.문자열.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * P5 문자열 제곱
 * https://www.acmicpc.net/problem/4354
 */
public class P5_문자열제곱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            if (s.equals(".")) {
                return;
            }
            List<Integer> indices = kmp(s + s, s);
            System.out.println(indices.size() - 1);
        }

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