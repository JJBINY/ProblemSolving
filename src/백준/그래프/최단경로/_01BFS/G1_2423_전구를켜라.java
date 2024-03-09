package 백준.그래프.최단경로._01BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G1 2423 전구를 켜라
 * 0-1BFS, 최단경로
 */
public class G1_2423_전구를켜라 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '\\') {
                    Node.of(i, j).addEdge(i + 1, j + 1, 0);
                    Node.of(i+1, j).addEdge(i, j + 1, 1);
                }else{
                    Node.of(i, j).addEdge(i + 1, j + 1, 1);
                    Node.of(i+1, j).addEdge(i, j + 1, 0);
                }
            }
        }

        int[][] distances = new int[N + 1][M + 1];
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        Deque<Pair<Node, Integer>> deque = new LinkedList<>();
        deque.offer(new Pair<>(Node.of(0, 0), 0));
        distances[0][0] = 0;
        while (!deque.isEmpty()){
            Node now = deque.peekFirst().a;
            int cost = deque.removeFirst().b.intValue();

            if(cost>distances[now.r][now.c]) continue;

            for (Edge edge : now.edges) {
                int nr = edge.next.r;
                int nc = edge.next.c;
                if(cost + edge.cost < distances[nr][nc]){
                    distances[nr][nc] = cost + edge.cost;
                    if(edge.cost == 0){
                        deque.offerFirst(new Pair<>(edge.next, cost + edge.cost));
                    }else{
                        deque.offerLast(new Pair<>(edge.next, cost + edge.cost));
                    }
                }
            }
        }

        System.out.println(distances[N][M] == Integer.MAX_VALUE? "NO SOLUTION" : distances[N][M]);
    }

    static class Node{
        private static final Node[][] nodes = new Node[501][501];

        int r;
        int c;
        List<Edge> edges = new ArrayList<>();

        static Node of(int r,int c){
            if(nodes[r][c] == null){
                nodes[r][c] = new Node(r,c);
            }
            return nodes[r][c];
        }

        private Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void addEdge(int r, int c, int cost){
            //양방향
            Node other = of(r, c);
            edges.add(new Edge(other, cost));
            other.edges.add(new Edge(this, cost));
        }
    }

    static class Edge{
        Node next;
        int cost;

        public Edge(Node next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static class Pair<A, B> {
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair<?, ?>)) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (a != null ? !a.equals(pair.a) : pair.a != null) return false;
            if (b != null ? !b.equals(pair.b) : pair.b != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            return result;
        }
    }
}