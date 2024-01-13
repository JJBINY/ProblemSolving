package 백준.그래프.트리.LCA.SparseTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P4 도로 네트워크
 * https://www.acmicpc.net/problem/3176
 */
public class P4_도로네트워크 {

    static List<Node> nodes = new ArrayList<>();
    static int[][] minTable;
    static int[][] maxTable;
    static int[][] parents;
    static int min;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parents = new int[n + 1][(int)log2(n)+1];
        minTable = new int[n + 1][(int)log2(n)+1];
        maxTable = new int[n + 1][(int)log2(n)+1];
        for (int i = 0; i < n + 1; i++) {
            nodes.add(new Node(i));
            Arrays.fill(minTable[i], Integer.MAX_VALUE);
            Arrays.fill(maxTable[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(a, b, c);
            nodes.get(a).edges.add(edge);
            nodes.get(b).edges.add(edge);
        }
        nodes.get(1).depth = 1;
        init(1);

        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node d = nodes.get(Integer.parseInt(st.nextToken()));
            Node e = nodes.get(Integer.parseInt(st.nextToken()));
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            solve(d, e);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    static void init(int id) {
        Node now = nodes.get(id);
        for (Edge edge : now.edges) {
            if (edge.isVisited) continue;
            edge.isVisited = true;
            int next = edge.a == id ? edge.b : edge.a;

            Node child = nodes.get(next);
            child.depth = now.depth + 1;
            parents[next][0] = now.id;
            minTable[next][0] = maxTable[next][0] = edge.dist;
            for (int i = 1; i < (int)log2(child.depth) + 1; i++) {
                int parent = parents[next][i - 1];
                if (parent <=1) break;
                parents[next][i] = parents[parent][i - 1];
                maxTable[next][i] = Math.max(maxTable[next][i-1], maxTable[parent][i - 1]);
                minTable[next][i] = Math.min(minTable[next][i-1], minTable[parent][i - 1]);
            }
            init(next);
        }
    }

    static void solve(Node a, Node b) {
        if (a.depth < b.depth) {
            Node t = a;
            a = b;
            b = t;
        }

        for (int i = (int) log2(a.depth); i >= 0; i--) {
            Node parent = nodes.get(parents[a.id][i]);

            if (parent.id != 0 && parent.depth >= b.depth) {
                max = Math.max(max, maxTable[a.id][i]);
                min = Math.min(min, minTable[a.id][i]);
                a = parent;
            }
        }

        if (a.equals(b)) return;

        for (int i = (int) log2(a.depth); i >= 0; i--) {
            if (parents[a.id][i] != 0 && parents[a.id][i] != parents[b.id][i]) {
                max = Math.max(max, Math.max(maxTable[a.id][i], maxTable[b.id][i]));
                min = Math.min(min, Math.min(minTable[a.id][i], minTable[b.id][i]));
                a = nodes.get(parents[a.id][i]);
                b = nodes.get(parents[b.id][i]);
            }
        }
        max = Math.max(max, Math.max(maxTable[a.id][0], maxTable[b.id][0]));
        min = Math.min(min, Math.min(minTable[a.id][0], minTable[b.id][0]));
    }

    static class Node {
        int id;
        int depth;

        List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    static class Edge {
        int a;
        int b;
        int dist;
        boolean isVisited;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
}