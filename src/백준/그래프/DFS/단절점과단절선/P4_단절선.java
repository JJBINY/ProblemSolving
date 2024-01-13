package 백준.그래프.DFS.단절점과단절선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4 단절선
 * https://www.acmicpc.net/problem/11400
 */
public class P4_단절선 {
    static int sequence;
    static PriorityQueue<Node.Edge> cutLines = new PriorityQueue<>(Comparator
            .comparingInt((Node.Edge e)->e.a.id)
            .thenComparingInt(e->e.b.id));

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
            if (root.sequence > 0) continue;
            dfs(root,null);

        }

        System.out.println(cutLines.size());
        StringBuilder sb = new StringBuilder();
        while (!cutLines.isEmpty()){
            Node.Edge cutLine = cutLines.poll();
            sb.append(cutLine.toString()).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(Node now, Node parent) {
        now.sequence = ++sequence;
        now.low = sequence;
        for (Node.Edge edge : now.edges) {
            Node next = edge.a.equals(now) ? edge.b : edge.a;
            if(next == parent) continue;
            if (next.sequence == 0) {
                dfs(next,now);
                if (next.low > now.sequence) {
                    cutLines.add(edge);
                }
            }
            now.low = Math.min(now.low, next.low);
        }
    }

    static class Node {
        private static final Node[] nodes = new Node[100_001];
        private int id;
        private int sequence;
        private int low;
        private final List<Edge> edges = new ArrayList<>();


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
                if(a.id>b.id){
                    Node t = a;
                    a=b;
                    b=t;
                }
                this.a = a;
                this.b = b;
            }
            @Override
            public String toString() {
                return a.id + " " + b.id;
            }
        }

    }
}
/*
11 12
1 2
2 3
2 4
2 5
3 4
4 8
4 9
8 10
10 11
10 9
5 6
5 7

ANS
5
1 2
2 5
5 6
5 7
10 11
 */
