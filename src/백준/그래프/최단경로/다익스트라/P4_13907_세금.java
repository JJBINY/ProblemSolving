package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

/**
 * P4_13907_세금
 * 최단경로, 다익스트라
 */
public class P4_13907_세금 {
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

    /*
    K*MlogM > 10^9
    => K번 다익스트라를 반복할 수는 없음

    세금이 오르는 경우에 거치는 노드가 달라질 수 있음
    => 세금이 오를 수록 거치는 정점 수가 적을 수록 유리해짐

    정점 수 N <= 1,000 으로 작음
    => 따라서 정점을 1..N개 거쳤을 때 목적지까지의 최단 경로를 기록

    이후 통행료 인상 시마다 최소 비용 계산 -> O(NK); NK <= 3*10^7
     */
    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken()); // 정점 수
        int M = parseInt(st.nextToken()); // 간선 수
        int K = parseInt(st.nextToken()); // 세금 인상 횟수

        st = new StringTokenizer(br.readLine());
        int S = parseInt(st.nextToken()); // 시작 도시
        int D = parseInt(st.nextToken()); // 도착 도시

        addEdges(br, M);
        int[][] costs = dijkstra(N, S, D);

//        System.out.println(Arrays.toString(costs[D]));

        StringBuilder res = new StringBuilder();
        res.append(getMinCost(costs[D])).append("\n");
        for (int i = 0; i < K; i++) {
            int p = parseInt(br.readLine()); // 세금 인상 금색
            for (int e = 1; e < N; e++) { // 사용 간선 수
                if (costs[D][e] == MAX_VALUE) continue;
                costs[D][e] += p * e;
            }
            res.append(getMinCost(costs[D])).append("\n");
        }

        return res;
    }

    private static int[][] dijkstra(int N, int S, int D) {
        int[][] costs = new int[N + 1][N]; // 정점 1~N, 0~N-1개의 사용 간선 수
        for (int[] c : costs) {
            Arrays.fill(c, MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1])); // id, cost, 사용 간선 수
        pq.offer(new int[]{S, 0, 0});
        Arrays.fill(costs[S], 0);

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int id = poll[0];
            int cost = poll[1];
            int cnt = poll[2];
//            System.out.println(Arrays.toString(poll));
            if (id == D || cnt == N - 1) continue;
            if (costs[id][cnt] < cost) continue;

            for (Edge e : Node.of(id).edges) {
                int nc = cost + e.cost;

                // 최적화 : 더 적은 개수의 간선을 사용해서 더 적은 비용으로 도달 가능한 경우 탐색 중지
                boolean flag = false;
                for (int i = cnt; i > 0; i--) {
                    if (nc > costs[e.to][i]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;

                if (nc < costs[e.to][cnt + 1]) {
                    costs[e.to][cnt + 1] = nc;
                    pq.offer(new int[]{e.to, nc, cnt + 1});
                }
            }
        }
        return costs;
    }

    private static int getMinCost(int[] costs) {
        return Arrays.stream(costs).min().getAsInt();
    }

    private static void addEdges(BufferedReader br, int M) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());
            addEdges(a, b, w);
        }
    }

    /**
     * 노드 a와 b 사이 비용이 w인 양방향 간선 추가
     *
     * @param a
     * @param b
     * @param w
     */
    private static void addEdges(int a, int b, int w) {
        Node.of(a).addEdge(b, w);
        Node.of(b).addEdge(a, w);
    }

    static class Node {
        private static Node[] nodes = new Node[1_001];

        private int id;
        private List<Edge> edges = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }

        public static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public void addEdge(int to, int weight) {
            edges.add(new Edge(to, weight));
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
}