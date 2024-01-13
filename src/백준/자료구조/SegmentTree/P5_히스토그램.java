package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * P5 히스토그램
 * https://www.acmicpc.net/problem/1725
 */
public class P5_히스토그램 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] histogram = new int[n];
        for (int i = 0; i < n; i++) {
            histogram[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree tree = SegmentTree.create(histogram);
        System.out.println(tree.getMaxArea(0, n-1));
    }

    static class SegmentTree {
        private int[] tree;
        private int[] arr;
        static public SegmentTree create(int[] histogram) {
            SegmentTree tree = new SegmentTree();
            tree.arr = new int[histogram.length + 1];
            for (int i = 0; i < histogram.length; i++) {
                tree.arr[i] = histogram[i];
            }
            tree.arr[histogram.length] = Integer.MAX_VALUE;
            tree.tree = new int[4 * histogram.length];
            tree.build(1, 0, histogram.length - 1);
            return tree;
        }
        private void build(int idx, int l, int r) {
            if (l == r) {
                tree[idx] = l;
                return;
            }

            int mid = (l + r) / 2;
            build(2 * idx, l, mid);
            build(2 * idx + 1, mid + 1, r);
            int lc = tree[2 * idx];
            int rc = tree[2 * idx + 1];
            if (arr[lc] < arr[rc]) {
                tree[idx] = lc;
            } else {
                tree[idx] = rc;
            }
        }
        public int getMaxArea(int from, int to){
            if(from>to){
                return 0;
            }else if(from == to){
                return arr[from];
            }
            int pivot = find(1, 0, arr.length - 2, from, to);
            int area = arr[pivot] * (to - from + 1);
            area = Math.max(area,getMaxArea(from, pivot-1));
            area = Math.max(area, getMaxArea(pivot+1, to));
            return area;
        }
        private int find(int idx, int l, int r, int from, int to) {
            if (r < from || to < l) {
                return arr.length - 1;
            }

            if (from <= l && r <= to) {
                return tree[idx];
            }

            int mid = (l + r) / 2;
            int lc = find(2 * idx, l, mid, from, to);
            int rc = find(2 * idx + 1, mid + 1, r, from, to);

            if (arr[lc] < arr[rc]) {
                return lc;
            } else {
                return rc;
            }
        }

    }
}