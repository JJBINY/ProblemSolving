package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1 최솟값과 최댓값
 * https://www.acmicpc.net/problem/2357
 */
public class G1_최솟값과최댓값 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree segmentTree = SegmentTree.build(arr);
        StringBuilder sb = new StringBuilder();
        while (m-- >0){
            st = new StringTokenizer(br.readLine());
            SegmentTree.Pair<Integer, Integer> result = segmentTree
                    .query(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
            sb.append(result);
        }
        System.out.println(sb.toString());
    }

    static class SegmentTree {
        Pair<Integer,Integer>[] tree;
        int[] arr;
        private static Pair<Integer,Integer> NONEPAIR = new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE);

        public static SegmentTree build(int[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new Pair[4 * arr.length];
            segmentTree.buildTree(1, 0, arr.length - 1);
            return segmentTree;
        }

        public Pair<Integer,Integer> query(int a, int b) {
            return getValBetween(1, 0, arr.length - 1, a-1, b-1);
        }

        private void buildTree(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = new Pair(arr[l],arr[l]);
                return;
            }
            int mid = (l + r) / 2;
            buildTree(idx * 2, l, mid);
            buildTree(idx * 2 + 1, mid + 1, r);
            Pair<Integer, Integer> lc = tree[idx * 2];
            Pair<Integer, Integer> rc = tree[idx * 2+1];
            tree[idx] = new Pair(Math.min(lc.a, rc.a), Math.max(lc.b, rc.b));
        }

        private Pair<Integer,Integer> getValBetween(int idx, int l, int r, int a, int b) {
            if (a > r || b < l) {
                return NONEPAIR;
            }

            if (a <= l && r <= b) {
                return tree[idx];
            }

            int mid = (l + r) / 2;
            Pair<Integer, Integer> lc = getValBetween(idx * 2, l, mid, a, b);
            Pair<Integer, Integer> rc = getValBetween(idx * 2 + 1, mid + 1, r, a, b);
            return new Pair<>(Math.min(lc.a, rc.a), Math.max(lc.b, rc.b));
        }

        static class Pair<A,B>{
            A a;
            B b;

            public Pair(A a, B b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public String toString() {
                return a + " " + b+"\n";
            }
        }
    }

}