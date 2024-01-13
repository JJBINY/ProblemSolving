package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 */
public class G1_구간합구하기 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(br.readLine());
            }
            SegmentTree tree = SegmentTree.build(arr);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m + k; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                if (cmd == 1) {
                    tree.replace(a, b);
                } else {
                    sb.append(tree.query(a, (int)b));
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    static class SegmentTree {
        long[] tree;
        long[] arr;

        public static SegmentTree build(long[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new long[4 * arr.length];
            segmentTree.buildTree(1, 0, arr.length - 1);
            return segmentTree;
        }

        public void replace(int a, long b) {
            arr[a-1] = b;
            replaceAt(1, 0, arr.length - 1, a-1);
        }

        public long query(int a, int b) {
            return getSumBetween(1, 0, arr.length - 1, a-1, b-1);
        }

        private void buildTree(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            buildTree(idx * 2, l, mid);
            buildTree(idx * 2 + 1, mid + 1, r);
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }


        private void replaceAt(int idx, int l, int r, int target) {
            if (l == r) {
                tree[idx] = arr[target];
                return;
            }
            int mid = (l + r) / 2;
            if (l <= target && target <= mid) {
                replaceAt(idx * 2, l, mid, target);
            } else {
                replaceAt(idx * 2 + 1, mid + 1, r, target);
            }
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }


        private long getSumBetween(int idx, int l, int r, int a, int b) {
            if (a > r || b < l) {
                return 0;
            }

            if (a <= l && r <= b) {
                return tree[idx];
            }

            int mid = (l + r) / 2;
            return getSumBetween(idx * 2, l, mid, a, b) +
                    getSumBetween(idx * 2 + 1, mid + 1, r, a, b);
        }
    }
}