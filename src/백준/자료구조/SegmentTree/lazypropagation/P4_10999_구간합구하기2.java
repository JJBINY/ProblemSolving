package 백준.자료구조.SegmentTree.lazypropagation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


/**
 * P2 10999 구간 합 구하기 2
 * 세그먼트트리, lazy propagation
 */
public class P4_10999_구간합구하기2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseLong(br.readLine());
        }

        SegmentTree sgt = SegmentTree.build(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int c = parseInt(st.nextToken());
            if (a == 1) {
                long d = parseLong(st.nextToken());
                sgt.plusRange(b, c, d);
            } else {
                sb.append(sgt.query(b, c)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    static class SegmentTree {
        long[] tree;
        long[] arr;
        long[] lazy;

        public static SegmentTree build(long[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new long[4 * arr.length];
            segmentTree.lazy = new long[4 * arr.length];
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

        public long query(int a, int b) {
            return query(1, 0, arr.length - 1, a - 1, b - 1);
        }

        private long query(int idx, int l, int r, int a, int b) {
            updateLazy(idx, l, r);
            if (a > r || b < l) {
                return 0;
            }

            if (a <= l && r <= b) {
                return tree[idx];
            }

            int mid = (l + r) / 2;
            return query(idx * 2, l, mid, a, b) +
                    query(idx * 2 + 1, mid + 1, r, a, b);
        }

        public void plusRange(int a, int b, long val) {
            plusRange(1, 0, arr.length - 1, a-1, b-1, val);
        }

        private void plusRange(int idx, int l, int r, int a, int b, long val) {
            updateLazy(idx, l, r);

            if (a > r || b < l) {
                return;
            }

            if (a <= l && r <= b) {
                tree[idx] += (r - l + 1) * val;
                if (l != r) {
                    lazy[idx * 2] += val;
                    lazy[idx * 2 + 1] += val;
                }
                return;
            }


            int mid = (l + r) / 2;
            plusRange(idx * 2, l, mid, a, b, val);
            plusRange(idx * 2 + 1, mid + 1, r, a, b, val);
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }

        private void updateLazy(int idx, int l, int r) {
            if (lazy[idx] == 0) return;

            tree[idx] += (r - l + 1) * lazy[idx];
            if (l != r) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;

        }
    }
}