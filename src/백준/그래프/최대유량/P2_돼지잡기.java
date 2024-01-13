package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 PiGS(돼지잡기)
 * https://www.acmicpc.net/problem/1658
 */
public class P2_돼지잡기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = parseInt(st.nextToken()); //돼지 우리 수
        int N = parseInt(st.nextToken()); //손님 수

        Node.init(M+1, N+2);
        Node source = Node.of(0, 0);
        Node sink = Node.of(0, N+1);

        //초기 돼지우리 연결
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=M; i++) {
            source.addEdge(Node.of(i, 0), parseInt(st.nextToken()));
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                Node.of(i, j).addEdge(Node.of(i, j + 1), MAX_VALUE);
            }
        }


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = parseInt(st.nextToken());
            Node customer = Node.of(0, i);
            Node[] pigs = new Node[A];
            //돼지우리와 손님 연결
            for (int j = 0; j < A; j++) {
                Node pig = Node.of(parseInt(st.nextToken()), i);
                pig.addEdge(customer, MAX_VALUE);
                pigs[j] = pig;
            }

            int B = parseInt(st.nextToken());
            customer.addEdge(sink, B);

            if(i==N)continue;
            //돼지 재배치
            for (int j = 0; j < A; j++) {
                for (int k = 0; k < A; k++) {
                    if(j==k) continue;
                    pigs[j].addEdge(pigs[k],MAX_VALUE);
                }
            }

        }

        int totalFlow = 0;
        while (true) {
            //경로 탐색
            Node[][] prev = new Node[M+1][N+2];
            Node.Edge[][] path = new Node.Edge[M+1][N+2];
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

        System.out.println(totalFlow);
        br.close();
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
            nodes = new Node[N + 1][T + 1];
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
