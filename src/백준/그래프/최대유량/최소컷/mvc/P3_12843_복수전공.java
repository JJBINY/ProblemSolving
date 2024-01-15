package 백준.그래프.최대유량.최소컷.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P3 12843 복수전공
 * 중복제거 = s와c사이 간선x인 정점의 집합 =  minimum vertex cover제거
 * 이분매칭
 */
public class P3_12843_복수전공 {

    static boolean[] visited;
    static Node[] assigned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        boolean[] isCMajor = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = parseInt(st.nextToken());
            if (st.nextToken().equals("c")) {
                isCMajor[id] = true;
            }
        }

        //간선연결
        Node.init(N + 1, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());

            if (isCMajor[a]) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            Node.of(a, 0).addEdge(Node.of(b, 0), 1);
        }

        assigned = new Node[N + 1];
        visited = new boolean[N + 1];
        int matched = 0;
        for (int i = 1; i <= N; i++) {
            if (isCMajor[i]) continue;
            Arrays.fill(visited, false);
            if (match(Node.of(i, 0))) matched++;
        }

        System.out.print(N - matched);
        br.close();
    }

    public static boolean match(Node a) {
        for (Node.Edge edge : a.edges) {
            Node b = edge.next;
            if (visited[b.id]) continue;
            visited[b.id] = true;
            if (Objects.isNull(assigned[b.id]) || match(assigned[b.id])) {
                assigned[b.id] = a;
                return true;
            }
        }
        return false;
    }


    static class Node {
        final int id;
        final int time;
        final List<Edge> edges = new ArrayList<>();

        private static Node[][] nodes;

        static public Node of(int id, int time) {
            if (isNull(nodes[id][time])) {
                nodes[id][time] = new Node(id, time);
            }
            return nodes[id][time];
        }

        static public void init(int N, int T) {
            nodes = new Node[N][T];
        }

        private Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public void addEdge(Node next, int cap) {
            Edge edge = new Edge(next, cap);
//            Edge reversed = new Edge(this, 0);
//            edge.reversed = reversed;
//            reversed.reversed = edge;
            edges.add(edge);
//            next.edges.add(reversed);
//            System.out.println(String.format("addEdge : %d - %d",this.id,next.id));
        }

        static class Edge {
            final Node next;
            Edge reversed;
            int capacity;
            int flow;

            public Edge(Node next, int capacity) {
                this.next = next;
                this.capacity = capacity;
            }

            public int getFreeCapacity() {
                return capacity - flow;
            }
        }
    }
}
