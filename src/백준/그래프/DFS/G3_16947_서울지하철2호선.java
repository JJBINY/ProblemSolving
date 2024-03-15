package 백준.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3 16947 서울 지하철 2호선 40m
 * DFS, BFS
 */
public class G3_16947_서울지하철2호선 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int seq;
    static boolean[] isCycle, contained, visited;
    static Stack<Node> stack = new Stack<>();
    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        Node.nodes = new Node[N + 1];
        isCycle = new boolean[N + 1];
        contained = new boolean[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            Node A = Node.of(a);
            Node B = Node.of(b);
            A.edges.add(B);
            B.edges.add(A);
        }

        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue;
            dfs(Node.of(i),null);
        } // 사이클 판단

        int[] ans = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if(isCycle[i]){ //거리 계산
                Queue<Pair> queue = new LinkedList<>();
                visited = new boolean[N + 1];
                queue.offer(new Pair(Node.of(i), 0));
                while (!queue.isEmpty()){
                    Node now = queue.peek().node;
                    int dist = queue.poll().dist;
                    ans[now.id] = dist;

                    for(Node next:now.edges){
                        if(visited[next.id]) continue;
                        visited[next.id] = true;
                        if(isCycle[next.id]){
                            queue.offer(new Pair(next, 0));
                        }else{
                            queue.offer(new Pair(next, dist+1));
                        }
                    }
                }
                break;
            } //bfs
                }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void dfs(Node now,Node prev){
        if(contained[now.id]){
            while (!stack.peek().equals(now)){
                contained[stack.peek().id] = false;
                isCycle[stack.pop().id] = true;
            }
            isCycle[stack.pop().id] = true;
            contained[now.id] = false;

            return;
        }else if(visited[now.id]) return;
        stack.push(now);
        contained[now.id] = true;
        visited[now.id] = true;

        for (Node next : now.edges) {
            if(next.equals(prev)) continue;
            dfs(next, now);
        }

        if(contained[now.id]){
            contained[now.id] = false;
            stack.pop();
        }
    }

    static class Pair{
        Node node;
        int dist;

        public Pair(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static class Node{
        static Node[] nodes;
        List<Node> edges = new ArrayList<>();
        int id;
        int dist;

        public static Node of(int idx){
            if(nodes[idx] == null){
                nodes[idx] = new Node();
                nodes[idx].id = idx;
            }
            return nodes[idx];
        }
    }
}