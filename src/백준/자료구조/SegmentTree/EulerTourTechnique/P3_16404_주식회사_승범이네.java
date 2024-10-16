package 백준.자료구조.SegmentTree.EulerTourTechnique;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P3_16404_주식회사_승범이네
 * 세그먼트 트리, 오일러 경로 테크닉, Lazy Propagation
 */
public class P3_16404_주식회사_승범이네 {

    static int dfsId;
    static int[][] dfsIds;

    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            Node.init(N);
            st.nextToken();
            for (int i = 2; i <= N; i++) {
                int x = Integer.parseInt(st.nextToken());
                Node.of(x).edges.add(Node.of(i));
            }

            dfsIds = new int[N + 1][2];
            dfs(Node.of(1), 1);

            SegTree segTree = SegTree.build(N);
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int i = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    long amount = Long.parseLong(st.nextToken());
                    segTree.addRange(dfsIds[i][0], dfsIds[i][1] + 1, amount);
                } else {
                    int id = dfsIds[i][0];
                    long balance = segTree.query(id, id + 1);
                    ans.append(balance).append("\n");
                }
            }
        }
        System.out.println(ans.toString());
    }

    private static int dfs(Node cur, int depth) {
        cur.visited = true;
        cur.depth = depth;
        dfsIds[cur.id][0] = dfsId;
        int maxId = dfsId++;

        for (Node next : cur.edges) {
            if (next.visited) continue;
            maxId = Math.max(maxId, dfs(next, depth + 1));
        }

        dfsIds[cur.id][1] = maxId;
        return maxId;
    }

    static class SegTree {
        private int N;
        private long[] tree;
        private long[] lazy;

        private SegTree(int N) {
            this.N = N;
            this.tree = new long[N * 4];
            this.lazy = new long[N * 4];
        }

        public static SegTree build(int N) {
            return new SegTree(N);
        }


        public void addRange(int from, int to, long amount) {
            addRange(1, 0, N, from, to, amount);
        }

        private void addRange(int node, int l, int r, int from, int to, long amount) {
            if (isOutOfBound(l, r, from, to)) return;
            if (from <= l && r <= to) {
                lazy[node] += amount;
                propagate(node, l, r);
                return;
            }
            propagate(node, l, r);

            int m = (l + r) / 2;
            addRange(node * 2, l, m, from, to, amount);
            addRange(node * 2 + 1, m, r, from, to, amount);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int from, int to) {
            return query(1, 0, N, from, to);
        }

        private long query(int node, int l, int r, int from, int to) {
            if (isOutOfBound(l, r, from, to)) return 0;
            propagate(node, l, r);
            if (from <= l && r <= to) return tree[node];

            int m = (l + r) / 2;
            long lSum = query(node * 2, l, m, from, to);
            long rSum = query(node * 2 + 1, m, r, from, to);
            return lSum + rSum;
        }

        private void propagate(int node, int l, int r) {
            if (!isLeaf(l, r)) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] += (r - l) * lazy[node];
            lazy[node] = 0;
        }

        private static boolean isOutOfBound(int l, int r, int from, int to) {
            return r <= from || to <= l;
        }

        private static boolean isLeaf(int l, int r) {
            return l + 1 == r;
        }
    }

    static class Node {
        static Node[] nodes;
        int id;
        int depth;
        boolean visited;
        List<Node> edges = new ArrayList<>();

        static void init(int N) {
            nodes = new Node[N + 1];
        }

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }
    }
}