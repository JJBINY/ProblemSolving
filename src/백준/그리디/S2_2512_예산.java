package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S2_2512_예산
 * 그리디
 */
public class S2_2512_예산 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int totalBudget = Integer.parseInt(br.readLine());
        int res = 0;
        for (int reqBudget : arr) {
            if(reqBudget*N <= totalBudget){
                res = reqBudget;
                totalBudget -= reqBudget;
            }else{
                res = Math.max(res, totalBudget/N);
                break;
            }
            N--;
        }
        return res;
    }

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
}