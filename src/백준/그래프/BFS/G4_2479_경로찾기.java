package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * G4 2479 경로찾기
 * 그래프, BFS
 */
public class G4_2479_경로찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1,parseInt(br.readLine(), 2));
        }

        st = new StringTokenizer(br.readLine());
        int A = parseInt(st.nextToken());
        int B = parseInt(st.nextToken());
        br.close();

        //간선 연결
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(Integer.bitCount(nodes[i].val ^ nodes[j].val) == 1){
                    nodes[i].edges.add(nodes[j]);
                    nodes[j].edges.add(nodes[i]);
                }
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(nodes[A - 1], new StringBuilder().append(A).append(" ")));
        boolean[] visited = new boolean[N];
        visited[A-1] = true;

        while (!queue.isEmpty()){
            Pair now = queue.poll();
            if(now.node.id == B){
                System.out.println(now.sb.toString());
                return;
            }
            for (Node node : now.node.edges) {
                if(visited[node.id-1]) continue;
                visited[node.id - 1] = true;
                Pair next = new Pair(node, new StringBuilder().append(now.sb).append(node.id).append(" "));
                queue.add(next);
            }
        }
        System.out.println(-1);
    }

    static class Node{
        int id;
        int val;
        List<Node> edges = new ArrayList<>();

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }
    }

    static class Pair{
        Node node;
        StringBuilder sb;

        public Pair(Node node, StringBuilder sb) {
            this.node = node;
            this.sb = sb;
        }
    }
}

