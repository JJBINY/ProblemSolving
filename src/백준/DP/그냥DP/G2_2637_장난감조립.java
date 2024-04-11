package 백준.DP.그냥DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G2 2637 장난감 조립
 * DP, 그래프
 */
public class G2_2637_장난감조립 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M;
    static int[] dp = new int[101];
    static boolean[] isBasic = new boolean[101];

    static void solve(BufferedReader br) throws IOException {
        init(br);

        for (int i = 1; i <= N; i++) {
            if(isBasic[i]) {
                System.out.println(i + " " + func(Node.of(i)));
            }
        }
    }
    static int func(Node now){
        if(now.edges.isEmpty()){
            return 1;
        }else if(dp[now.id] >0){
            return dp[now.id];
        }

        for (Edge edge : now.edges) {
            dp[now.id] += func(edge.to) * edge.weight;
        }
        return dp[now.id];
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());
        Arrays.fill(isBasic, true);
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node a = Node.of(parseInt(st.nextToken()));
            Node b = Node.of(parseInt(st.nextToken()));
            int w = parseInt(st.nextToken());
            b.edges.add(new Edge(a, w));
            isBasic[a.id] = false;
        }
    }

    static class Node {
        static Node[] nodes = new Node[101];
        int id;
        List<Edge> edges = new ArrayList<>();

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }
    }

    static class Edge{
        Node to;
        int weight;

        public Edge(Node to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
