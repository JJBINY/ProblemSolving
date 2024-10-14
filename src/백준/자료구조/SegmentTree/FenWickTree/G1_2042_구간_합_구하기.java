package 백준.자료구조.SegmentTree.FenWickTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1_2042_구간_합_구하기
 * 세그먼트 트리, 펜윅 트리
 */
public class G1_2042_구간_합_구하기 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            FenWickTree tree = new FenWickTree(n);
            for (int i = 1; i <= n; i++) {
                long val = Long.parseLong(br.readLine());
                tree.update(i, val);
            }


            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m + k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    long c = Long.parseLong(st.nextToken());
                    tree.update(b, c);
                } else {
                    int c = Integer.parseInt(st.nextToken());
                    sb.append(tree.query(b, c));
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    static class FenWickTree {
        private long[] arr;
        private long[] tree;

        private FenWickTree(int N) {
            this.arr = new long[N+1];
            this.tree = new long[N + 1];
        }

        public void update(int i, long val) {
            long diff = val - arr[i];
            arr[i] = val;
            while (i < tree.length) {
                tree[i] += diff;
                i += (i & -i);
            }
        }

        public long query(int from, int to) {
            return query(to) - query(from-1);
        }

        // [0,idx]
        public long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= (i & -i);
            }
            return sum;
        }
    }
}