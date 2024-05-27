package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2_5214_환승
 * BFS
 */
public class G2_5214_환승 {
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
        int K = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Node ht = new Node(0);
            for (int j = 0; j < K; j++) {
                ht.addEdge(Node.of(parseInt(st.nextToken())));
            }
        }

        ArrayDeque<Pair> dq = new ArrayDeque<>();
        dq.push(new Pair(Node.of(1), 1));
        Node.of(1).visited = true;
        while (!dq.isEmpty()){
            Node now = dq.peek().node;
            int cnt = dq.poll().cnt;
            if(now.id == N){
                return cnt;
            }
            for (Node next : now.edges) {
                if(next.visited) continue;
                next.visited = true;
                if(next.id == 0){
                    dq.offer(new Pair(next, cnt));
                }else{
                    dq.offer(new Pair(next, cnt + 1));
                }
            }
        }
        return -1;
    }
    static class Pair{
        Node node;
        int cnt;

        public Pair(Node node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
    static class Node{
        static Node[] nodes = new Node[100_001];
        int id;
        List<Node> edges = new ArrayList<>();
        boolean visited;

        public Node(int id) {
            this.id = id;
        }

        static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node(id);
            }
            return nodes[id];
        }

        public void addEdge(Node other){
            this.edges.add(other);
            other.edges.add(this);
        }
    }
}