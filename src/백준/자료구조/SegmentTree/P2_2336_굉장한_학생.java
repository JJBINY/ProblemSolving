package 백준.자료구조.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P2_2336_굉장한_학생
 * 세그먼트 트리, 스위핑
 */
public class P2_2336_굉장한_학생 {
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

    /*
        굉장한 학생?
        다음 조건을 만족하는 학생 x가 "없는" 경우 (now.a > x.a) and (now.b > x.b) and (now.c > x.b)
        즉, (now.a < x.a) or (now.b < x.b) or (now.c < x.c)를 만족하는지 확인하면 된다.
        a, b, c 세 시험에 대한 등수가 주어졌을 때,
        1. a 오름차순으로 학생 리스트 정렬
        2. 세그먼트 트리에 0~b 범위 쿼리 결과가 존재하지 않는다면?
        2-1. now.a > x.a인 x에 대해 now.b < x.b 이다. => 굉장한 학생
        3. 쿼리 결과 r = min(x.c)가 존재한다면?
        3-1. now.a > x.a 이고 now.b > x.b인 x가 존재한다.
        3-2. a.c < r이면 now.c < x.c이다. => 굉장한 학생
        4. a.c > r 이면 now.c > x.c인 x가 존재한다.
     */

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine()); // <= 500,000

        List<Student> students = new ArrayList<>(N+1);
        for (int i = 0; i < N; i++) {
            students.add(new Student());
        }

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st1.nextToken())-1;
            int b = Integer.parseInt(st2.nextToken())-1;
            int c = Integer.parseInt(st3.nextToken())-1;
            students.get(a).setA(i+1);
            students.get(b).setB(i+1);
            students.get(c).setC(i+1);
        }

        PriorityQueue<Student> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.a));
        pq.addAll(students);

        int[] arr = new int[N + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        SegTree segTree = SegTree.build(arr);

        int cnt = 0;
        while (!pq.isEmpty()) {
            Student student = pq.poll();
            int minC = segTree.query(1, student.b);
            if (student.c < minC) {
                cnt++;
            }
            segTree.replace(student.b, student.c);
        }

        return cnt;
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
            tree[cur] = Math.min(tree[cur * 2], tree[cur * 2 + 1]);
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
            tree[cur] = Math.min(tree[cur * 2], tree[cur * 2 + 1]);
        }

        public int query(int from, int to){
            return query(1, 0, arr.length, from, to);
        }

        private int query(int cur, int l, int r, int from, int to){
            if(to <= l || from >=r) return Integer.MAX_VALUE;
            if(from <= l && r <= to) return tree[cur];

            int m = (l + r) / 2;
            int lr = query(cur * 2, l, m, from, to);
            int rr = query(cur * 2 + 1, m, r, from, to);
            return Math.min(lr, rr);
        }

    }

    static class Student{
        int a;
        int b;
        int c;

        public Student() {
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }

        public void setC(int c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }
}

/*
10
2 5 3 8 10 7 1 6 9 4
1 2 3 4 5 6 7 8 9 10
3 8 7 10 5 4 1 2 6 9
=> 4
 */