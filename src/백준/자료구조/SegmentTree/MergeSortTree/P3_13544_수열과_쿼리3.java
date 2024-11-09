package 백준.자료구조.SegmentTree.MergeSortTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * P3_13544_수열과_쿼리3
 * 세그먼트 트리, 머지 소트 트리
 */
public class P3_13544_수열과_쿼리3 {
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


    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        SegTree segTree = SegTree.build(arr);
//        System.out.println("Arrays.toString(segTree.tree) = " + Arrays.toString(segTree.tree));

        StringBuilder sb = new StringBuilder();
        int M = parseInt(br.readLine());
        int lastAns = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int c = parseInt(st.nextToken());
            lastAns = segTree.query(a ^ lastAns, b ^ lastAns, c ^ lastAns);
            sb.append(lastAns).append("\n");
        }

        return sb;
    }

    static class SegTree {
        private int[] arr;
        private Portion[] tree;

        private SegTree(int[] arr) {
            this.arr = arr;
            this.tree = new Portion[4 * arr.length];
        }

        public static SegTree build(int[] arr) {
            SegTree segTree = new SegTree(arr);
            segTree.build(1, 0, arr.length - 1);
            return segTree;
        }


        private void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = new Portion();
                tree[idx].list.add(arr[l]);
                return;
            }

            int m = l + r >> 1;
            build(idx << 1, l, m);
            build(idx << 1 | 1, m + 1, r);
            tree[idx] = tree[idx << 1].merge(tree[idx << 1 | 1]);
        }

        public int query(int from, int to, int k) {
            return query(1, 0, arr.length - 1, from, to, k);
        }

        private int query(int idx, int l, int r, int from, int to, int k) {
            if (to < l || from > r) return 0;
            if (from <= l && r <= to) return tree[idx].countGraterThan(k);
            int m = l + r >> 1;
            int left = query(idx << 1, l, m, from, to, k);
            int right = query(idx << 1 | 1, m + 1, r, from, to, k);
            return left + right;
        }

    }

    static class Portion {
        private final List<Integer> list = new ArrayList<>();

        public Portion merge(Portion other) {
            Portion portion = new Portion();
            int i = 0;
            int j = 0;
            while (i < this.list.size() && j < other.list.size()) {
                if (this.list.get(i) < other.list.get(j)) {
                    portion.list.add(this.list.get(i++));
                } else {
                    portion.list.add(other.list.get(j++));
                }
            }

            while (i < this.list.size()) {
                portion.list.add(this.list.get(i++));
            }

            while (j < other.list.size()) {
                portion.list.add(other.list.get(j++));
            }
            return portion;
        }

        public int countGraterThan(int k) {
            return list.size() - upperBound(k);
        }

        private int upperBound(int k) {
            int lo = -1;
            int hi = list.size();
            while (lo + 1 < hi) {
                int mid = lo + hi >> 1;
                if (list.get(mid) > k) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }

            return hi;
        }

        @Override
        public String toString() {
            return "Portion{" +
                    "list=" + list +
                    '}';
        }
    }
}