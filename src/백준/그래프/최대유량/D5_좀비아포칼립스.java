package 백준.그래프.최대유량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * D5 Avoiding the Apocalypse(좀비아포칼립스)
 * https://www.acmicpc.net/problem/10319
 */
public class D5_좀비아포칼립스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            //입력
            int N = parseInt(br.readLine()); //장소 수
            StringTokenizer st = new StringTokenizer(br.readLine());
            int I = parseInt(st.nextToken()); //처음에 있던 장소
            int G = parseInt(st.nextToken()); //일행 수
            int S = parseInt(st.nextToken()); //감염 시간


            boolean[] isSink = new boolean[N+1];
            int M = parseInt(br.readLine()); //병원 수
            for (int i = 0; i < M; i++) {
                isSink[parseInt(br.readLine())] = true;
            }

            Node.init(N, S);
            int R = parseInt(br.readLine()); //도로 수
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                int a = parseInt(st.nextToken());
                int b = parseInt(st.nextToken());
                int capacity = parseInt(st.nextToken());
                int t = parseInt(st.nextToken());
                //간선 추가
                for (int j = 0; j <= S - t; j++) {
                    Node.of(a, j).addEdge(Node.of(b, j + t), capacity);
                }
            }

            //제자리 대기하는 경우
            for (int i = 1; i <= N; i++) {
                Node prev = Node.of(i, 0);
                for (int j = 1; j <= S; j++) {
                    Node node = Node.of(i, j);
                    prev.addEdge(node, MAX_VALUE);
                    prev = node;
                }
            }

            int totalFlow = 0;
            Node source = Node.of(I, 0);
            while (true) {
                //경로 탐색
                Node[][] prev = new Node[N + 1][S + 1];
                Node.Edge[][] path = new Node.Edge[N + 1][S + 1];
                Queue<Node> queue = new LinkedList<>();
                queue.add(source);
                Node sink = null;
                while (!queue.isEmpty()) {
                    Node now = queue.poll();

                    for (Node.Edge edge : now.edges) {
                        Node next = edge.next;
                        if (edge.getFreeCapacity() <= 0 || !isNull(prev[next.id][next.time])) continue;
                        prev[next.id][next.time] = now;
                        path[next.id][next.time] = edge;
                        queue.add(next);
                        if (isSink[next.id]) {
                            sink = next;
                            break;
                        }
                    }
                }

                //경로 탐색 실패
                if (sink == null) break;

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
                if(totalFlow>=G) break;
            }

            ans.append(Math.min(totalFlow, G)).append("\n");
        }
        System.out.print(ans);
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
