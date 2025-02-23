package 백준.그래프.트리.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;


/**
 * 백준.그래프.트리.MST.G3_13418_학교_탐방하기
 */
public class G3_13418_학교_탐방하기 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            C = -1*C +1; // 입력 값에 주의
            edges.add(Node.addEdge(A, B, C));
        }
        edges.sort(Comparator.comparingInt(e -> e.weight)); // 오름차순 정렬
        int min = calculateResult(edges);

//        System.out.println("===============");
        edges.sort(Comparator.comparingInt(e -> -e.weight)); // 내림차순 정렬
        int max = calculateResult(edges);
        return (int)(Math.pow(max,2) - Math.pow(min,2));
    }

    private static int calculateResult(List<Edge> edges) {
        int res = 0;
        DisjointSet ds = new DisjointSet();
        for (Edge e : edges) {
//            System.out.println("e = " + e);
            if(ds.find(e.from) == ds.find(e.to)) continue;
            ds.union(e.from, e.to);
            res+= e.weight;
//            System.out.println("res = " + res);
        }
        return res;
    }

    static class DisjointSet{
        int[] parents = IntStream.range(0, 1001).toArray();

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            parents[b] = a;
        }

        public int find(int a) {
            if(a == parents[a]) return a;
            return parents[a] = find(parents[a]);
        }

    }

    static class Edge{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
    }

    static class Node{
        private static Node[] nodes = new Node[1001];
        private int id;

        private List<Edge> edges = new ArrayList<>();

        public static Node of(int id){
            if(nodes[id] == null){
                Node node = new Node();
                node.id = id;
                nodes[id] = node;
            }
            return nodes[id];
        }
        public static Edge addEdge(int v, int u, int w){
            Edge e = new Edge(v, u, w);
            of(v).edges.add(e);
            return e;
        }

    }

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
}