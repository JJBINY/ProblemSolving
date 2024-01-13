package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * G3 별자리 만들기
 * https://www.acmicpc.net/problem/4386
 */
public class G3_별자리만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double r = Double.parseDouble(st.nextToken());
            double c = Double.parseDouble(st.nextToken());
            nodes.add(new Node(r, c));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getDist));
        for (int i = 0; i < n; i++) {
            Node a = nodes.get(i);
            for (int j = i+1; j < n; j++) {
                Node b = nodes.get(j);
                pq.add(new Edge(i, j, a.getDistTo(b)));
            }
        }

        int[] parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        double ans = 0;
        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find(parent,edge.a) == find(parent,edge.b)) continue;
            union(parent, edge.a, edge.b);
            ans += edge.dist;
        }
        System.out.printf("%.2f", ans);
    }

    static int find(int[] parent, int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if(a<b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    static class Node{
        double r;
        double c;

        public Node(double r, double c) {
            this.r = r;
            this.c = c;
        }

        public double getDistTo(Node other){
            double dr = (r-other.r);
            double dc = (c - other.c);

            return Math.sqrt(dr * dr + dc * dc);
        }
    }

    static class Edge{
        int a;
        int b;
        double dist;

        public double getDist() {
            return dist;
        }

        public Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
}