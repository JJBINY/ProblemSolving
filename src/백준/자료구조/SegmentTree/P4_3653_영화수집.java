package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * P4 3653 영화 수집
 * 자료구조, 세그먼트트리
 */
public class P4_3653_영화수집 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-- > 0)
                solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        int[] disks = new int[n + 1];
        for (int i = 0; i < n; i++) {
            disks[i + 1] = n - i;
        }

        long[] arr = new long[n + m + 1];
        Arrays.fill(arr, 1);
        SegmentTree sgmTree = SegmentTree.build(arr);

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int top = n;
        for (int i = 0; i < m; i++) {
            int num = parseInt(st.nextToken());
            int idx = disks[num];
            sb.append(sgmTree.query(idx + 1, top)).append(" ");
            disks[num] = ++top;
            sgmTree.replace(idx, 0);
        }
        System.out.println(sb.toString());
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
            arr[a - 1] = b;
            replaceAt(1, 0, arr.length - 1, a - 1);
        }

        public long query(int a, int b) {
            return getSumBetween(1, 0, arr.length - 1, a - 1, b - 1);
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