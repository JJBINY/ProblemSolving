package 백준.그래프.최대유량.최소컷.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

/**
 * P1 5398 WrongAnswer
 * 최소컷, minimum vertex cover, 최대유량, 이분매칭
 */
public class P1_5398_WrongAnswer {

    static boolean[] visited;
    static Node[] assigned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = parseInt(br.readLine());
        while (T-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = parseInt(st.nextToken()); //가로
            int V = parseInt(st.nextToken()); //세로

            //가로단어
            char[][] words = new char[2000][2000];
            int[][] horizon = new int[2000][2000];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken());
                int y = parseInt(st.nextToken());
                String word = st.nextToken();
                for (int j = 0; j < word.length(); j++) {
                    words[y][x + j] = word.charAt(j);
                    horizon[y][x + j] = i;
                }
            }

            //세로단어
            Node.init(Math.max(H, V), 2);
            for (int i = 0; i < V; i++) {
                st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken());
                int y = parseInt(st.nextToken());
                String word = st.nextToken();
                for (int j = 0; j < word.length(); j++) {
                    //충돌시 간선연결
                    if (words[y + j][x] != 0 && words[y + j][x] != word.charAt(j)) {
                        Node.of(horizon[y + j][x], 0).addEdge(Node.of(i, 1), 1);
                    }
                }
            }

            assigned = new Node[V];
            visited = new boolean[V];
            int matched = 0;
            for (int i = 0; i < H; i++) {
                Arrays.fill(visited, false);
                if (match(Node.of(i, 0))) matched++;
            }
            sb.append(H + V - matched).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean match(Node a) {
        for (Node.Edge edge : a.edges) {
            Node b = edge.next;
            if (visited[b.id]) continue;
            visited[b.id] = true;
            if (Objects.isNull(assigned[b.id]) || match(assigned[b.id])) {
                assigned[b.id] = a;
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
