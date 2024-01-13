package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 Grid(격자0만들기)
 * https://www.acmicpc.net/problem/11495
 */
public class P2_격자0만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());

            Node.init(N * M + 1);
            Node source = Node.of(N * M);
            Node sink = Node.of(N * M + 1);
            int idx = 0;
            Node[][] nodes = new Node[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int capacity = parseInt(st.nextToken());
                    nodes[i][j] = Node.of(idx++);
                    if ((i + j) % 2 == 0) {
                        source.addEdge(nodes[i][j], capacity);
                    } else {
                        nodes[i][j].addEdge(sink, capacity);
                    }
                }
            }

            int[] dr = new int[]{0, 0, 1, -1};
            int[] dc = new int[]{1, -1, 0, 0};
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if ((i + j) % 2 == 0) {
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                            nodes[i][j].addEdge(nodes[nr][nc], MAX_VALUE);
                        }
                    }
                }
            }


            int totalFlow = 0;
            while (true) {
                //경로 탐색
                Node[] prev = new Node[N * M + 2];
                Node.Edge[] path = new Node.Edge[N * M + 2];
                Queue<Node> queue = new LinkedList<>();
                queue.add(source);
                while (!queue.isEmpty()) {
                    Node now = queue.poll();
                    for (Node.Edge edge : now.edges) {
                        Node next = edge.next;
                        if (edge.getFreeCapacity() <= 0 || !isNull(prev[next.id])) continue;
                        prev[next.id] = now;
                        path[next.id] = edge;
                        queue.add(next);
                        if (next.equals(sink)) break;
                    }
                }

                //경로 탐색 실패
                if (isNull(prev[sink.id])) break;

                //경로에서 가능한 최소 유량 탐색
                int flow = MAX_VALUE;
                for (Node node = sink; !node.equals(source); node = prev[node.id]) {
                    flow = Math.min(flow, path[node.id].getFreeCapacity());
                }

                //유량 갱신
                for (Node node = sink; !node.equals(source); node = prev[node.id]) {
                    Node.Edge edge = path[node.id];
                    edge.flow += flow;
                    edge.reversed.flow -= flow;
                }
                totalFlow += flow;
            }

            int sum1 = source.edges.stream().mapToInt(Node.Edge::getFreeCapacity).sum();
            int sum2 = sink.edges.stream().mapToInt(e -> e.reversed.getFreeCapacity()).sum();
            ans.append(totalFlow + sum1 + sum2).append("\n");
        }
        System.out.print(ans);
        br.close();
    }

    static class Node {
        final int id;
        final List<Edge> edges = new ArrayList<>();

        static Node[] nodes;

        static public Node of(int id) {
            if (isNull(nodes[id])) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        static public void init(int N) {
            nodes = new Node[N + 1];
        }

        private Node(int id) {
            this.id = id;
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
            Node next;
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
