package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 1826 연료 채우기
 * 그리디, 우선순위 큐
 */
public class G2_1826_연로채우기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());

        PriorityQueue<Pair> stations = new PriorityQueue<>(Comparator.comparingInt(p -> p.loc));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            stations.add(new Pair(a, b));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = parseInt(st.nextToken());
        int P = parseInt(st.nextToken());

        int[] costs = new int[L + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator
                .comparingInt((Pair p) -> p.fuel)
                .reversed());
        while (!stations.isEmpty() && P >= stations.peek().loc) {
            pq.add(stations.poll());
        }

        int cnt = 0;
        while (!pq.isEmpty()) {
            if (P >= L) {
                System.out.println(cnt);
                return;
            }

            Pair station = pq.poll();
            P += station.fuel;
            cnt++;

            while (!stations.isEmpty() && P >= stations.peek().loc) {
                pq.add(stations.poll());
            }
        }

        System.out.println(-1);
    }

    static class Pair {
        int loc;
        int fuel;

        public Pair(int loc, int fuel) {
            this.loc = loc;
            this.fuel = fuel;
        }
    }

}
/*
6
5 6
7 8
6 10
11 5
13 2
18 5
30 10

ans 3
 */