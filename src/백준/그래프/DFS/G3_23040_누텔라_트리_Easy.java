package 백준.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G3_23040_누텔라_트리_Easy
 * 트리, DFS
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

    /*
    dfs로 빨간색 노드끼리 연결된 갯수를 구한다.
    한번에 탐색으로 도달 가능한 빨간색 노드들에 그 갯수를 지정한다.
    검은색 노드를 순회하며 연결된 첫 빨간색 노드에 기록된 갯수만큼 정답에 더한다.
     */
    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Node.init(N);
        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node node1 = Node.of(Integer.parseInt(st.nextToken()));
            Node node2 = Node.of(Integer.parseInt(st.nextToken()));
            node1.edges.add(node2);
            node2.edges.add(node1);
        }
        String color = br.readLine();
        List<Node> reds = new ArrayList<>();
        List<Node> blacks = new ArrayList<>();
        for (int i = 0; i < color.length(); i++) {
            Node node = Node.of(i + 1);
            if(color.charAt(i) == 'R') {
                reds.add(node);
            }else{
                node.isBlack = true;
                blacks.add(node);
            }
        }

        for (Node red : reds) {
            if(red.visited) continue;
            cnt = 0;
            visitNodes = new ArrayList<>();
            dfs(red);
            for (Node node : visitNodes) {
                node.cnt = cnt;
            }
        }

        long ans = 0;
        for (Node black : blacks){
            for (Node next : black.edges) {
                if(next.isBlack) continue;
                ans += next.cnt;
            }
        }

        return ans;
    }

    static int cnt;
    static List<Node> visitNodes;
    static void dfs(Node node){
        node.visited = true;
        cnt++;
        visitNodes.add(node);
        for (Node next : node.edges) {
            if(next.visited || next.isBlack) continue;
            dfs(next);
        }
    }


    static class Node{
        static Node[] nodes;
        int id;
        int cnt;
        boolean isBlack;
        boolean visited;
        List<Node> edges = new ArrayList<>();

        static void init(int N){
            nodes = new Node[N + 1];
        }
        static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", cnt=" + cnt +
                    ", isBlack=" + isBlack +
                    ", visited=" + visited +
                    '}';
        }
    }
}