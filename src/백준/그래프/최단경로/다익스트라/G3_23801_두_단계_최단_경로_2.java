package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G3_23801_두_단계_최단_경로_2
 * 그래프, 최단경로, 다익스트라
 */
public class G3_23801_두_단계_최단_경로_2 {
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
    static long INF = (long) 3e11+1L;
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Node.init(N);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            Node.addEdge(u, v, w);
            Node.addEdge(v, u, w); // 양방향
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Z = Integer.parseInt(st.nextToken());

        int P = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] mid = new int[P];
        for (int i = 0; i < P; i++) {
            mid[i] = Integer.parseInt(st.nextToken());
        }

        long[] distFromX = dijkstra(X, -1);
        long[] distFromZ = dijkstra(Z, -1);

        long result = INF;
        for (int m : mid) {
            long xToM = distFromX[m];
            long mToZ = distFromZ[m];
            if(xToM == INF || mToZ == INF) continue;
            result = Math.min(result, xToM + mToZ);
        }

        return result == INF ? -1 : result;
    }

    private static long[] dijkstra(int from, int except) {
        long[] totalCost = new long[N + 1];
        Arrays.fill(totalCost, INF);
        totalCost[from] = 0L;

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.cost));
        pq.offer(new State(from, 0L));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if(cur.cost > totalCost[cur.id]) continue;

            for (Edge e : Node.of(cur.id).edges) {
//                if(e.to == except) continue;

                long cost = cur.cost + e.cost;
                if(cost < totalCost[e.to]){
                    totalCost[e.to] = cost;
                    pq.offer(new State(e.to, cost));
                }
            }
        }

        return totalCost;
    }

    static class Node {
        static Node[] nodes;
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

        public static void addEdge(int u, int v, long w) {
            Node.of(u).edges.add(new Edge(v, w));
        }
    }

    static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State {

        int id;
        long cost;

        public State(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}