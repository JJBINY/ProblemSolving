package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 Crucial Links(완전 중요한 간선)
 * https://www.acmicpc.net/problem/5651
 */
public class P2_완전중요한간선 {
    /*
       capacity == flow인 간선 u->v가 u->x->v와 같은 우회로가 존재X
       => u->v가 대체 불가능
        */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        while (K-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken());
            int M = parseInt(st.nextToken());

            Node.init(N);
            Node.Edge[] edges = new Node.Edge[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                Node f = Node.of(parseInt(st.nextToken()));
                Node t = Node.of(parseInt(st.nextToken()));
                f.addEdge(t, parseInt(st.nextToken()));
            }

            Node source = Node.of(1);
            Node sink = Node.of(N);
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
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                source = Node.of(i);
                for (Node.Edge e : source.edges) {
                    if (e.capacity == 0 || e.getFreeCapacity() > 0) continue;

                    sink = e.next;
                    e.capacity--;
                    e.flow--;
                    //경로 탐색
                    Node[] prev = new Node[N + 1];
                    Node.Edge[] path = new Node.Edge[N + 1];
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
                    if (isNull(prev[sink.id])) cnt++;
                }
            }
            ans.append(cnt).append("\n");
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
