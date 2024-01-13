package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * D5 Risk
 * https://www.acmicpc.net/problem/3666
 */
public class D5_Risk {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            //입력
            int N = parseInt(br.readLine());

            Node.init(N + 1, 2);

            boolean[] isEnemy = new boolean[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node source = Node.of(0, 0);
            for (int i = 1; i <= N; i++) {
                int capacity = parseInt(st.nextToken());
                if (capacity == 0) {
                    isEnemy[i] = true;
                } else {
                    source.addEdge(Node.of(i, 0), capacity);
                    Node.of(i, 0).addEdge(Node.of(i, 1), MAX_VALUE);
                }
            }

            Node sink = Node.of(0, 1);
            boolean[] isAdjacent = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                String adjacency = br.readLine();
                if (isEnemy[i]) continue;
                for (int j = 1; j <= N; j++) {
                    if (adjacency.charAt(j - 1) == 'N') continue;
                    if (isEnemy[j]) {
                        isAdjacent[i] = true;
                        continue;
                    }
                    Node.of(i, 0).addEdge(Node.of(j, 1), MAX_VALUE);
                }
                Node.of(i, 1).addEdge(sink, MAX_VALUE);
            }


            int lo = 0;
            int hi = 10_000;
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;

                for (int i = 0; i <= N; i++) {
                    for (Node.Edge edge : Node.of(i, 0).edges) {
                        edge.flow = 0;
                    }
                    for (Node.Edge edge : Node.of(i, 1).edges) {
                        edge.flow = 0;
                    }
                }

                for (Node.Edge edge : sink.edges) {
                    if (!isAdjacent[edge.next.id]) {
                        edge.reversed.capacity = 1;
                    } else {
                        edge.reversed.capacity = mid;
                    }
                }

                //upper bound
                if (check(N)) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            sb.append(lo).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    private static boolean check(int N) {
        Node source = Node.of(0, 0);
        Node sink = Node.of(0, 1);
//        int totalFlow = 0;
        while (true) {
            //경로 탐색
            Node[][] prev = new Node[N + 1][2];
            Node.Edge[][] path = new Node.Edge[N + 1][2];
            Queue<Node> queue = new LinkedList<>();
            queue.add(source);
            while (!queue.isEmpty()) {
                Node now = queue.poll();

                for (Node.Edge edge : now.edges) {
                    Node next = edge.next;
                    if (edge.getFreeCapacity() <= 0 || !isNull(prev[next.id][next.time])) continue;
                    prev[next.id][next.time] = now;
                    path[next.id][next.time] = edge;
                    queue.add(next);
                    if (next.equals(sink)) {
                        break;
                    }
                }
            }

            //경로 탐색 실패
            if (prev[sink.id][sink.time] == null) break;

            //경로에서 가능한 최소 유량 탐색
            int flow = MAX_VALUE;
            for (Node node = sink; !node.equals(source); node = prev[node.id][node.time]) {
                flow = Math.min(flow, path[node.id][node.time].getFreeCapacity());
            }

            //유량 갱신
            for (Node node = sink; !node.equals(source); node = prev[node.id][node.time]) {
                Node.Edge edge = path[node.id][node.time];
                edge.flow += flow;
                edge.reversed.flow -= flow;
            }
//            totalFlow += flow;
        }

        return sink.edges.stream()
                .mapToInt(e -> e.reversed.getFreeCapacity())
                .allMatch(fc -> fc == 0);

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
            Edge reversed = new Edge(this, 0);
            edge.reversed = reversed;
            reversed.reversed = edge;
            edges.add(edge);
            next.edges.add(reversed);
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
