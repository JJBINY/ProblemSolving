package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 15681 트리와쿼리
 * 그래프, 트리, DFS
 */
public class G5_15681_트리와쿼리 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int R = parseInt(st.nextToken());
        int Q = parseInt(st.nextToken());

        Node.init(N);
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            Node.of(parseInt(st.nextToken()))
                    .addEdge(Node.of(parseInt(st.nextToken())));
        }

        Node.of(R).makeTree();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            result.append(Node.query(parseInt(br.readLine())))
                    .append("\n");
        }
        return result;
    }

    static class Node {
        static Node[] nodes;
        int id;
        int nChild;
        boolean visited;
        List<Node> edges = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }

        public static void init(int N) {
            nodes = new Node[N + 1];
        }

        public static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public static int query(int id) {
            return nodes[id].nChild + 1;
        }

        public void addEdge(Node node) {
            this.edges.add(node);
            node.edges.add(this);
        }

        public int makeTree() {
            visited = true;
            nChild = edges.stream()
                    .filter(n -> !n.visited)
                    .mapToInt(Node::makeTree)
                    .sum();
            return nChild + 1;
        }
    }
}