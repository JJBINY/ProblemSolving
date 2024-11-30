package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G5_14217_그래프_탐색
 * 그래프, BFS
 */
public class G5_14217_그래프_탐색 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // <=500
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node.addEdge(a, b);
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(cmd == 1){
                Node.addEdge(a, b);
            }else{
                Node.removeEdge(a, b);
            }

            // bfs
            int[] visited = new int[501];
            Arrays.fill(visited, -1);
            visited[1] = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.addLast(1);
            while (!queue.isEmpty()) {
                Node cur = Node.of(queue.pollFirst());
                for (Node next : cur.edges) {
                    if(visited[next.id] > -1) continue;
                    visited[next.id] = visited[cur.id] + 1;
                    queue.addLast(next.id);
                }
            }

            for (int i = 1; i <= N; i++) {
                sb.append(visited[i]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }
    
    static class Node{
        static Node[] nodes = new Node[501];
        int id;
        List<Node> edges = new ArrayList<>();
        
        static Node of(int id){
            if(nodes[id] == null){
                nodes[id] = new Node();
                nodes[id].id = id;
            }
            return nodes[id];
        }

        static void addEdge(int a, int b){
            Node na = of(a);
            Node nb = of(b);
            na.edges.add(nb);
            nb.edges.add(na);
        }

        static void removeEdge(int a, int b){
            Node na = of(a);
            Node nb = of(b);
            na.edges.remove(nb);
            nb.edges.remove(na);
        }
    }
}