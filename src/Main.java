import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P1 The King of the North
 * https://www.acmicpc.net/problem/9209
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = parseInt(st.nextToken()); //행
        int C = parseInt(st.nextToken()); //열

        Node.init(R * C + 1, 2);
        Node sink = Node.of(R * C, 0);
        int[][] arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = parseInt(st.nextToken());
                Node.of(i * C + j, 0).addEdge(Node.of(i * C + j, 1), arr[i][j]);
            }
            //바깥 테두리(탈출지점)를 sink와 연결
            Node.of(i * C, 1).addEdge(sink, MAX_VALUE);
            Node.of(i * C + C - 1, 1).addEdge(sink, MAX_VALUE);
            if (i == 0 || i == R - 1) {
                for (int j = 1; j < C - 1; j++) {
                    Node.of(i * C + j, 1).addEdge(sink, MAX_VALUE);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        Node source = Node.of(parseInt(st.nextToken()) * C + parseInt(st.nextToken()), 1);

        addEdges(R, C, arr, source);
        System.out.print(getTotalFlow(R, C, sink, source));
        br.close();
    }

    private static int getTotalFlow(int R, int C, Node sink, Node source) {
        int totalFlow = 0;
        while (true) {
            //경로 탐색
            Node[][] prev = new Node[R * C + 1][2];
            Node.Edge[][] path = new Node.Edge[R * C + 1][2];
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
        return totalFlow;
    }

    private static void addEdges(int R, int C, int[][] arr, Node source) {
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source.id);
        boolean[][] visited = new boolean[R][C];
        visited[source.id / C][source.id % C] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            int r = now / C;
            int c = now % C;
            Node out = Node.of(now, 1);

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (arr[nr][nc] == 0) continue;
                int next = nr * C + nc;
                out.addEdge(Node.of(next, 0), MAX_VALUE);
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(next);
            }
        }
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
