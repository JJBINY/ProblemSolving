package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G1 1800 인터넷 설치
 * 다익스트라
 */
public class G1_1800_인터넷설치 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int N,P, K;
    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        P = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        addEdge(br);

        int[][] distances = new int[N + 1][N+1];
        for (int[] arr : distances) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }


        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p -> p.dist));
        pq.offer(new Pair(Node.of(1, 0), 0));

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            Node now = pair.node;
            int nowDist = pair.dist;

            if (nowDist > distances[now.id][now.time]) continue;

            for (Edge edge : now.edges) {
                int dist = Math.max(nowDist, edge.weight);
                Node next = edge.to;
                if (dist < distances[next.id][next.time]) {
                    distances[next.id][next.time] = dist;
                    pq.offer(new Pair(next, dist));
                }
            }
        }

        int ans = Arrays.stream(distances[N]).min().getAsInt();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void addEdge(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());
            for (int k = 0; k <= K; k++) {
                Node na = Node.of(a,k);
                Node nb = Node.of(b,k);
                na.addEdge(nb, weight);
                nb.addEdge(na, weight);
                if(k == K) break;
                Node na2 = Node.of(a,k+1);
                Node nb2 = Node.of(b,k+1);
                na.addEdge(nb2, 0);
                nb.addEdge(na2, 0);
            }
        }
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
        static Node[][] nodes = new Node[1001][1001];
        int id;
        int time;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id, int time) {
            if (nodes[id][time] == null) {
                nodes[id][time] = new Node(id,time);
            }
            return nodes[id][time];
        }

        public Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        void addEdge(Node to, int weight) {
            edges.add(new Edge(to, weight));
        }
    }

    static class Edge {
        Node to;
        int weight;

        public Edge(Node to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}