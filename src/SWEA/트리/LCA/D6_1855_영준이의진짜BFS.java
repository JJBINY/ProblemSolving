package SWEA.트리.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * D6 1855 영준이의 진짜 BFS
 * 트리, LCA, Sparse Table, BFS
 */
public class D6_1855_영준이의진짜BFS {

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static int N, LEN;
    static int[][] parents;

    static Object solve(BufferedReader br) throws IOException {
        init(br);
        Queue<Node> visitQ = bfs(); //O(V+E) = O(2V) = O(V) == O(N)
        initSparseTable();

        long result = 0;
        Node from = visitQ.poll();
        while (!visitQ.isEmpty()) { // O(N)
            Node to = visitQ.peek();
            Node lca = lca(from, to); // O(logN)
            result += from.depth - lca.depth;
            result += to.depth - lca.depth;
            from = visitQ.poll();
        }
        return result;
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        LEN = log2(N);
        parents = new int[N + 1][LEN+1];
        Node.init(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= N; i++) {
            Node parent = Node.of(parseInt(st.nextToken()));
            parent.addChild(Node.of(i));
        }
    }

    private static Queue<Node> bfs() {
        Queue<Node> visitQ = new ArrayDeque<>();
        Queue<Node> queue = new ArrayDeque<>();
        Node root = Node.of(1);
        root.depth = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visitQ.offer(now);
            for (Node child : now.children) {
                child.depth = now.depth + 1;
                parents[child.id][0] = now.id;
                queue.offer(child);

            }
        }
        return visitQ;
    }

    private static void initSparseTable() {
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= LEN; j++) {
                int ancestor = parents[i][j - 1];
                parents[i][j] = parents[ancestor][j - 1];
                if (parents[i][j] == 0) break;
            }
        }
    }

    private static Node lca(Node from, Node to) {
        Node u = from;
        Node v = to;
        if (u.depth < v.depth) {
            u = to;
            v = from;
        }


        for (int i = LEN; i >= 0; i--) {
            Node parent = Node.of(parents[u.id][i]);
            if (parent.depth >= v.depth) {
                u = parent;
            }
        }

        if (u == v) return u;

        for (int i = LEN; i >= 0; i--) {
            if (parents[u.id][i] != parents[v.id][i]) {
                u = Node.of(parents[u.id][i]);
                v = Node.of(parents[v.id][i]);
            }
        }
        return Node.of(parents[u.id][0]);
    }

    private static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    static class Node {
        static Node[] nodes;
        int id;
        int depth;
        List<Node> children = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }

        static void init(int N) {
            nodes = new Node[N + 1];
        }

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public void addChild(Node child) {
            children.add(child);
        }

    }
}