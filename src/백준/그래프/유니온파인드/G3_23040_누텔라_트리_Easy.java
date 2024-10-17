package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;


/**
 * G3_23040_누텔라_트리_Easy
 * 트리, 분리집합
 */
public class G3_23040_누텔라_트리_Easy {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] parents;
    static int[] counts;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b ) return;

        parents[b] = a;
        counts[a] += counts[b];
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Node.init(N);
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node node1 = Node.of(Integer.parseInt(st.nextToken()));
            Node node2 = Node.of(Integer.parseInt(st.nextToken()));
            node1.edges.add(node2);
            node2.edges.add(node1);
        }

        String color = br.readLine();
        List<Node> reds = new ArrayList<>();
        List<Node> blacks = new ArrayList<>();

        parents = IntStream.range(0, N + 1).toArray();
        counts = new int[N + 1];
        Arrays.fill(counts, 1);

        for (int i = 0; i < color.length(); i++) {
            Node node = Node.of(i + 1);
            if (color.charAt(i) == 'R') {
                reds.add(node);
            } else {
                node.isBlack = true;
                blacks.add(node);
            }
        }

        for (Node red : reds) {
            for (Node next : red.edges) {
                if (next.isBlack) continue;
                union(red.id, next.id);
            }
        }

        long ans = 0;
        for (Node black : blacks) {
            for (Node next : black.edges) {
                if (next.isBlack) continue;
                ans += counts[find(next.id)];
            }
        }

//        System.out.println("Arrays.toString(counts) = " + Arrays.toString(counts));
        return ans;
    }

    static class Node {
        static Node[] nodes;
        int id;
        boolean isBlack;
        List<Node> edges = new ArrayList<>();

        static void init(int N) {
            nodes = new Node[N + 1];
        }

        static Node of(int id) {
            if (nodes[id] == null) {
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }
    }
}