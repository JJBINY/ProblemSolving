package 백준.그래프.트리.LCA.SparseTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * P5 LCA2
 * https://www.acmicpc.net/problem/11438
 */
public class P5_LCA2 {
    static boolean[] visited;
    static List<Node> nodes;
    static int[][] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodes = IntStream.range(0,n+1).mapToObj(Node::new).collect(Collectors.toList());

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes.get(a).edges.add(b);
            nodes.get(b).edges.add(a);
        }
        visited = new boolean[n + 1];
        parents = new int[n + 1][20];
        makeTree(nodes.get(1), nodes.get(0));

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(query(nodes.get(a), nodes.get(b)));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int query(Node a, Node b) {
        if(a.depth<b.depth){
            Node tmp = a;
            a=b;
            b=tmp;
        }

        for (int i = (int)log2(a.depth); i >=0; i--) {
            Node parent = nodes.get(parents[a.id][i]);
            if(parent.depth>= b.depth){
                a = parent;
            }
        }

        if(a==b){
            return a.id;
        }

        for (int i = (int)log2(a.depth); i >=0; i--) {
            if(parents[a.id][i]!=0 && parents[a.id][i] != parents[b.id][i]){
                a = nodes.get(parents[a.id][i]);
                b = nodes.get(parents[b.id][i]);
            }
        }

        return parents[a.id][0];
    }

    static void makeTree(Node now, Node parent){
        visited[now.id] = true;
        now.depth = parent.depth+1;
        int len = (int) log2(now.depth) + 1;
        parents[now.id][0] = parent.id;
        for (int i = 1; i < len; i++) {
            int ancestor = parents[now.id][i - 1];
            if(parents[ancestor].length<i) continue;
            parents[now.id][i] = parents[ancestor][i - 1];
        }
        for (Integer edge : now.edges) {
            if(visited[edge]) continue;
            makeTree(nodes.get(edge),now);
        }

    }
    static double log2(int x){
        return Math.log(x) / Math.log(2);
    }

    static class Node{
        int id;
        int depth;
        List<Integer> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }
}