package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_23793_두_단계_최단_경로_1
 * 그래프, 최단경로, 다익스트라
 */
public class G4_23793_두_단계_최단_경로_1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M;
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Node.init(N);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Node.addEdge(u, v, w);
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int Z = Integer.parseInt(st.nextToken());

        int xToY = dijkstra(X, Y, -1);
        int yToZ = dijkstra(Y, Z, -1);
        int xToZExceptY = dijkstra(X, Z, Y);// Y를 거치지 않은 최단경로

        StringBuilder result = new StringBuilder();
        return result.append((xToY < 0 || yToZ < 0) ? -1 : (xToY + yToZ))
                .append(" ")
                .append(xToZExceptY);
    }

    private static int dijkstra(int from, int to, int except) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        pq.offer(new State(from, 0));

        int INF = (int) 1e9;
        int[] totalCost = new int[N + 1];
        Arrays.fill(totalCost, INF);
        totalCost[from] = 0;
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if(cur.cost > totalCost[cur.id]) continue;

            for (Edge e : Node.of(cur.id).edges) {
                if(e.to == except) continue;

                int cost = cur.cost + e.cost;
                if(cost < totalCost[e.to]){
                    totalCost[e.to] = cost;
                    pq.offer(new State(e.to, cost));
                }
            }
        }

        return totalCost[to] == INF ? -1 : totalCost[to];
    }

    static class Node {
        static Node[] nodes = new Node[100_001];
        int id;
        List<Edge> edges;

        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        public static void init(int N) {
            nodes = new Node[N + 1];
        }

        public static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public static void addEdge(int u, int v, int w) {
            Node.of(u).edges.add(new Edge(v, w));
        }
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State {

        int id;
        int cost;

        public State(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}