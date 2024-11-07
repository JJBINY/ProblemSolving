package 백준.자료구조.SegmentTree.금광세그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P1_16993_연속합과_쿼리
 * 세그먼트 트리
 */
public class P1_16993_연속합과_쿼리 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine()); // <= 500,000

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegTree segTree = SegTree.build(arr);

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            sb.append(segTree.query(i, j)).append("\n");
        }

        return sb;
    }

    static class SegTree {
        static final int INF = (int) 1e9;
        static final State nullState = new State(0, -INF, -INF, -INF);
        private int[] arr;
        private State[] tree;

        private SegTree(int[] arr) {
            this.arr = arr;
            this.tree = new State[arr.length * 4];
        }

        public static SegTree build(int[] arr) {
            SegTree segTree = new SegTree(arr);
            segTree.build(1, 0, arr.length - 1);
            return segTree;
        }

        private void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = new State(arr[l], arr[l], arr[l], arr[l]);
                return;
            }
            int m = (l + r) / 2;
            build(idx * 2, l, m);
            build(idx * 2 + 1, m + 1, r);
            tree[idx] = getState(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public int query(int from, int to) {
            return query(1, 0, arr.length - 1, from, to).tMax;
        }

        private State query(int idx, int l, int r, int from, int to) {
            if (to < l || r < from) return nullState;
            if (from <= l && r <= to) return tree[idx];

            int m = (l + r) / 2;
            State left = query(idx * 2, l, m, from, to);
            State right = query(idx * 2 + 1, m + 1, r, from, to);
            return getState(left, right);
        }

        private State getState(State left, State right) {
            int total = left.total + right.total;
            int lMax = max(left.lMax, left.total + right.lMax);
            int rMax = max(right.rMax, left.rMax + right.total);
            int tMax = max(left.tMax, right.tMax, left.rMax + right.lMax);
            return new State(total, tMax, lMax, rMax);
        }

        private int max(int... nums) {
            int max = -INF;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }

        static class State {
            int total;
            int tMax;
            int lMax;
            int rMax;

            public State(int total, int tMax, int lMax, int rMax) {
                this.total = total;
                this.tMax = tMax;
                this.lMax = lMax;
                this.rMax = rMax;
            }
        }
    }
}
