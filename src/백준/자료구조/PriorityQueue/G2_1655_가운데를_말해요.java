package 백준.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * G2_1655_가운데를_말해요
 * 자료구조, 우선순위 큐, 힙
 */
public class G2_1655_가운데를_말해요 {
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
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int number = Integer.parseInt(br.readLine());
        maxHeap.offer(number);

        StringBuilder sb = new StringBuilder();
        sb.append(number).append(" ");

        for (int i = 0; i < N-1; i++) {
            number = Integer.parseInt(br.readLine());
            /*
                1. maxHeap이 minHeap보다 사이즈 1 크거나 같도록 유지
                2. (maxHeap의 가장 큰 값) <= (minHeap의 가장 작은 값)이 되도록 유지
             */

            if (maxHeap.size() == minHeap.size() + 1) {
                if (number > maxHeap.peek()) {
                    // number삽입 후에도 maxHeap의 피크가 중간값으로 유지됨
                    minHeap.offer(number);
                }else{
                    // number가 새로운 중간값이 되므로 기존 maxHeap의 피크를 minHeap으로 옮겨 높이 맞춤
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(number);
                }
            } else {
                if(number > minHeap.peek()){
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(number);
                }else{
                    maxHeap.offer(number);
                }
            }
            sb.append(maxHeap.peek()).append(" ");
        }
        return sb;
    }
}