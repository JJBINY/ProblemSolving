package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3 1939 중량제한
 * 이분 탐색, 파라메트릭 서치, BFS
 */
public class G3_1939_중량제한 {
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
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = parseInt(st.nextToken());
            int B = parseInt(st.nextToken());
            int C = parseInt(st.nextToken());
            Node.of(A).edges.add(new Edge(B, C));
            Node.of(B).edges.add(new Edge(A, C));
        }
        st = new StringTokenizer(br.readLine());
        int start = parseInt(st.nextToken());
        int end = parseInt(st.nextToken());

        int hi = 1_000_000_001;
        int lo = 0;

        while (lo+1<hi){
            int mid = (lo + hi) / 2;
            if (!canMove(mid, start, end)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        System.out.println(lo);
    }

    private static boolean canMove(int weight, int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(Node.of(start));
        boolean[] visited = new boolean[100_001];
        visited[start] = true;
        while (!queue.isEmpty()){
            Node now = queue.poll();
            if(now.id == end){
                return true;
            }
            for (Edge edge : now.edges) {
                if (visited[edge.to] || edge.weight < weight) {
                    continue;
                }
                visited[edge.to] = true;
                queue.add(Node.of(edge.to));
            }
        }
        return false;
    }

    static class Node{
        static Node[] nodes = new Node[100_001];
        int id;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id){
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }
    }

    static class Edge{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
