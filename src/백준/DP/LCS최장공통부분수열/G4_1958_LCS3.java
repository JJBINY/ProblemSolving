package 백준.DP.LCS최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G4 1958 LCS3
 * DP, LCS
 */
public class G4_1958_LCS3 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();
        int[][][] LCS = new int[A.length()+1][B.length()+1][C.length()+1];

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                for (int k = 1; k <= C.length(); k++) {
                    if (A.charAt(i-1) == B.charAt(j-1) && B.charAt(j-1) == C.charAt(k-1)) {
                        LCS[i][j][k] = LCS[i - 1][j - 1][k - 1] + 1;
                    } else {
                        LCS[i][j][k] = Math.max(LCS[i - 1][j][k], Math.max(LCS[i][j - 1][k], LCS[i][j][k - 1]));
                    }
                }

            }
        }
        System.out.println(LCS[A.length()][B.length()][C.length()]);
    }
}
