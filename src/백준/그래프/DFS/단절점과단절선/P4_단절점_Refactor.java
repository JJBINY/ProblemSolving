package 백준.그래프.DFS.단절점과단절선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4 단절점
 * https://www.acmicpc.net/problem/11266
 */
public class P4_단절점_Refactor {
    static int sequence;
    static Set<Integer> cutVertexes = new HashSet<>();

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
            if (root.sequence == 0) {
                dfs(root, null);
            }

        }

        System.out.println(cutVertexes.size());
        StringBuilder sb = new StringBuilder();
        cutVertexes.stream().sorted()
                .forEach(i -> sb.append(i).append(" "));
        System.out.print(sb.toString());
    }

    private static void dfs(Node now, Node parent) {
        now.sequence = ++sequence;
        now.low = sequence;

        int children = 0;
        for (Node.Edge edge : now.edges) {
            Node next = edge.a.equals(now) ? edge.b : edge.a;

            if (next.sequence == 0) {
                children++;
                dfs(next, now);
                now.low = Math.min(now.low, next.low);

                if (parent != null && next.low >= now.sequence) {
                    cutVertexes.add(now.id);
                }
            }else{
                now.low = Math.min(now.low, next.sequence);
//                now.low = Math.min(now.low, next.low); //next가 부모인 경우 부모가 이미 방문순서 빠른 노드로 갱신되었을 수 있음 이경우 해 보장 못함
            }
        }
        if (parent == null && children > 1) {
            cutVertexes.add(now.id);
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
                if (a.id > b.id) {
                    Node t = a;
                    a = b;
                    b = t;
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
3
2 5 10

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

6
1 2 6 8 11 20
 */
