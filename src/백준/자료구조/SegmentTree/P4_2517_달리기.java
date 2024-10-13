package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P4_2517_달리기
 * 세그먼트 트리, 경로 압축
 */
public class P4_2517_달리기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print(solve(br));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] powers = new int[N];
        for (int i = 0; i < N; i++) {
            powers[i] = Integer.parseInt(br.readLine());
        }

        // power의 범위를 [0,N)으로 압축
        int[] sortedPowers = Arrays.stream(powers).sorted().toArray();
        Map<Integer, Integer> compressedMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            compressedMap.put(sortedPowers[i], i);
        }
//        System.out.println("compressedMap = " + compressedMap);


        // 해당 power에 해당하는 선수가 존재하면 0 아니면 1인 배열에 대한 세그먼트 트리 생성
        int[] isExists = new int[N];
        SegTree segTree = SegTree.build(isExists);

        // 정답 구하기 : O(NlogN)
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int power = compressedMap.get(powers[i]);
            // 앞서 달리고 있는 선수 중 자신보다 실력이 좋은 선수가 몇명 있는지 쿼리
            int query = 1 + segTree.query(power + 1, N);
            res.append(query).append("\n");
            segTree.replace(power, 1); // power에 해당하는 선수가 존재함을 마킹
        }
        return res.toString();
    }

    static class SegTree{
        private int[] arr;
        private int[] tree;

        private SegTree(int[] arr) {
            this.arr = arr;
            this.tree = new int[arr.length * 4];
        }

        public static SegTree build(int[] arr){
            SegTree segTree = new SegTree(arr);
//            segTree.build(1, 0, arr.length);
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

        public void replace(int idx, int val){
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

        public int query(int from, int to){
            return query(1, 0, arr.length, from, to);
        }

        private int query(int cur, int l, int r, int from, int to){
            if(to <= l || from >=r) return 0;
            if(from <= l && r <= to) return tree[cur];

            int m = (l + r) / 2;
            int lr = query(cur * 2, l, m, from, to);
            int rr = query(cur * 2 + 1, m, r, from, to);
            return lr + rr;
        }

    }
}