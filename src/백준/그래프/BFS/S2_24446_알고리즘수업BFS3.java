package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * S2 24446 알고리즘 수업 - 너비 우선 탐색 3
 * 그래프, BFS
 */
public class S2_24446_알고리즘수업BFS3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int R = parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            Node.of(a).addEdge(b);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(Node.of(R), 0));
        boolean[] visited = new boolean[N + 1];
        visited[R] = true;
        int[] ans = new int[N + 1];
        Arrays.fill(ans, -1);
        while (!queue.isEmpty()){
            Node now = queue.peek().node;
            int sequence = queue.poll().sequence;
            ans[now.id] = sequence;
            for (Edge edge : now.edges) {
                Node next = edge.a.equals(now) ? edge.b : edge.a;
                if(visited[next.id]) continue;
                visited[next.id] = true;
                queue.add(new Pair(next, sequence + 1));
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
    static class Pair{
        Node node;
        int sequence;

        public Pair(Node node, int sequence) {
            this.node = node;
            this.sequence = sequence;
        }
    }

    static class Node{
        static Node[] nodes = new Node[100_001];
        int id;
        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public void addEdge(int other){
            Edge edge = new Edge(this, of(other));
            edges.add(edge);
            of(other).edges.add(edge);
        }
    }

    static class Edge{
        Node a;
        Node b;

        public Edge(Node a, Node b) {
            this.a = a;
            this.b = b;
        }
    }

}