package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G1 1275 커피숍2
 * 자료구조, 세그먼트트리
 */
public class G1_1275_커피숍2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, Q;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        Q = parseInt(st.nextToken());
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        SegmentTree sTree = SegmentTree.build(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            sb.append(sTree.query(x, y)).append("\n");
            sTree.replace(a, (long) b);
        }
        System.out.println(sb.toString());
    }

    static class SegmentTree {
        long[] tree;
        long[] arr;

        public static SegmentTree build(long[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new long[3 * arr.length];
            segmentTree.buildTree(1, 0, arr.length - 1);
            return segmentTree;
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
        public void replace(int a, long b) {
            arr[a - 1] = b;
            replace(1, 0, arr.length - 1, a - 1);
        }

        private void replace(int idx, int l, int r, int target) {
            if (l == r) {
                tree[idx] = arr[target];
                return;
            }
            int mid = (l + r) / 2;
            if (l <= target && target <= mid) {
                replace(idx * 2, l, mid, target);
            } else {
                replace(idx * 2 + 1, mid + 1, r, target);
            }
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }

        public long query(int a, int b) {
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            return getSumBetween(1, 0, arr.length - 1, a - 1, b - 1);
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