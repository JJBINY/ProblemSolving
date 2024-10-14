package 백준.자료구조.SegmentTree.lazypropagation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P3_1395_스위치
 * 세그먼트트리, Lazy Propagation
 */
public class P3_1395_스위치 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            SegTree segTree = SegTree.build(new int[N]);

            StringBuilder sb = new StringBuilder();
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int o = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                if (o == 0) {
                    segTree.reverseRange(s - 1, t);
                } else {
                    sb.append(segTree.query(s - 1, t)).append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    static class SegTree {
        private int[] arr;
        private int[] tree;
        private int[] lazy;

        private SegTree(int[] arr) {
            this.arr = arr;
            this.tree = new int[arr.length * 4];
            this.lazy = new int[arr.length * 4];
        }

        public static SegTree build(int[] arr) {
            return new SegTree(arr);
        }

        public void reverseRange(int from, int to) {
            reverseRange(1, 0, arr.length, from, to);
        }

        private void reverseRange(int node, int l, int r, int from, int to) {
            propagate(node, l, r);
            if (to <= l || r <= from) return;
            if (from <= l && r <= to) {
                lazy[node] ^= 1; // 반전
                propagate(node, l, r);
                return;
            }

            int m = (l + r) / 2;
            reverseRange(node * 2, l, m, from, to);
            reverseRange(node * 2 + 1, m, r, from, to);
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
            if (lazy[node] == 0) return;
            lazy[node] = 0;

            if (isLeaf(l, r)) {
                tree[node] ^= 1;
                return;
            }

            lazy[node * 2] ^= 1;
            lazy[node * 2 + 1] ^= 1;
            int m = (l+r)/2;
            tree[node] = countOn(node * 2, l, m) + countOn(node * 2 + 1, m, r);
        }

        private static boolean isLeaf(int l, int r) {
            return l + 1 == r;
        }

        private int countOn(int node, int l, int r) {
            if (lazy[node] == 1) { // {반전될 경우 켜져있는 스위치 수} = {구간 길이} - {현재 켜져있는 스위치 수}
                return (r - l)  - tree[node];
            }
            return tree[node];
        }

    }
}