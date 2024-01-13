package 백준.문자열.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * P5 찾기
 * https://www.acmicpc.net/problem/1786
 */
public class P5_찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> indices = kmp(br.readLine(), br.readLine());
        StringBuilder sb = new StringBuilder();
        for (Integer index : indices) {
            sb.append(index+1 + "\n");
        }
        System.out.println(indices.size());
        System.out.println(sb.toString());
    }

    static List<Integer> kmp(String text, String pattern){
        int[] pi = getPrefixTable(pattern);

        List<Integer> indices = new ArrayList<>();
        int matched = 0;
        for (int i = 0; i < text.length(); i++) {
            while (matched>0 && text.charAt(i)!=pattern.charAt(matched)){
                matched = pi[matched - 1];
            }

            if(text.charAt(i) == pattern.charAt(matched)){
                if (matched == pattern.length()-1) {
                    indices.add(i - matched);
                    matched = pi[matched];
                    continue;
                }
                matched++;
            }
        }
        return indices;
    }

    static int[] getPrefixTable(String pattern){
        //index까지의 부분 문자열에서 접두사와 접미사가 일치하는 최대 길이
        int[] pi = new int[pattern.length()];

        int matched = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (matched>0 && pattern.charAt(i)!=pattern.charAt(matched)){
                matched = pi[matched - 1]; //
            }
            if (pattern.charAt(i) == pattern.charAt(matched)) {
                pi[i] = ++matched;
            }
        }
        return pi;
    }
}