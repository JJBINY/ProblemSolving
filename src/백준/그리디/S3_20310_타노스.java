package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S3_20310_타노스
 * 그리디, 구현
 */
public class S3_20310_타노스 {
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
    사전순으로 빠른 것 => 0을 가능한 앞에 배치 == 1을 가능한 뒤에 배치
     */
    static Object solve(BufferedReader br) throws IOException {
        String S = br.readLine();
        StringBuilder sb = new StringBuilder();
        int nZero = (int) Arrays.stream(S.split("")).filter(s -> s.equals("0")).count();
        int nOne = S.length() - nZero;

        int cntZero = 0;
        int cntOne = 0;
        for (char c : S.toCharArray()) {
            if (c == '0') {
                if (cntZero++ < nZero / 2) {
                    sb.append(c);
                }
            }else{ // c == '1'
                if(cntOne++ >= nOne/2) {
                    sb.append(c);
                }
            }
        }
        return sb;
    }
}