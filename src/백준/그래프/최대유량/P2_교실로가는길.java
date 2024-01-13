package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 도시로 가는 길
 * https://www.acmicpc.net/problem/2316
 */
public class P2_교실로가는길 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 1;
        StringBuilder ans = new StringBuilder();
        while (true) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = parseInt(st.nextToken());
            int N = parseInt(st.nextToken());
            if (K == 0 || N == 0) break;

            Node.init();
            for (int a = 1; a <= N; a++) {
                Node.of(a).addEdge(Node.of(N + a));
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    int b = parseInt(st.nextToken());
                    Node.of(N + a).addEdge(Node.of(b));
                }
            }

            Node source = Node.of(N + 1);
            Node sink = Node.of(2);
            int totalFlow = 0;
            while (true) {
                //경로 탐색
                Node[] prev = new Node[2 * N + 1];
                Node.Edge[] path = new Node.Edge[2 * N + 1];
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

            ans.append(String.format("Case %d:\n", T++));
            if (totalFlow < K) {
                ans.append("Impossible\n");
            } else {
                StringBuilder sb = new StringBuilder();

                int cnt = 0;
                for (Node.Edge edge : source.edges) {
                    if (edge.flow == 1 && cnt<K) {
                        cnt++;
                        sb.append(1).append(" ");
                        dfs(edge.next, sb, N);
                        sb.append("\n");
                    }
                }
                ans.append(sb.toString());
            }
            ans.append("\n");
        }
        System.out.print(ans);
        br.close();
    }

    static void dfs(Node now, StringBuilder sb, int N) {
        if (now.id <= N) {
            sb.append(now.id).append(" ");
        }
        for (Node.Edge edge : now.edges) {
            if (edge.flow == 1) {
                edge.flow = 0;
                dfs(edge.next, sb, N);
                break;
            }
        }
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

        static public void init() {
            nodes = new Node[100001];
        }

        private Node(int id) {
            this.id = id;
        }

        public void addEdge(Node next) {
            Edge edge = new Edge(next, 1);
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
