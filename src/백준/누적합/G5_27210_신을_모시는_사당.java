package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5_27210_신을_모시는_사당
 * 누적합
 */
public class G5_27210_신을_모시는_사당 {

    /*
    10
    2 2 1 2 2 1 1 1 1 1

    ans: 5
     */
    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine()); // <=10^5 -> O(NlogN)이내 풀이
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(i -> i == 2 ? -1 : 1).toArray();

        int[] pSum = new int[N+1];
        for (int i = 0; i < N; i++) {
            pSum[i+1] = pSum[i] + arr[i];
        }
        int min = Arrays.stream(pSum).min().getAsInt();
        return Arrays.stream(pSum)
                .map(i -> i - min)
                .max().getAsInt();
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