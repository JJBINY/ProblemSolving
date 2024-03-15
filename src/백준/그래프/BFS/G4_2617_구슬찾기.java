package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4 2617 구슬찾기 30m
 * BFS
 */
public class G4_2617_구슬찾기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            Node.of(b).edges.add(Node.of(a));
        }

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;

            Queue<Node> queue = new LinkedList<>();
            queue.add(Node.of(i));

            int heavy = 0;
            while (!queue.isEmpty()){
                Node now = queue.poll();
                for (Node next : now.edges) {
                    if(visited[next.id]) continue;
                    visited[next.id] = true;

                    heavy++;
                    next.light++;
                    queue.add(next);
                }
            } //while

            Node.of(i).heavy = heavy;
        } //for

        int ans = 0;
        int mid = N / 2 + 1;
        for (int i = 1; i <= N; i++) {
            Node node = Node.of(i);
            if(node.light>= mid || node.heavy>=mid) ans++;
        }

        System.out.println(ans);
    }
    
    static class Node{
        static Node[] nodes = new Node[100];
        List<Node> edges = new ArrayList<>();
        int id;
        int heavy;
        int light;
        
        public static Node of(int idx){
            if(nodes[idx] == null){
                nodes[idx] = new Node();
                nodes[idx].id = idx;
            }
            return nodes[idx];
        }
    }
}