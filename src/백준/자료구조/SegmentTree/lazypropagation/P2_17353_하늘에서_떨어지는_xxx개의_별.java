package 백준.자료구조.SegmentTree.lazypropagation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P2_17353_하늘에서_떨어지는_xxx개의_별
 * 세그먼트 트리, Lazy Propagation
 * ref: https://rebro.kr/92
 */
public class P2_17353_하늘에서_떨어지는_xxx개의_별 {

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
        int N = Integer.parseInt(br.readLine());
        SegTree segTree = SegTree.build(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N+1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            segTree.addRange(i-1, i, A[i] - A[i - 1]);
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if(cmd == 1){
                int L = Integer.parseInt(st.nextToken());
                int R = Integer.parseInt(st.nextToken());
                segTree.addRange(L - 1, R, 1);
                segTree.addRange(R, R+1, -(R-L+1));
            }else{
                int X = Integer.parseInt(st.nextToken());
                sb.append(segTree.query(0, X)).append("\n");
            }
        }

        return sb;
    }

    static class SegTree {
        int N;
        private long[] tree;
        private long[] lazy;

        private SegTree(int N) {
            this.N = N;
            this.tree = new long[N * 4];
            this.lazy = new long[N * 4];
        }

        public static SegTree build(int N) {
            return new SegTree(N);
        }

        public void addRange(int from, int to, long val) {
            addRange(1, 0, N, from, to, val);
        }

        private void addRange(int node, int l, int r, int from, int to, long val) {
            propagate(node, l, r);
            if (to <= l || r <= from) return;
            if (from <= l && r <= to) {
                lazy[node] += val;
                propagate(node, l, r);
                return;
            }

            int m = (l + r) / 2;
            addRange(node * 2, l, m, from, to, val);
            addRange(node * 2 + 1, m, r, from, to, val);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int from, int to) {
            return query(1, 0, N, from, to);
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
            if (l + 1 < r) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] += (r - l) * lazy[node];
            lazy[node] = 0;
        }

    }
}
