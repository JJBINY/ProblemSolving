package 백준.그래프.최대유량.MCMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P3 10937 두부 모판 자르기
 * MCMF, 최대유량
 */
public class P3_10937_두부모판자르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int N = parseInt(br.readLine());
        int MAX_LEN = N * N;

        int[][] costs = new int[][]{
                {100, 70, 40, 0},
                {70, 50, 30, 0},
                {40, 30, 20, 0},
                {0, 0, 0, 0}};
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('B', 1);
        map.put('C', 2);
        map.put('F', 3);

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = map.get(line.charAt(j));
            }
        }

        //간선연결
        Node.init(MAX_LEN + 1, 2);
        Node source = Node.of(MAX_LEN, 1);
        Node sink = Node.of(MAX_LEN, 0);

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int now = i * N + j;
                Node.of(now, 0).addEdge(Node.of(now, 1), 1, 0);
                if ((i + j) % 2 != 0) {
                    Node.of(now, 1).addEdge(sink, 1, 0);
                } else {
                    source.addEdge(Node.of(now, 0), 1, 0);
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        int adj = nr * N + nc;
                        Node.of(now, 1).addEdge(Node.of(adj, 0),
                                1, -costs[arr[i][j]][arr[nr][nc]]);
                    }
                }
            }
        }

        int ans = 0;

        int minCost = 0;
        int maxFlow = 0;
        while (true) {
            boolean[][] isInQ = new boolean[MAX_LEN + 1][2];
            int[][] dist = new int[MAX_LEN + 1][2];
            Node[][] prev = new Node[MAX_LEN + 1][2];
            Node.Edge[][] path = new Node.Edge[MAX_LEN + 1][2];
            Queue<Node> Q = new LinkedList<>();

            for (int[] ints : dist) Arrays.fill(ints, MAX_VALUE);
            dist[source.id][source.time] = 0;
            Q.add(source);

            while (!Q.isEmpty()) {
                Node s = Q.poll();
                isInQ[s.id][s.time] = false;

                for (Node.Edge edge : s.edges) {
                    if (edge.getFreeCapacity() <= 0) continue;

                    Node e = edge.to;
                    int d = dist[s.id][s.time] + edge.cost;
                    if (dist[e.id][e.time] > d) {
                        dist[e.id][e.time] = d;
                        prev[e.id][e.time] = s;
                        path[e.id][e.time] = edge;
                        if (!isInQ[e.id][e.time]) {
                            Q.add(e);
                            isInQ[e.id][e.time] = true;
                        }
                    }
                }
            }

            if (isNull(prev[sink.id][sink.time])) break;

            int flow = MAX_VALUE;
            for (Node i = sink; i != source; i = prev[i.id][i.time]) {
                flow = Math.min(flow, path[i.id][i.time].getFreeCapacity());
            }

            for (Node i = sink; i != source; i = prev[i.id][i.time]) {
                Node.Edge p = path[i.id][i.time];
                minCost += flow * p.cost;
                p.flow += flow;
                p.reversed.flow -= flow;
            }
            maxFlow += flow;
            ans = Math.max(ans, -minCost);
        }
        System.out.println(ans);

        br.close();

    }


    static class Node {
        private static Node[][] nodes;
        final List<Edge> edges = new ArrayList<>();

        final int id;
        final int time;

        public Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        static void init(int I, int T) {
            nodes = new Node[I][T];
        }

        static Node of(int id, int time) {
            if (isNull(nodes[id][time])) {
                nodes[id][time] = new Node(id, time);
            }
            return nodes[id][time];
        }

        public void addEdge(Node to, int capacity, int cost) {
            Edge edge = new Edge(to, capacity, cost);
            Edge reverEdge = new Edge(this, 0, -cost);

            edge.reversed = reverEdge;
            reverEdge.reversed = edge;

            this.edges.add(edge);
            to.edges.add(reverEdge);
        }

        static class Edge {
            final Node to;
            final int capacity;
            int flow;
            int cost;
            Edge reversed;

            private Edge(Node to, int capacity, int cost) {
                this.to = to;
                this.capacity = capacity;
                this.cost = cost;
            }

            public int getFreeCapacity() {
                return capacity - flow;
            }
        }
    }

}
