package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * D5_16225_제271회_웰노운컵
 * 그리디, 정렬
 */
public class D5_16225_제271회_웰노운컵 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine()); // [2, 200000]
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        PriorityQueue<Problem> pqB = new PriorityQueue<>(Comparator.comparingInt(p -> p.b));
        for (int i = 0; i < N; i++) {
            pqB.add(new Problem(A[i], B[i]));
        }
        long ans = pqB.poll().a;

        PriorityQueue<Problem> pqA = new PriorityQueue<>(Comparator.comparingInt(p -> -p.a));
        for (int i = 0; i < N/2-1; i++) {
            pqA.offer(pqB.poll());
            pqA.offer(pqB.poll());
            ans += pqA.poll().a;
        }

        return ans;
    }

    static class Problem{
        int a;
        int b;

        public Problem(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}