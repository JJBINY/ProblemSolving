package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 22942 데이터 체커
 * 스택, 기하학, 정렬
 */
public class G4_22942_데이터체커 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int r = parseInt(st.nextToken());
            pq.add(new Pair(i, x - r));
            pq.add(new Pair(i, x + r));
        }

        Stack<Pair> stack = new Stack<>();
        while (!pq.isEmpty()){
            if(stack.isEmpty()){
                stack.push(pq.poll());
            }else{
                Pair pair = pq.poll();
                if(stack.peek().x == pair.x){
                    System.out.println("NO");
                    return;
                }else if(stack.peek().id == pair.id){
                    stack.pop();
                }else{
                    stack.push(pair);
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    static class Pair{
        int id;
        int x;

        public Pair(int id, int x) {
            this.id = id;
            this.x = x;
        }
    }
}
