package 백준.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

/**
 * D5_1848_동굴_탐험
 * 최단경로, 다익스트라, 분할 정복
 * Ref: https://jwvg0425-ps.tistory.com/80
 */
public class D5_1848_동굴_탐험 {
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
    조건에 따라 완주할 수 있는 경로를 찾는 법
    1. 1번 방과 연결된 방 s가 있을 때,
        1<->s 경로를 끊고 다익스트라를 수행, s에서 1번 방까지의 최단 경로를 찾음
        해당 경로에 1->s 추가하면 1->s->t->1로 이어지는 최단 경로가 됨.
        (t는 1과 연결된 s가 아닌 임의의 정점)

    2. 위 과정을 1번 가능한 모든 s 대해 수행하여 그 중 최소를 찾는다.
        s는 최대 N-1개 (편의상 N개로 계산)
        따라서 N개에 대해 다익스트라 -> O(N*MlogM)이고 N <=5000, M<=10,000
         => NMlogM > 10^8 이므로 시간 초과

    다익스트라 수행 횟수를 logN으로 줄여야 한다.
    1. 전체 집합을 S∩T = Ø인 두 집합 S와 T로 분할,
        1번 방과 다른 모든 방들의 연결을 끊고 S->T를 구하자.
        이후 1->S, T->1을 경로에 추가하면 1->S->T->1을 구할 수 있다.
        방향에 따라 비용이 달라지므로 위와 같은 방법으로 1->T->S->1도 구한다.

    2. 이제 S와 T 각 내부 집합에서의 최단경로를 구해야 한다.
        S를 S1과 S2, T를 T1과 T2라는 서로소 집합으로 분리한다.
        우선 S1->S2, T1->T2를 구해보자. (편의상 정방향이라고 부르겠음)
        U(S1, T1) -> U(S2, T2)를 구하면 한 번의 연산으로 S1->S2, T1->T2를 구할 수 있다.
        같은 방법으로 S1을 두 개의 서로소 집합으로 나눈 것을 S11, S12과 같이 표현하면
        U(S11, S21, T11, T21) -> U(S12, S22, T12, T22)의 한 번의 연산으로 다음 4개의 경로 중 최단 경로를 구할 수 있다.
        S11->S12, S21->S22, T11->T12, T21->T22
        역방향(S2->S1, T2->T1)도 마찬가지로 구할 수 있다.
        => 다익스트라 수행 횟수를 logN으로 줄일 수 있다!

    3. 각 정점의 번호를 비트로 표현하면 집합 분리 과정을 손쉽게 할 수 있다.
        S(0), T(1)
        S1(00), S2(10), T1(10), T2(11)
        S11(000), S12(100), S21(010), S22(110), T11(010), T12(110), T21(011), T22(111)
        즉 i번째 bit가 0이냐 1이냐에 따라 두 집합을 분리할 수 있다.
        그리고 i번째 bit가 같은 집합끼리 묶은 뒤 최단경로를 구한다.
        => N<= 5000 < 2^13 이므로 분할 과정을 13번 수행한다.
     */

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken()); // 정점 수 <=5000
        int M = parseInt(st.nextToken()); // 간선 수 <= 10000

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int c = parseInt(st.nextToken());
            int d = parseInt(st.nextToken());
            addEdge(a, b, c);
            addEdge(b, a, d);
        }

        int res = MAX_VALUE;
        for (int i = 0; i < 13; i++) {
            res = Math.min(res, dijkstra(N, i, 0));
            res = Math.min(res, dijkstra(N, i, 1));
        }
        return res;
    }

    private static int dijkstra(int N, int i, int bit) {
        int[] costs = new int[N + 1];
        Arrays.fill(costs, MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1])); // id, cost
        Node start = Node.of(1);
        for (Edge e : start.edges) {
            if (isStartSet(e.to, i, bit)) {
                pq.offer(new int[]{e.to, e.cost}); // 1->{시작집합} 비용으로 초기화
                costs[e.to] = e.cost;
            }
        }

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int id = poll[0];
            int cost = poll[1];

            if (id == 1) {
                return cost; // 1->{시작집합}->{도착집합}->1 경로의 최소 비용
            }

            if (costs[id] < cost) continue;

            boolean flag = isStartSet(id, i, bit);
            for (Edge e : Node.of(id).edges) {
                if (flag && e.to == 1) continue; // 도착집합 이외의 정점에서 시작점에 도착해서는 안됨

                int ncost = cost + e.cost;
                if (ncost < costs[e.to]) {
                    costs[e.to] = ncost;
                    pq.offer(new int[]{e.to, ncost});
                }
            }
        }
        return MAX_VALUE;
    }

    private static boolean isStartSet(int id, int i, int bit){
        return getBit(id, i) == bit;
    }

    private static int getBit(int id, int i) {
        return (id >> i) & 1;
    }

    private static void addEdge(int a, int b, int w) {
        Node.of(a).addEdge(b, w);
    }

    static class Node {
        private static Node[] nodes = new Node[5_001];

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