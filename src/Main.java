import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 학교가지마!
 * https://www.acmicpc.net/problem/1420
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        Node.init(N * M, 2);

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        Node source = null;
        Node sink = null;
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = arr[i].charAt(j);
                Node in = Node.of(i * M + j, 0);
                Node out = Node.of(i * M + j, 1);
                if (c == 'K') {
                    source = out;
                } else if (c == 'H') {
                    sink = in;
                } else if (c == '#') {
                    continue;
                } else {
                    in.addEdge(out, 1);
                }

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (arr[nr].charAt(nc) == '#') continue;
                    out.addEdge(Node.of(nr * M + nc, 0), 1);
                }
            }
        }

        for (Node.Edge edge : source.edges) {
            if (edge.next.equals(sink)) {
                System.out.println(-1);
                return;
            }
        }

        int totalFlow = 0;
        while (true) {
            //경로 탐색
            Node[][] prev = new Node[N * M][2];
            Node.Edge[][] path = new Node.Edge[N * M][2];
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
            totalFlow += flow;
        }
        System.out.print(totalFlow);
        br.close();
    }

    private static boolean check(int N) {
        Node source = Node.of(0, 0);
        Node sink = Node.of(0, 1);
//        int totalFlow = 0;


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
