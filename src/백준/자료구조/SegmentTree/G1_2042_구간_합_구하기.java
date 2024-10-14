package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1_2042_구간_합_구하기
 * 세그먼트트리
 */
public class G1_2042_구간_합_구하기 {

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
            SegTree tree = SegTree.build(arr);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m + k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    long c = Long.parseLong(st.nextToken());
                    tree.replace(b-1, c);
                } else {
                    int c = Integer.parseInt(st.nextToken());
                    sb.append(tree.query(b-1, c));
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }
    static class SegTree{
        private long[] arr;
        private long[] tree;

        private SegTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[arr.length * 4];
        }

        public static SegTree build(long[] arr){
            SegTree segTree = new SegTree(arr);
            segTree.build(1, 0, arr.length);
            return segTree;
        }

        private void build(int cur, int l, int r){
            if(l+1==r){
                tree[cur] = arr[l];
                return;
            }

            int m = (l + r) / 2;
            build(cur * 2, l, m);
            build(cur * 2+1, m, r);
            tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
        }

        public void replace(int idx, long val){
            arr[idx] = val;
            replace(1, idx, 0, arr.length);
        }

        private void replace(int cur, int target, int l, int r){
            if(l+1 == r){
                tree[cur] = arr[target];
                return;
            }

            int m = (l + r) / 2;
            if(target <m){
                replace(cur*2, target, l, m);
            }else{
                replace(cur*2 + 1, target, m, r);
            }
            tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
        }

        public long query(int from, int to){
            return query(1, 0, arr.length, from, to);
        }

        private long query(int cur, int l, int r, int from, int to){
            if(to <= l || from >=r) return 0;
            if(from <= l && r <= to) return tree[cur];

            int m = (l + r) / 2;
            long lr = query(cur * 2, l, m, from, to);
            long rr = query(cur * 2 + 1, m, r, from, to);
            return lr + rr;
        }

    }
}