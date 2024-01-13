package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P3 상어의 저녁식사
 * https://www.acmicpc.net/problem/1671
 */
public class P3_상어의저녁식사 {

    static boolean[] visited = new boolean[51];
    static int[] assigned = new int[51];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int n = Integer.parseInt(br.readLine());

        Shark[] sharks = new Shark[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(i, a, b, c);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Shark s1 = sharks[i];
                Shark s2 = sharks[j];
                if (s1.isStrongerThan(s2)) {
                    addEdge(s1.id, s2.id);
                    addEdge(s1.id + n, s2.id);
                } else if (s2.isStrongerThan(s1)) {
                    addEdge(s2.id, s1.id);
                    addEdge(s2.id + n, s1.id);
                }
            }
        }

        //매칭
        int cnt = 0;
        Arrays.fill(assigned, -1);
        for (int a = 0; a < 2 * n; a++) {
            Arrays.fill(visited, false);
            if (match(a)) cnt++;
        }
        System.out.println(n - cnt);
        br.close();
    }

    private static void addEdge(int a, int b) {
        List<Integer> list = edges.getOrDefault(a, new ArrayList<>());
        list.add(b);
        edges.put(a, list);
    }

    static boolean match(int a) {

        for (int b : edges.getOrDefault(a, new ArrayList<>())) {
            if (visited[b]) continue;
            visited[b] = true;
            if (assigned[b] == -1 || match(assigned[b])) {
                assigned[b] = a;
                return true;
            }
        }
        return false;
    }

    static class Shark {
        int id;
        int size;
        int speed;
        int intelli;

        public Shark(int id, int size, int speed, int intelli) {
            this.id = id;
            this.size = size;
            this.speed = speed;
            this.intelli = intelli;
        }

        public boolean isStrongerThan(Shark s) {
            int c1 = Integer.compare(size, s.size);
            int c2 = Integer.compare(speed, s.speed);
            int c3 = Integer.compare(intelli, s.intelli);
            if (c1 < 0 || c2 < 0 || c3 < 0) {
                return false;
            } else if ((c1 | c2 | c3) == 0 && id > s.id) {
                return false;
            }
            return true;
        }
    }
}
