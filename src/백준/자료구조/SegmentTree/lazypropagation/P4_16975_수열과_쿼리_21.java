package 백준.자료구조.SegmentTree.lazypropagation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * P4_16975_수열과_쿼리_21
 * 세그먼트트리, Lazy Propagation
 */
public class P4_16975_수열과_쿼리_21 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            SegTree segTree = SegTree.build(arr);

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            while (M-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    int i = Integer.parseInt(st.nextToken());
                    int j = Integer.parseInt(st.nextToken());
                    int k = Integer.parseInt(st.nextToken());
                    segTree.addRange(i - 1, j, k);
                } else {
                    int x = Integer.parseInt(st.nextToken());
                    sb.append(segTree.query(x - 1, x)).append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    static class SegTree {
        private long[] arr;
        private long[] tree;
        private long[] lazy;

        private SegTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[arr.length * 4];
            this.lazy = new long[arr.length * 4];
        }

        public static SegTree build(long[] arr) {
            SegTree segTree = new SegTree(arr);
            segTree.build(1, 0, arr.length);
            return segTree;
        }

        private void build(int node, int l, int r) {
            if (l + 1 == r) {
                tree[node] = arr[l];
                return;
            }

            int m = (l + r) / 2;
            build(node * 2, l, m);
            build(node * 2 + 1, m, r);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public void addRange(int from, int to, long val) {
            addRange(1, 0, arr.length, from, to, val);
        }

        private void addRange(int node, int l, int r, int from, int to, long val) {
            propagate(node, l, r);
            if (to <= l || r <= from) return;
            if (from <= l && r <= to) {
                lazy[node] += val;
                propagate(node, l, r);
                return;
            }

            int m = (l + r) / 2;
            addRange(node * 2, l, m, from, to, val);
            addRange(node * 2 + 1, m, r, from, to, val);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int from, int to) {
            return query(1, 0, arr.length, from, to);
        }

        private long query(int node, int l, int r, int from, int to) {
            propagate(node, l, r);
            if (to <= l || from >= r) return 0;
            if (from <= l && r <= to) return tree[node];

            int m = (l + r) / 2;
            long lr = query(node * 2, l, m, from, to);
            long rr = query(node * 2 + 1, m, r, from, to);
            return lr + rr;
        }

        private void propagate(int node, int l, int r) {
            if (l + 1 < r) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] += (r - l) * lazy[node];
            lazy[node] = 0;
        }

    }
}