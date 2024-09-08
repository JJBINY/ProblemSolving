package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G3_11779_최소비용_구하기_2
 * 그래프, 최단경로, 다익스트라
 */
public class G3_11779_최소비용_구하기_2 {
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


    static Object solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Node.of(from).edges.add(new Edge(to, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        StringBuilder paths = new StringBuilder().append(start);
        pq.offer(new State(Node.of(start), 0, 1, paths));

        int[] totalCost = new int[n + 1];
        Arrays.fill(totalCost, (int)1e9);
        totalCost[start] = 0;

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()){
            State cur = pq.poll();
            if (cur.cost > totalCost[cur.city.id]) {
                continue;
            }
            if(cur.city.id == end){
                result.append(cur.cost).append("\n")
                        .append(cur.nCity).append("\n")
                        .append(cur.paths);
                break;
            }

            for (Edge e : cur.city.edges) {
                int cost = cur.cost + e.cost;
                if(cost < totalCost[e.to]){
                    totalCost[e.to] = cost;
                    StringBuilder nextPaths = new StringBuilder(cur.paths).append(" ").append(e.to);
                    pq.offer(new State(Node.of(e.to), cost, cur.nCity + 1, nextPaths));
                }
            }
        }

        return result;
    }

    static class Node{
        static Node[] nodes = new Node[1_001];
        int id;
        List<Edge> edges;

        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }
    }

    static class Edge{
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State {

        Node city;
        int cost;
        int nCity;
        StringBuilder paths;

        public State(Node city, int cost, int nCity, StringBuilder paths) {
            this.city = city;
            this.cost = cost;
            this.nCity = nCity;
            this.paths = paths;
        }
    }
}