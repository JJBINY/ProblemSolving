package 백준.그래프.이분그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 1707 이분 그래프
 * 이분그래프, DFS
 */
public class G4_1707_이분그래프 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int K = parseInt(br.readLine());
            while (K-- > 0) {
                solve(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = parseInt(st.nextToken());
        int E = parseInt(st.nextToken());
        init(br, V, E);

        for (int i = 1; i <= V; i++) {
            if (Node.of(i).color !=0) continue;
            Node.of(i).color = 1;
            if (!dfs(Node.of(i))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }

    private static void init(BufferedReader br, int V, int E) throws IOException {
        StringTokenizer st;
        Node.init(V);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            Node.of(a).edges.add(Node.of(b));
            Node.of(b).edges.add(Node.of(a));
        }
    }

    static boolean dfs(Node now) {
        for (Node next : now.edges) {
            if (next.color != 0) {
                if (next.color == now.color) {
                    return false;
                }
                continue;
            }
            next.color = -1 * now.color;
            if (!dfs(next)) {
                return false;
            }
        }
        return true;
    }


    static class Node {
        static Node[] nodes;
        int id;
        int color;
        List<Node> edges = new ArrayList<>();

        static void init(int V) {
            nodes = new Node[V + 1];
        }

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public Node(int id) {
            this.id = id;
        }

    }
}
