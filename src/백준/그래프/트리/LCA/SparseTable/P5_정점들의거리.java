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
 * P5 정점들의 거리
 * https://www.acmicpc.net/problem/1761
 */
public class P5_정점들의거리 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<int[]> inputs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs.add(new int[]{Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())});
        }

        Tree tree = Tree.getInstance(n, inputs);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(tree.getDist(a, b) + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }


    static class Tree {
        private List<Node> nodes;

        private int[][] dp;

        private Tree() {
        }

        public static Tree getInstance(int n, List<int[]> edges) {
            Tree instance = new Tree();
            instance.init(n, edges);
            return instance;
        }

        private void init(int n, List<int[]> inputs) {
            nodes = IntStream.range(0, n + 1)
                    .mapToObj(Node::new).collect(Collectors.toList());
            for (int[] input : inputs) {
                Node a = nodes.get(input[0]);
                Node b = nodes.get(input[1]);
                Edge edge = new Edge(a, b, input[2]);
                a.edges.add(edge);
                b.edges.add(edge);
            }

            dp = new int[n + 1][(int) log2(n) + 2];

            Node root = nodes.get(1);
            initChildren(root, nodes.get(0));
        }

        private void initChildren(Node current, Node parent) {
            current.depth = parent.depth + 1;
            dp[current.id][0] = parent.id;
            for (int i = 1; i < log2(current.depth) + 1; i++) {
                int ancestorId = dp[current.id][i - 1];
                dp[current.id][i] = dp[ancestorId][i-1];
            }
            for (Edge edge : current.edges) {
                if (edge.isVisited()) continue;
                edge.visited = true;
                Node child = current == edge.a ? edge.b : edge.a;
                child.distFromRoot = current.distFromRoot + edge.dist;
                initChildren(child, current);
            }
        }

        public long getDist(int a, int b) {
            Node from = nodes.get(a);
            Node to = nodes.get(b);
            Node lca = getLCA(from, to);
            return from.distFromRoot + to.distFromRoot - 2 * lca.distFromRoot;
        }

        private Node getLCA(Node a, Node b) {
            if (a.depth < b.depth) {
                Node tmp = a;
                a = b;
                b = tmp;
            }

            for (int i = (int) log2(a.depth); i >= 0; i--) {
                Node ancestor = nodes.get(dp[a.id][i]);
                if (ancestor.depth >= b.depth) {
                    a = ancestor;
                }
            }

            if (a == b) return a;
            for (int i = (int) log2(a.depth); i >= 0; i--) {
                if (dp[a.id][i] != 0 && dp[a.id][i] != dp[b.id][i]) {
                    a = nodes.get(dp[a.id][i]);
                    b = nodes.get(dp[b.id][i]);
                }
            }
            return nodes.get(dp[a.id][0]);
        }

        private double log2(int x) {
            return Math.log(x) / Math.log(2);
        }

        private static class Node {
            private List<Edge> edges = new ArrayList<>();
            private int id;
            private int depth;
            private long distFromRoot;

            public Node(int id) {
                this.id = id;
            }
        }

        private static class Edge {
            Node a;
            Node b;
            int dist;
            boolean visited;

            public Edge(Node a, Node b, int dist) {
                this.a = a;
                this.b = b;
                this.dist = dist;
            }

            public boolean isVisited() {
                return visited;
            }
        }
    }
}

/*
28
1 2 19
1 3 68
2 4 27
2 5 17
2 6 29
3 7 71
4 8 38
4 9 13
5 10 4
7 11 81
8 12 5
9 13 75
10 14 70
10 15 68
11 16 7
11 17 86
12 18 23
12 19 47
14 20 26
15 21 92
17 22 55
18 23 20
19 24 49
20 25 2
20 26 95
21 27 37
22 28 4
1
3 28
 */