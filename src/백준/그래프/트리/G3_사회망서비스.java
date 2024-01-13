package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * G3 사회망 서비스
 * https://www.acmicpc.net/problem/2533
 */
public class G3_사회망서비스 {
    static int[][] dp;
    static List<Node> nodes;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n][2];
        visited = new boolean[n];
        nodes = IntStream.range(0, n).mapToObj(Node::new).collect(Collectors.toList());
        for (int i = 0; i < n - 1; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(s->Integer.parseInt(s)-1)
//                    .sorted()
                    .toArray();
            int a = inputs[0];
            int b = inputs[1];
            Edge edge = new Edge(a, b);
            nodes.get(a).edges.add(edge);
            nodes.get(b).edges.add(edge);
        }

        dfs(nodes.get(0));
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }


    static void dfs(Node now){
        visited[now.id] = true;
        dp[now.id][0] = 0;
        dp[now.id][1] = 1;
        for (Edge edge : now.edges) {
            int childId = edge.a == now.id ? edge.b : edge.a;
            if(visited[childId]) continue;
            dfs(nodes.get(childId));
            dp[now.id][0] += dp[childId][1];
            dp[now.id][1] += Math.min(dp[childId][0], dp[childId][1]);
        }
    }

    static class Edge{
        int a;
        int b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static class Node{
        int id;
        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }
}
