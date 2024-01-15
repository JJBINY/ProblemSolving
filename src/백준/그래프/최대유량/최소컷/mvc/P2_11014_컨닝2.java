package 백준.그래프.최대유량.최소컷.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P2 11014 컨닝2
 * (전체 자리) - ((부서진 자리) + (매칭수))
 * 이분매칭
 */
public class P2_11014_컨닝2 {

    static boolean[][] visited;
    static Node[][] assigned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = parseInt(st.nextToken()); //행
            int M = parseInt(st.nextToken()); //열

            String[] arr = new String[N];
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            //간선연결
            Node.init(N + 1, M + 1);

            int[] dr = new int[]{-1, -1, 0, 0, 1, 1};
            int[] dc = new int[]{-1, 1, -1, 1, -1, 1};
            Node source = Node.of(N, 0);
            Node sink = Node.of(N, 1);
            int x = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i].charAt(j) == 'x') {
                        x++;
                        continue;
                    }

                    Node now = Node.of(i, j);
                    if (j % 2 > 0) {
                        now.addEdge(sink, 1);
                    } else {
                        source.addEdge(now, 1);
                        for (int k = 0; k < 6; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                            if (arr[nr].charAt(nc) == 'x') continue;
                            now.addEdge(Node.of(nr, nc), 1);
                        }
                    }
                }
            }

            assigned = new Node[N + 1][M + 1];
            visited = new boolean[N + 1][M + 1];
            int matched = 0;
            for (Node.Edge edge : source.edges) {
                Node a = edge.next;
                for (boolean[] v : visited) {
                    Arrays.fill(v, false);
                }
                if (match(a)) matched++;
            }

            sb.append(N * M - x - matched).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean match(Node a) {
        for (Node.Edge edge : a.edges) {
            Node b = edge.next;
            if (visited[b.id][b.time]) continue;
            visited[b.id][b.time] = true;
            if (Objects.isNull(assigned[b.id][b.time]) || match(assigned[b.id][b.time])) {
                assigned[b.id][b.time] = a;
                return true;
            }
        }
        return false;
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
//            Edge reversed = new Edge(this, 0);
//            edge.reversed = reversed;
//            reversed.reversed = edge;
            edges.add(edge);
//            next.edges.add(reversed);
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
