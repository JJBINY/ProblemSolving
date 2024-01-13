package 백준.그래프.트리.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * P5 행성터널
 * https://www.acmicpc.net/problem/2887
 */
public class P5_행성터널 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] points = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            nodes.add(new Node(i,points[0], points[1], points[2]));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        nodes.sort((a, b) -> a.x - b.x);
        for (int i = 0; i < n-1; i++) {
            Node a = nodes.get(i);
            Node b = nodes.get(i + 1);
            pq.add(new Edge(a.id, b.id, Math.abs(a.x - b.x)));
        }
        nodes.sort((a, b) -> a.y - b.y);
        for (int i = 0; i < n-1; i++) {
            Node a = nodes.get(i);
            Node b = nodes.get(i + 1);
            pq.add(new Edge(a.id, b.id, Math.abs(a.y - b.y)));
        }
        nodes.sort((a, b) -> a.z - b.z);
        for (int i = 0; i < n-1; i++) {
            Node a = nodes.get(i);
            Node b = nodes.get(i + 1);
            pq.add(new Edge(a.id, b.id, Math.abs(a.z - b.z)));
        }

        parent = IntStream.range(0,n).toArray();

        long ans = 0;
        int cnt = 0;
        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find(edge.a) == find(edge.b)) continue;
            union(edge.a, edge.b);
            cnt+=1;
            ans += edge.dist;
            if(cnt>=n-1) break;
        }

        System.out.println(ans);
    }
    private static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    private static void union(int a, int b){
        a= find(a);
        b= find(b);
        if(a<b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }
    static class Edge{
        int a;
        int b;
        int dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        public int getDist() {
            return dist;
        }
    }
    static class Node{
        int id;
        int x;
        int y;
        int z;

        public Node(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}
