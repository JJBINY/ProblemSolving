package SWEA.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1220. [S/W 문제해결 기본] 5일차 - Magnetic D3
 * 구현
 */
public class D3_1220_Magnetic {

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
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < 100; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        } // for i

        int result = 0;
        for (int c = 0; c < 100; c++) {
            boolean meetN = false;
            for(int r = 0; r<100;r++) {
                if(arr[r][c] == 1) {
                    meetN = true;
                }else if(arr[r][c] == 2) {
                    if(meetN) {
                        result++;
                    }
                    meetN=false;
                }
            }
        } // for i

        return result;
    }
}
