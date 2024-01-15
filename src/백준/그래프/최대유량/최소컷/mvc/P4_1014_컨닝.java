package 백준.그래프.최대유량.최소컷.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P4 1014 컨닝
 * (전체 자리) - ((부서진 자리) + (매칭수))
 * 모든 bipartite graph에서 maximum matching의 크기와 minimum vertex cover의 크기는 동일하다.
 * 출처: https://gazelle-and-cs.tistory.com/12 [Gazelle and Computer Science:티스토리]
 * => 이분그래프에서 minimum vertex cover = maximum matching  =maximum flow = minimum cut
 *
 * 컨닝이 가능한 자리들을 연결한 뒤 minimum vertex cover를 제거
 * 이후 남은 정점(vertex)들이 앉을 수 있는 자리(파괴된 자리 제외)
 *
 * 두 그룹으로 나눈 뒤 중복(충돌, 컨닝가능)되는 정점들을 간선으로 연결.
 * 이후 연결된 간선들을 모두 제거하는 문제로 생각 가능 -> 최소컷 == 최대유량
 */
public class P4_1014_컨닝 {

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

            int totalFlow = 0;
            while (true) {
                //경로 탐색
                Node[][] prev = new Node[N + 1][M + 1];
                Node.Edge[][] path = new Node.Edge[N + 1][M + 1];
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
            sb.append(N * M - x - totalFlow).append("\n");
        }
        System.out.print(sb);
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
