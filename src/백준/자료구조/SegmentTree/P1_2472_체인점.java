package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;


/**
 * P1_2472_체인점
 * 세그먼트 트리, 좌표 압축, 다익스트라
 */
public class P1_2472_체인점 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = parseInt(br.readLine()); // <=10^4
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
        교차로는 기껏해야 오거리
        아파트 단지 위치에도 매장 설치 가능

        A, B, C 각각으로부터의 거리를 a,b,c라 하자
        => A,B,C 각각에 대해 다익스트라를 총 3번 수행하여 구할 수 있다.

        p(a,b,c), q(x,y,z)일 때, a>x && b>y && c>z 면 p에는 매장 설치 X
        => 매장 후보지 P에 매장을 설치하려면 다른 후보지 Q에 대해 (P.a <= Q.a || P.b <= Q.b || P.c <= Q.c) 를 만족해야 한다.
        1. a에 대한 오름차순으로 후보지 정렬
        2. 오름차순으로 쿼리 시, if segtree 0~b범위 쿼리 결과가 존재 X
        2-1. p.a >= q.a인 q에 대해 p.b <= q.b 이므로 설치 가능
        3. 쿼리 결과 q.c = min(c..)가 존재한다면?
        3-1. p.a >= q.a 이고 p.b >= q.b인 가 존재한다.
        3-2. p.c <= q.c 이면 설치 가능
     */
    static int N; // <= 10^5

    static Object solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = parseInt(st.nextToken());
        int B = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());

        int M = parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v1 = parseInt(st.nextToken());
            int v2 = parseInt(st.nextToken());
            int dist = parseInt(st.nextToken()); // <= 10^4
            Node.addEdge(v1, v2, dist);
        }

        // NlogN
        int[] fromA = dijkstra(A);
        int[] fromB = dijkstra(B); // B에 대해 경로압축
        int[] fromC = dijkstra(C);

        int rank = 0;
        Map<Integer, Integer> bmap = new HashMap<>();
        int[] sortedB = Arrays.stream(fromB).distinct().sorted().toArray();
        for (int b : sortedB) {
            bmap.put(b, rank++);
        }

        PriorityQueue<Candidate> pq = new PriorityQueue<>(Comparator
                .<Candidate>comparingInt(c -> c.a)
                .thenComparingInt(c -> -c.b)
//                .thenComparingInt(c -> -c.c)
        );
        for (int i = 1; i < N + 1; i++) {
            pq.offer(new Candidate(i, fromA[i], fromB[i], fromC[i]));
        }

        SegTree segTree = SegTree.build(N+1);
        boolean[] canPlace = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Candidate p = pq.poll();
            int res = segTree.query(0, bmap.get(p.b));
            segTree.add(bmap.get(p.b), p.c);
            if (res == MAX_VALUE || p.c <= res) {
                canPlace[p.id] = true;
            }
//            System.out.println("p = " + p);
//            System.out.println("Arrays.toString(segTree.tree) = " + Arrays.toString(segTree.tree));
//            System.out.println("res = " + res);
        }

        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int Q = parseInt(br.readLine());
            if (canPlace[Q]) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        } // while

        return sb;
    }

    static int[] dijkstra(int from) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[from] = 0;
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.dist));
        pq.offer(new State(from, 0));
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.dist > distances[cur.id]) continue;

            for (Node.Edge e : Node.of(cur.id).edges) {
                int dist = cur.dist + e.dist;
                if (dist < distances[e.to]) {
                    distances[e.to] = dist;
                    pq.offer(new State(e.to, dist));
                }
            }
        }

        return distances;
    }

    static class State {
        int id;

        int dist;

        public State(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    static class Candidate {
        int id;
        int a;
        int b;
        int c;

        public Candidate(int id, int a, int b, int c) {
            this.id = id;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Candidate{" +
                    "id=" + id +
                    ", a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

    static class Node {
        private static Node[] nodes = new Node[100_001];
        int id;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }

        public static void addEdge(int v1, int v2, int dist) {
            of(v1).edges.add(new Edge(v2, dist));
            of(v2).edges.add(new Edge(v1, dist));
        }

        static class Edge {
            int to;
            int dist;

            public Edge(int to, int dist) {
                this.to = to;
                this.dist = dist;
            }
        }
    }

    static class SegTree {
        private int len;
        private int[] tree;

        private SegTree(int len) {
            this.len = len;
            this.tree = new int[4 * len];
            Arrays.fill(tree, MAX_VALUE);
        }

        public static SegTree build(int len) {
            return new SegTree(len);
        }

        public void add(int to, int val) {
            add(1, to, 0, len, val);
        }

        private void add(int idx, int target, int l, int r, int val) {
            if (target < l || r < target) return;
            if (l == r) {
                tree[idx] = Math.min(tree[idx], val);
                return;
            }

            int m = (l + r) / 2;
            add(idx * 2, target, l, m, val);
            add(idx * 2 + 1, target, m + 1, r, val);
            tree[idx] = Math.min(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public int query(int from, int to) {
            return query(1, 0, len, from, to);
        }

        private int query(int idx, int l, int r, int from, int to) {
            if (to < l || from > r) return Integer.MAX_VALUE;
            if (from <= l && r <= to) return tree[idx];

            int m = (l + r) / 2;
            int left = query(idx * 2, l, m, from, to);
            int right = query(idx * 2 + 1, m + 1, r, from, to);
            return Math.min(left, right);
        }

    }
}

/*
9
2 5 9
15
1 2 8
1 3 5
2 4 6
2 5 8
2 6 5
3 4 6
3 9 4
4 6 4
4 9 3
5 6 3
5 7 4
6 7 2
6 9 5
7 8 7
8 9 6
9
1
2
3
4
5
6
7
8
9

=>
NO
YES
NO
YES
YES
YES
NO
NO
YES

case 2:
5
3 4 5
6
1 3 5
1 4 1
1 5 1
2 3 5
2 4 2
2 5 3
5
1
2
3
4
5
=>
YES
YES
YES
YES
YES
 */