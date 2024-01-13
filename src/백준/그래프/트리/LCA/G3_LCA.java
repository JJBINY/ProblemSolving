package 백준.그래프.트리.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * G3 LCA (Lowest Common Ancestor
 * https://www.acmicpc.net/problem/11437
 */
public class G3_LCA {
    static boolean[] visited;
    static List<Node> nodes;
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
        Node root = nodes.get(1);
        visited = new boolean[n + 1];
        makeTree(root, 0);

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
        while (a.depth>b.depth){
            a = a.parent;
        }
        while(a.depth<b.depth){
            b = b.parent;
        }
        while(a != b){
            a = a.parent;
            b = b.parent;
        }
        return a.id;
    }

    static void makeTree(Node now, int depth){
        visited[now.id] = true;
        now.depth = depth;

        for (Integer edge : now.edges) {
            if(visited[edge]) continue;
            Node child = nodes.get(edge);
            child.parent = now;
            makeTree(child,depth+1);
        }
    }

    static class Node{
        int id;
        int depth;
        Node parent;
        List<Integer> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }
}