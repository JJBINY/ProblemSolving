package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G5 7983 내일 할거야
 * 그리디, 정렬
 */
public class G5_7983_내일할거야 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair<Integer,Integer>::getB).reversed());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Pair<>(parseInt(st.nextToken()), parseInt(st.nextToken())));
        }

        int day = pq.peek().b - pq.poll().a;
        while (!pq.isEmpty()){
            Pair<Integer, Integer> task = pq.poll();
            day = Math.min(day, task.b) - task.a;
        }
        System.out.println(day);
    }


    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }

        public T1 getA() {
            return a;
        }

        public T2 getB() {
            return b;
        }
    }

}
