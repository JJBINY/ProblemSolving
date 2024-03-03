package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G2 13911 집 구하기
 * 그래프, 최단경로, 다익스트라
 */
public class G2_13911_집구하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        //input
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = parseInt(st.nextToken()); // <=10,000
        int E = parseInt(st.nextToken()); // <=300,000
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());
            Node.of(u).addEdge(v, weight);
            Node.of(v).addEdge(u, weight);
        }
        st = new StringTokenizer(br.readLine());
        int M = parseInt(st.nextToken()); // <=V-2
        int x = parseInt(st.nextToken());
        Node macRoot = new Node();
        boolean[] isMac = new boolean[V + 1];
        for (String s : br.readLine().split(" ")) {
            int id = parseInt(s);
            isMac[id] = true;
            macRoot.addEdge(id, 0);
        }
        st = new StringTokenizer(br.readLine());
        int S = parseInt(st.nextToken());
        int y = parseInt(st.nextToken());
        Node starRoot = new Node();
        boolean[] isStar = new boolean[V + 1];
        for (String s : br.readLine().split(" ")) {
            int id = parseInt(s);
            isStar[id] = true;
            starRoot.addEdge(id, 0);
        }

        //solution
        int[] distFromMacs = dijkstra(V, E, x, macRoot);
        int[] distFromStars = dijkstra(V, E, y, starRoot);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= V; i++) {
            if (isMac[i] || isStar[i]) continue;

            if (distFromMacs[i] <= x && distFromStars[i] <= y) {
                pq.offer(distFromMacs[i] + distFromStars[i]);
            }
        }
        if (pq.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(pq.poll());
        }
    }

    private static int[] dijkstra(int V, int E, int maxDist, Node root) {
        int[] distances = new int[V + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>(E, Comparator.comparingInt(p -> p.dist));
        pq.offer(new Pair(root, 0));

        while (!pq.isEmpty()) {
            Pair now = pq.poll();

            if (now.dist > distances[now.node.id]) continue;
            if (now.dist > maxDist) continue;

            for (Edge edge : now.node.edges) {
                int dist = now.dist + edge.weight;
                if (dist < distances[edge.to]) {
                    distances[edge.to] = dist;
                    pq.offer(new Pair(Node.of(edge.to), dist));
                }
            }
        }
        return distances;
    }

    static class Pair {
        Node node;
        int dist;

        public Pair(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static class Node {
        static Node[] nodes = new Node[10_001];
        int id;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }

        void addEdge(int to, int weight) {
            edges.add(new Edge(to, weight));
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