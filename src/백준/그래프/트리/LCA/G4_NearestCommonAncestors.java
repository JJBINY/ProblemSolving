package 백준.그래프.트리.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G4 NearestCommonAncestors
 * https://www.acmicpc.net/problem/3584
 */
public class G4_NearestCommonAncestors {
    static int n;
    static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            n = Integer.parseInt(br.readLine());
            nodes = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                nodes.add(new Node(i));
            }
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                Node parent = nodes.get(Integer.parseInt(st.nextToken()));
                Node child = nodes.get(Integer.parseInt(st.nextToken()));
                child.parent = parent;
                parent.children.add(child);
            }
            Node root = nodes.stream().filter(n -> n.parent == null && n.id > 0).findFirst().get();
            root.depth = 1;
            init(root);

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findLCA(nodes.get(a), nodes.get(b))).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void init(Node now) {
        for (Node child : now.children) {
            child.depth = now.depth + 1;
            init(child);
        }
    }

    static int findLCA(Node a, Node b) {
        if (a.depth < b.depth) {
            Node tmp = a;
            a = b;
            b = tmp;
        }

        while (a.depth > b.depth) {
            a = a.parent;
        }

        while (a.id != b.id) {
            a = a.parent;
            b = b.parent;
        }
        return a.id;
    }

    static class Node {
        int id;
        int depth;
        Node parent;
        List<Node> children = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }
}