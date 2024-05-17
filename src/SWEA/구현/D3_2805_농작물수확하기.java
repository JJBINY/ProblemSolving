package SWEA.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
/**
 * 2805. 농작물 수확하기 D3
 * 구현
 */
public class D3_2805_농작물수확하기 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = parseInt(br.readLine());
            //			int T  = 10;
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

    static int N;
    static int[][] arr;

    public static Object solve(BufferedReader br) throws Exception {
        N = parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
        int result = Arrays.stream(arr[N/2]).sum();
        for (int i = 1; i <= N/2; i++) {
            for (int j = i; j < N-i; j++){
                result += arr[N/2-i][j];
                result += arr[N/2+i][j];
            }
        }
        return result;

    }



}
