package 백준.DP.LCS최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G4 LCS 2
 * https://www.acmicpc.net/problem/9252
 * 최장공통부분수열
 */
public class G4_LCS2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] LCS = new int[s1.length()+1][s2.length()+1];
        int max = 0;
        int x = 0;

        for (int i = 0; i < s1.length()+1; i++) {
            for (int j = 0; j < s2.length()+1; j++) {
                if(i==0||j==0) {
                    LCS[i][j] = 0;
                }
                else if (s1.charAt(i-1)==s2.charAt(j-1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;

                }
                else {
                    LCS[i][j] = Math.max(LCS[i-1][j],LCS[i][j-1]);
                }
            }
        }

        int i = s1.length();
        int j = s2.length();

        System.out.println(LCS[i][j]);
        if(LCS[i][j] == 0) return;

        StringBuilder sb = new StringBuilder();
        while (LCS[i][j]!=0) {
            if(LCS[i][j-1] == LCS[i][j]){
                j -= 1;
            }else if(LCS[i-1][j] == LCS[i][j]){
                i -= 1;
            }else{
                sb.append(s1.charAt(i - 1));
                i-=1;
                j-=1;
            }
        }
        System.out.println(sb.reverse().toString());
    }
}