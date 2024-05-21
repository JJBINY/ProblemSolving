package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G1 10868 최솟값
 * 세그먼트트리
 */
public class G1_10868_최솟값 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(br.readLine());
        }

        SegmentTree segmentTree = SegmentTree.init(arr);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken())-1;
            int b = parseInt(st.nextToken())-1;
            result.append(segmentTree.query(a, b))
                    .append("\n");
        }
        return result;
    }

    static class SegmentTree {
        int[] arr;
        int[] tree;

        static SegmentTree init(int[] arr) {
            SegmentTree segmentTree = new SegmentTree();
            segmentTree.arr = arr.clone();
            segmentTree.tree = new int[4 * arr.length];
            segmentTree.build(1, 0, arr.length - 1);
            return segmentTree;
        }

        private void build(int idx, int lo, int hi) {
            if (lo == hi) {
                tree[idx] = arr[lo];
                return;
            }
            int mid = (lo + hi) / 2;
            build(2 * idx, lo, mid);
            build(2 * idx + 1, mid + 1, hi);
            tree[idx] = Math.min(tree[2 * idx], tree[2 * idx + 1]);
        }

        public int query(int a, int b) {
            return query(1, 0, arr.length - 1, a, b);
        }

        private int query(int idx, int lo, int hi, int a, int b) {
            if (a > hi || b < lo) {
                return Integer.MAX_VALUE;
            } else if (a <= lo && hi <= b) {
                return tree[idx];
            }

            int mid = (lo + hi) / 2;
            return Math.min(
                    query(2 * idx, lo, mid, a, b),
                    query(2 * idx + 1, mid+1, hi, a, b));
        }
    }
}