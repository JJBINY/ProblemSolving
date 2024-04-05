package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 1374 강의실
 * 그리디, 정렬
 */
public class G5_1374_강의실 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());

        PriorityQueue<Lecture> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.s));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int id = parseInt(st.nextToken());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());
            pq.offer(new Lecture(id, start, end));
        }

        int ans = 0;
        PriorityQueue<Lecture> pq2 = new PriorityQueue<>(Comparator.comparingInt(l -> l.e));
        while (!pq.isEmpty()) {
            Lecture now = pq.poll();
            while (!pq2.isEmpty() && now.s >= pq2.peek().e) {
                pq2.poll();
            }
            pq2.offer(now);
            ans = Math.max(ans, pq2.size());
        }

        System.out.println(ans);
    }

    static class Lecture {
        int id;
        int s;
        int e;

        public Lecture(int id, int s, int e) {
            this.id = id;
            this.s = s;
            this.e = e;
        }
    }
}