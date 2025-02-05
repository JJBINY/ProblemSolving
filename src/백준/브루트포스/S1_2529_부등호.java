package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * S1_2529_부등호
 * 브루트포스
 */
public class S1_2529_부등호 {

    static String[] arr;

    static Object solve(BufferedReader br) throws IOException {
        int k = Integer.parseInt(br.readLine());
        arr = br.readLine().split(" ");

        int[] A = new int[k + 1];
        Arrays.fill(A, -1);
        int val = 9;
        int idx = 0;
        while (idx  < k + 1) {
            int cnt = 1;
            while (idx < k && arr[idx].equals("<")) {
                idx++;
                cnt++;
            }

            for (int i = 0; i < cnt; i++) {
                A[idx-i] = val--;
            }
            idx++;
        }

        val = 0;
        idx = 0;
        int[] B = new int[k + 1];
        while (idx  < k + 1) {
            int cnt = 1;
            while (idx < k && arr[idx].equals(">")) {
                idx++;
                cnt++;
            }

            for (int i = 0; i < cnt; i++) {
                B[idx-i] = val++;
            }
            idx++;
        }

        StringBuilder sb = new StringBuilder();

        return sb.append(Arrays.toString(A).replaceAll("[\\[\\], ]", ""))
                .append("\n")
                .append(Arrays.toString(B).replaceAll("[\\[\\], ]", ""));

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