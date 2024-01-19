package 백준.그래프.최대유량.MCMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 9413 제주도 관광
 * MCMF, 최대유량
 */
public class P2_9413_제주도관광 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {

            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());
            int MAX_LEN = N;
            Node.init(MAX_LEN + 1, 2);
            Node source = Node.of(0, 1);
            Node sink = Node.of(0, 0);
            for (int i = 1; i <= N; i++) {
                source.addEdge(Node.of(i, 0), 1, -1);
                Node.of(i, 0).addEdge(Node.of(i, 1), 1, 0);
                Node.of(i, 1).addEdge(sink, 1, 0);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = parseInt(st.nextToken());
                int b = parseInt(st.nextToken());
                Node.of(a, 1).addEdge(Node.of(b, 0), 1, -1);
            }

            int minCost = 0;
            for (int group = 0; group < 2; group++) {
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
            }
            sb.append(-minCost).append("\n");
        }
        System.out.println(sb.toString());

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
