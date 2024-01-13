package 백준.그래프.DFS.SCC._2SAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P3 2-SAT4
 * https://www.acmicpc.net/problem/11281
 * 2-Satisfiability Problem
 * Ref: https://blog.naver.com/kks227/220803009418
 */
public class P3_2SAT4 {
    static int sequence;
    static Stack<List<Node>> sccs = new Stack<>();
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node.of(-a).nexts.add(Node.of(b));
            Node.of(-b).nexts.add(Node.of(a));
        }

        //scc탐색
        for (int i = 1; i < n + 1; i++) {
            if (!Node.of(i).isFinished) {
                dfs(Node.of(i));
            }
            if (!Node.of(-i).isFinished) {
                dfs(Node.of(-i));
            }
        }

        //만족가능성판별
        int ans = 1;
        for (int i = 1; i < n + 1; i++) {
            if (Node.of(i).scc == Node.of(-i).scc) {
                ans = 0;
                break;
            }
        }

        //만족가능성 출력
        System.out.println(ans);
        if (ans == 0) return;

        /*
        가능한 변수의 값 구하기

        조건문에서 P가 False이면 항상 참
        P Q P->Q
        T T T
        T F F
        F T T
        F F T
        -> 위상정렬 후 순서대로 방문하여 먼저 방문한 노드를 False(쌍을 이루는 노드는 자동으로 True)로 설정
        -> 먼저 방문한 scc에 속한 노드 즉 P가 False이므로 P->Q는 항상 참
         */
        int[] result = new int[n + 1];
        Arrays.fill(result, -1);
        StringBuilder sb = new StringBuilder();
        //scc 생성 순서 역순이 위상정렬 결과와 같음
        while (!sccs.isEmpty()) {
            List<Node> scc = sccs.pop();
            for (Node node : scc) {
                if (result[node.getId()] == -1) {
                    result[node.getId()] = node.val > 0 ? 0 : 1;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString());


    }

    static void dfs(Node now) {

        now.sequence = ++sequence;
        now.low = sequence;
        stack.push(now);
        for (Node next : now.nexts) {
            if (next.sequence == 0) {
                dfs(next);
                now.low = Math.min(now.low, next.low);
            } else if (!next.isFinished) {
                now.low = Math.min(now.low, next.sequence);
            }
        }
        if (now.low == now.sequence) {
            List<Node> scc = new ArrayList<>();
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                node.isFinished = true;
                node.scc = sccs.size();
                scc.add(node);
                if (node.equals(now)) {
                    break;
                }
            }
            sccs.push(scc);
        }
    }

    static class Node {
        private static final Node[] positive = new Node[10001];
        private static final Node[] negative = new Node[10001];
        private int val;
        private boolean isFinished;
        private int sequence;
        private int low;
        private int scc;
        private final List<Node> nexts = new ArrayList<>();

        private Node(int val) {
            this.val = val;
        }

        public static Node of(int val) {
            int id = Math.abs(val);
            if (val < 0) {
                if (negative[id] == null) {
                    negative[id] = new Node(val);
                }
                return negative[id];
            } else {
                if (positive[id] == null) {
                    positive[id] = new Node(val);
                }
                return positive[id];
            }
        }

        public int getId() {
            return Math.abs(val);
        }
    }
}
