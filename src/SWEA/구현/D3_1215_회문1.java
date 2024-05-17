package SWEA.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 1215. [S/W 문제해결 기본] 3일차 - 회문1 D3
 * 구현
 */
public class D3_1215_회문1 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
//			int T  = Integer.parseInt(br.readLine());
            int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Object solve(BufferedReader br) throws Exception {
        int result = 0;
        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[8][8];
        for (int i = 0; i < 8; i++) {
            arr[i] = br.readLine().toCharArray();
        } // for i

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= 8-N; j++) {
                for (int k = 0; k < N/2; k++) {
                    if(arr[i][j+k] != arr[i][j+N-1-k]) break;
                    else if(k == N/2-1) result++;
                } // for k
                for (int k = 0; k < N/2; k++) {
                    if(arr[j+k][i] != arr[j+N-1-k][i]) break;
                    else if(k == N/2-1) result++;
                } // for k
            } // for j
        } // for i

        return result;
    }
}
