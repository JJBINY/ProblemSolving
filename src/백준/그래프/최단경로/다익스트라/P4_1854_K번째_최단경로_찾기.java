package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P4_1854_K번째_최단경로_찾기
 * 그래프, 최단경로, 다익스트라
 */
public class P4_1854_K번째_최단경로_찾기 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Node.of(from).edges.add(new Edge(to, cost));
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        pq.offer(new State(Node.of(1), 0));

        int INF = (int) 1e9;
        List<PriorityQueue<Integer>> totalCost = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            totalCost.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }
        totalCost.get(1).add(0);


        while (!pq.isEmpty()) {
            State cur = pq.poll();

            for (Edge e : cur.node.edges) {
                int cost = cur.cost + e.cost;
                PriorityQueue<Integer> costs = totalCost.get(e.to);
                if(costs.size()< k){
                    costs.offer(cost);
                    pq.offer(new State(Node.of(e.to), cost));
                }else if(costs.peek() > cost){
                    costs.poll();
                    costs.offer(cost);
                    pq.offer(new State(Node.of(e.to), cost));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            PriorityQueue<Integer> costs = totalCost.get(i);
            result.append(costs.size() < k ? -1 : costs.peek()).append("\n");
        }
        return result;
    }

    static class Node {
        static Node[] nodes = new Node[1_001];
        int id;
        List<Edge> edges;

        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
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

        Node node;
        int cost;

        public State(Node node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}