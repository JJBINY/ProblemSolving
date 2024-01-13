package 백준.그래프.DFS.단절점과단절선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4 단절점
 * https://www.acmicpc.net/problem/11266
 */
public class P4_단절점 {
    static int sequence;
    static Set<Integer> articulations = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int V, E;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                Node.addEdge(a, b);
            }
        }

        for (int i = 1; i <= V; i++) {
            Node root = Node.of(i);
            if (root.isVisited) continue;
            root.isVisited = true;
            root.sequence = ++sequence;
            dfs(root);
            if (root.children.size() < 2) {
                articulations.remove(root.id);
            }
        }

        System.out.println(articulations.size());
        StringBuilder sb = new StringBuilder();
        articulations.stream().sorted().forEach(i -> sb.append(i).append(" "));
        System.out.println(sb.toString());
    }

    private static int dfs(Node now) {
        now.low = now.sequence;
        for (Node.Edge edge : now.edges) {
            Node child = edge.a.equals(now) ? edge.b : edge.a;
            if (child.isVisited) continue;
            child.isVisited = true;
            child.sequence = ++sequence;
            now.children.add(child);
            int childMinLow = dfs(child);
            if (child.low == now.sequence) {
                articulations.add(now.id);
            }
        }

        for (Node.Edge edge : now.edges) {
            Node other = edge.a.equals(now) ? edge.b : edge.a;
            now.low = Math.min(now.low, other.low);
        }
        return now.low;
    }

    private static class Node {
        private static final Node[] nodes = new Node[10001];
        private int id;
        private int sequence;
        private int low;
        private boolean isVisited;
        private final List<Edge> edges = new ArrayList<>();
        private final List<Node> children = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }

        public static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public static void addEdge(int a, int b) {
            Node A = Node.of(a);
            Node B = Node.of(b);
            Edge edge = new Edge(A, B);
            A.edges.add(edge);
            B.edges.add(edge);
        }

        private static class Edge {
            Node a;
            Node b;

            private Edge(Node a, Node b) {
                this.a = a;
                this.b = b;
            }
        }
    }
}

/*
20 15
6 2
16 1
6 1
14 3
20 19
8 17
3 7
1 9
14 20
2 20
19 7
2 16
11 8
6 18
15 11
 */