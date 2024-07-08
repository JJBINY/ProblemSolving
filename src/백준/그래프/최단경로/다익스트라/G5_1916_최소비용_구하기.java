package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G5_1916_최소비용_구하기
 * 최단경로, 다익스트라
 */
public class G5_1916_최소비용_구하기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int N = parseInt(br.readLine());
        int M = parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int fee = parseInt(st.nextToken());
            Node.of(from).edges.add(new Edge(to, fee));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = parseInt(st.nextToken());
        int end = parseInt(st.nextToken());

        int[] weights = new int[N + 1];
        Arrays.fill(weights, 1_000_000_000);
        pq.add(new Pair(start, 0));
        while (!pq.isEmpty()) {
            Pair now = pq.poll();
            if (now.id == end) {
                break;
            }
            if (now.weight > weights[now.id]) {
                continue;
            }
            for (Edge edge : Node.of(now.id).edges) {
                int weight = now.weight + edge.weight;
                if (weight < weights[edge.to]) {
                    weights[edge.to] = weight;
                    pq.add(new Pair(edge.to, weight));
                }
            }
        }

        return weights[end];
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
        static Node[] nodes = new Node[1001];
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