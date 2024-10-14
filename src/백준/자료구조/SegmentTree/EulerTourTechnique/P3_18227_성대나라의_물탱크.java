package 백준.자료구조.SegmentTree.EulerTourTechnique;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P3_18227_성대나라의_물탱크
 * 세그먼트 트리, 오일러 경로 테크닉
 */
public class P3_18227_성대나라의_물탱크 {

    static int dfsId;
    static int[][] dfsIds;

    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            Node.init(N);
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Node.addEdge(x, y);
            }
            dfsIds = new int[N + 1][2];
            dfs(Node.of(C), 1);

            SegTree segTree = SegTree.build(new long[dfsId]);
            int Q = Integer.parseInt(br.readLine());
            while (Q-- > 0) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    segTree.add(dfsIds[A][0]);
                } else {
                    long addCount = segTree.query(dfsIds[A][0], dfsIds[A][1] + 1);
                    ans.append(Node.of(A).depth * addCount).append("\n");
                }
            }
        }
        System.out.println(ans.toString());
    }

    private static int dfs(Node cur,int depth) {
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
        private long[] arr;
        private long[] tree;

        private SegTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[arr.length * 4];
        }

        public static SegTree build(long[] arr) {
            return new SegTree(arr);
        }


        public void add(int idx) {
            arr[idx]++;
            add(1, idx, 0, arr.length);
        }

        private void add(int node, int idx, int l, int r) {
            if (l + 1 == r) {
                tree[node] = arr[idx];
                return;
            }

            int m = (l + r) / 2;
            if (idx < m) add(node * 2, idx, l, m);
            else add(node * 2 + 1, idx, m, r);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int from, int to) {
            return query(1, 0, arr.length, from, to);
        }

        private long query(int cur, int l, int r, int from, int to) {
            if (to <= l || from >= r) return 0;
            if (from <= l && r <= to) return tree[cur];

            int m = (l + r) / 2;
            long lr = query(cur * 2, l, m, from, to);
            long rr = query(cur * 2 + 1, m, r, from, to);
            return lr + rr;
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

        public static void addEdge(int x, int y) {
            Node nx = of(x);
            Node ny = of(y);
            nx.edges.add(ny);
            ny.edges.add(nx);
        }
    }
}