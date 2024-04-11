package 백준.그래프.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G2 2637 장난감 조립
 * 위상정렬
 */
public class G2_2637_장난감조립 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;
    static int[] weights = new int[101];
    static int[] inDegree = new int[101];

    static void solve(BufferedReader br) throws IOException {
        init(br);

        weights[N] = 1;
        Queue<Node> q = new LinkedList<>();
        q.offer(Node.of(N));
        while (!q.isEmpty()){
            Node now = q.poll();
            for (Edge edge : now.edges) {
                Node next = edge.to;
                inDegree[next.id]--;
                weights[next.id] += weights[now.id] * edge.weight;
                if(inDegree[next.id] == 0){
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(Node.of(i).edges.isEmpty()) {
                System.out.println(i + " " + weights[i]);
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node a = Node.of(parseInt(st.nextToken()));
            Node b = Node.of(parseInt(st.nextToken()));
            int w = parseInt(st.nextToken());
            a.edges.add(new Edge(b, w));
            inDegree[b.id]++;
        }
    }

    static class Node {
        static Node[] nodes = new Node[101];
        int id;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }
    }

    static class Edge{
        Node to;
        int weight;

        public Edge(Node to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
