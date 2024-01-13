package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P4 도시 왕복하기 2
 * https://www.acmicpc.net/problem/2316
 */
public class P4_도시왕복하기2 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int P = parseInt(st.nextToken());

        Node[] nodes = new Node[2 * N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
            nodes[N + i] = new Node(N + i);
            nodes[i].addEdge(nodes[N + i]);
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()) - 1;
            int b = parseInt(st.nextToken()) - 1;
            nodes[N + a].addEdge(nodes[b]);
            nodes[N + b].addEdge(nodes[a]);
        }

        Node source = nodes[N + 0];
        Node sink = nodes[1];
        int totalFlow = 0;
        while (true) {
            //경로 탐색
            Node[] prev = new Node[2 * N];
            Node.Edge[] path = new Node.Edge[2 * N];
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

        System.out.println(totalFlow);
        br.close();
    }


    static class Node {
        int id;
        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void addEdge(Node node) {
            Edge edge = new Edge(node, 1);
            Edge reversed = new Edge(this, 0);
            edge.reversed = reversed;
            reversed.reversed = edge;
            edges.add(edge);
            node.edges.add(reversed);
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
