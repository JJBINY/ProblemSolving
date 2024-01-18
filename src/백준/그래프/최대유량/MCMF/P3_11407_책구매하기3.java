package 백준.그래프.최대유량.MCMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P3 11407 책 구매하기3
 * MCMF, 최대유량
 */
public class P3_11407_책구매하기3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int MAX_LEN = Math.max(N, M);
        Node.init(MAX_LEN + 1, 2);


        Node sink = new Node(0, 0);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int capacity = parseInt(st.nextToken());
            Node.of(i, 0).addEdge(sink, capacity, 0);
        }

        Node source = new Node(0, 1);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int capacity = parseInt(st.nextToken());
            source.addEdge(Node.of(i, 1), capacity, 0);
        }

        String[] inputs = new String[M+1];
        for (int i = 1; i <= M; i++) {
            inputs[i] = br.readLine();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(inputs[i]);
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            Node bookStore = Node.of(i, 1);
            for (int j = 1; j <= N; j++) {
                int capacity = parseInt(st.nextToken());
                int cost = parseInt(st2.nextToken());
                bookStore.addEdge(Node.of(j, 0), capacity, cost);
            }
        }
        int totalFlow = 0;
        int minCost = 0;
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
            totalFlow += flow;
        }
        System.out.println(totalFlow);
        System.out.println(minCost);

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
