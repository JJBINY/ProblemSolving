package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3 1939 중량제한
 * 그래프, 그리디, 다익스트라 응용
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

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt((Pair p) -> p.weight).reversed());

        int[] weights = new int[N + 1];
        pq.add(new Pair(start, 1_000_000_001));
        while (!pq.isEmpty()){
            Pair now = pq.poll();
            if(now.id == end){
                break;
            }
            if(weights[now.id] > 0 && now.weight >weights[now.id]){
                continue;
            }
            for (Edge edge : Node.of(now.id).edges) {
                int weight = Math.min(now.weight, edge.weight);
                if (weight > weights[edge.to]) {
                    weights[edge.to] = weight;
                    pq.add(new Pair(edge.to, weight));
                }
            }
        }

        System.out.println(weights[end]);
    }

    static class Pair {
        int id;
        int weight;

        public Pair(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }


    static class Node {
        static Node[] nodes = new Node[100_001];
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

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
