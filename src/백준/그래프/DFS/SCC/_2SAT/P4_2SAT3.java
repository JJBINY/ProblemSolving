package 백준.그래프.DFS.SCC._2SAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * P4 2-SAT 3
 * https://www.acmicpc.net/problem/11280
 * 2-Satisfiability Problem
 * Ref: https://blog.naver.com/kks227/220803009418
 */
public class P4_2SAT3 {
    static int sequence;
    static int scc;
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
        for (int i = 1; i < n+1; i++) {
            if(!Node.of(i).isFinished){
                dfs(Node.of(i));
            }
            if(!Node.of(-i).isFinished){
                dfs(Node.of(-i));
            }
        }

        //만족가능성판별
        int ans = 1;
        for (int i = 1; i < n+1; i++) {
            if(Node.of(i).scc == Node.of(-i).scc){
                ans = 0;
                break;
            }
        }
        System.out.println(ans);
    }

    static void dfs(Node now){

        now.sequence = ++sequence;
        now.low = sequence;
        stack.push(now);
        for (Node next : now.nexts) {
            if(next.sequence==0){
                dfs(next);
                now.low = Math.min(now.low, next.low);
            }else if(!next.isFinished){
                now.low = Math.min(now.low, next.sequence);
            }
        }
        if(now.low == now.sequence){
            scc +=1;
            while (!stack.isEmpty()){
                Node node = stack.pop();
                node.isFinished = true;
                node.scc = scc;
                if(node.equals(now)){
                    break;
                }
            }
        }
    }

    static class Node{
        private static final Node[] positive = new Node[10001];
        private static final Node[] negative = new Node[10001];
        private boolean isFinished;
        private int sequence;
        private int low;
        private int scc;
        private final List<Node> nexts = new ArrayList<>();

        public static Node of(int val){
            int id = Math.abs(val);
            if(val<0){
                if(negative[id] == null){
                    negative[id] = new Node();
                }
                return negative[id];
            }else{
                if(positive[id] == null){
                    positive[id] = new Node();
                }
                return positive[id];
            }
        }
    }
}
