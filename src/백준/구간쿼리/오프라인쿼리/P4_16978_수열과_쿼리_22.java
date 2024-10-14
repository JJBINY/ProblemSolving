package 백준.구간쿼리.오프라인쿼리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4_16978_수열과_쿼리_22
 * 세그먼트 트리, 오프라인 쿼리
 */
public class P4_16978_수열과_쿼리_22 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            SegTree segTree = SegTree.build(arr);

            int M = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            List<int[]> querys1 = new ArrayList<>();
            List<int[]> querys2 = new ArrayList<>();
            while (M-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    int i = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    querys1.add(new int[]{i, v});
                } else {
                    int k = Integer.parseInt(st.nextToken());
                    int i = Integer.parseInt(st.nextToken());
                    int j = Integer.parseInt(st.nextToken());
                    querys2.add(new int[]{k, i, j, querys2.size()});
                }
            }
            querys2.sort(Comparator.comparingInt(a -> a[0]));
            int cnt = 0;
            long[] results = new long[querys2.size()];
            for (int[] q2 : querys2) {
                int k = q2[0];
                int i = q2[1];
                int j = q2[2];
                int order = q2[3];
                while (cnt < k){
                    int[] q1 = querys1.get(cnt);
                    segTree.replace(q1[0] - 1, q1[1]);
                    cnt++;
                }
                results[order] = segTree.query(i - 1, j);
            }
            Arrays.stream(results).forEach(r -> sb.append(r).append("\n"));
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