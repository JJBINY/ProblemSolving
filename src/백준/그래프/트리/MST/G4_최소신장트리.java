package 백준.그래프.트리.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Minimum Spanning Tree
 */
public class G4_최소신장트리 {
    private static Map<Integer, Integer> parent = new HashMap<>();
    private static int find(int a){
        if(parent.getOrDefault(a,a) == a){
            return a;
        }
        int root = find(parent.get(a));
        parent.put(a, root);
        return root;
    }
    private static void union(int a, int b){
        a= find(a);
        b= find(b);
        if(a<b){
            parent.put(b, a);
        }else{
            parent.put(a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, weight));
        }

        edges.sort(Comparator.comparing(Edge::getWeight));

        int ans = 0;
        for (Edge edge : edges) {
            if(find(edge.a) != find(edge.b)){
                union(edge.a,edge.b);
                ans += edge.weight;
            }
        }

        System.out.println(ans);

    }

    private static class Edge{
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }



}