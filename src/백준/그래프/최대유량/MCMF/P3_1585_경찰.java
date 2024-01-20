package 백준.그래프.최대유량.MCMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P3 1585 경찰
 * MCMF, 최대유량
 */
public class P3_1585_경찰 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int N = parseInt(br.readLine());
        int MAX_LEN = N;

        int[] inTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] outTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int T = parseInt(br.readLine()); //과속기준시간
        int F = parseInt(br.readLine()); //벌금최댓값
        br.close();

        int minCost = getMinCost(N, MAX_LEN, inTimes, outTimes, T, F, 1);
        System.out.print(minCost);
        if (minCost < 0) {
            return;
        }
        int maxCost = -getMinCost(N, MAX_LEN, inTimes, outTimes, T, F, -1);
        System.out.println(" " + maxCost);

    }

    private static int getMinCost(int N, int MAX_LEN, int[] inTimes, int[] outTimes, int T, int F, int flag) {
        //간선연결
        Node.init(MAX_LEN + 1, 2);
        Node source = Node.of(N, 1);
        Node sink = Node.of(N, 0);
        for (int i = 0; i < N; i++) {
            source.addEdge(Node.of(i, 0), 1, 0);
            Node.of(i, 1).addEdge(sink, 1, 0);
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (inTimes[i] >= outTimes[j]) continue;
                int fine = flag * getFine(F, T, outTimes[j] - inTimes[i]);
                Node.of(i, 0).addEdge(Node.of(j, 1), 1, fine);
            }
        }

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
        }
        if (maxFlow < N) {
            minCost = -1;
        }
        return minCost;
    }

    private static int getFine(int F, int T, int diff) {
        if (diff < T) return Math.min(F, (int) Math.pow(T - diff, 2));
        return 0;
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
