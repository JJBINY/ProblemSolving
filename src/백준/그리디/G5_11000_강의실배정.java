package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * G5 11000 강의실 배정
 * 그리디, 우선순위 큐
 */
public class G5_11000_강의실배정 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.s));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.add(new Pair(s, t));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(pq.poll().t);
        while (!pq.isEmpty()){
            Pair now = pq.poll();
            if(now.s >=rooms.peek()){
                rooms.poll();
            }
            rooms.add(now.t);
        }
        System.out.println(rooms.size());
    }

    static class Pair{
        int s;
        int t;

        public Pair(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }
}