package 백준.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import static java.lang.Integer.parseInt;

/**
 * S5 14729 칠무해
 * 정렬
 */
public class S5_14729_칠무해 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int N = parseInt(br.readLine());
            PriorityQueue<Float> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < N; i++) {
                Float score = Float.parseFloat(br.readLine());
                if (pq.size() >= 7) {
                    if (pq.peek() > score) {
                        pq.poll();
                        pq.add(score);
                    }
                } else {
                    pq.add(score);
                }
            }

            Stack<Float> stack = new Stack<>();
            for (int i = 0; i < 7; i++) {
                stack.push(pq.poll());
            }
            for (int i = 0; i < 7; i++) {
                sb.append(String.format("%.3f",stack.pop())).append("\n");
            }


            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
