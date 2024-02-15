package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G1 14438 수열과 쿼리 17
 * 자료구조, 세그먼트 트리
 */
public class G1_14438_수열과쿼리17 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = parseInt(st.nextToken());
            }
            SegmentTree tree = SegmentTree.build(arr);

            int M = parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = parseInt(st.nextToken());
                int a = parseInt(st.nextToken());
                int b = parseInt(st.nextToken());
                if(cmd == 1){
                    tree.replace(a, b);
                }else{
                    sb.append(tree.query(a, b)).append("\n");
                }
            }

            System.out.println(sb.toString());
        }
    }

    static class SegmentTree {
        int[] tree;
        int[] arr;

        public static SegmentTree build(int[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new int[4 * arr.length];
            segmentTree.buildTree(1, 0, arr.length - 1);
            return segmentTree;
        }

        public int query(int a, int b) {
            return getValue(1, 0, arr.length - 1, a-1, b-1);
        }

        public void replace(int idx, int val) {
            arr[idx-1] = val;
            updateTree(1, 0, arr.length - 1, idx-1);
        }

        private void updateTree(int idx, int l, int r, int target) {
            if (l == r) {
                tree[idx] = arr[target];
                return;
            }
            int mid = (l + r) / 2;
            if (l <= target && target <= mid) {
                updateTree(idx * 2, l, mid, target);
            } else {
                updateTree(idx * 2 + 1, mid + 1, r, target);
            }
            setValue(idx);
        }

        private int getValue(int idx, int l, int r, int a, int b) {
            if (a > r || b < l) { //범위 벗어남
                return Integer.MAX_VALUE;
            }

            if (a <= l && r <= b) { // 범위 포함
                return tree[idx];
            }

            //범위 미포함
            int mid = (l + r) / 2;
            return Math.min(getValue(idx * 2, l, mid, a, b),
                    getValue(idx * 2 + 1, mid + 1, r, a, b));
        }

        private void buildTree(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            buildTree(idx * 2, l, mid);
            buildTree(idx * 2 + 1, mid + 1, r);
            setValue(idx);
        }

        private void setValue(int idx) {
            tree[idx] = Math.min(tree[idx * 2],tree[idx * 2 + 1]);
        }

    }
}
