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
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        //입력
        int N = parseInt(br.readLine());
        int MAX_LEN = N;

        int[] inTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] outTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int T = parseInt(br.readLine()); //과속기준시간
        int F = parseInt(br.readLine()); //벌금최댓값

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
                int fine = -Math.min(F, getFine(T, outTimes[j] - inTimes[i]));
                System.out.println("fine = " + fine);
                Node.of(i, 0).addEdge(Node.of(j, 1), 1, fine);
            }
        }

        int minCost = 0;
        while(true){
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
            System.out.println("flow = " + flow);
        }
        sb.append(-minCost).append("\n");

        System.out.print(sb.toString());

        br.close();
    }

    private static int getFine(int T, int diff) {
        if(diff>=T) return (int)Math.pow(T - diff,2);
        return 0;
//        return (int)Math.pow(T - diff,2);
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
