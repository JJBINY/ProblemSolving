package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * G5 17178 줄서기
 * 스택
 */
public class G5_17178_줄서기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getA)
                .thenComparingInt(Pair::getB));

        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                String[] split = st.nextToken().split("-");
                Pair ticket = new Pair(split[0], parseInt(split[1]));
                pq.add(ticket);
                queue.add(ticket);
            }
        }

        Stack<Pair> stack = new Stack<>();
        while (!queue.isEmpty()) {
            if (queue.peek() == pq.peek()) {
                pq.poll();
                queue.poll();
            } else if (!stack.isEmpty() && stack.peek() == pq.peek()) {
                pq.poll();
                stack.pop();
            } else {
                stack.push(queue.poll());
            }
        }
        while (!stack.isEmpty()) {
            if(stack.peek() == pq.peek()){
                stack.pop();
                pq.poll();
            }else{
                break;
            }
        }
        if(pq.isEmpty()){
            System.out.println("GOOD");
        }else{
            System.out.println("BAD");
        }
    }

    static class Pair{
        String a;
        int b;

        public Pair(String a, int b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    '}';
        }
    }

}
