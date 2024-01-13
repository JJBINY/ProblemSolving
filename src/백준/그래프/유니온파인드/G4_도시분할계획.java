package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 도시 분할 계획
 * https://www.acmicpc.net/problem/1647
 * 최소신장트리 응용
 */
public class G4_도시분할계획 {

    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Edge(
                    parseInt(st.nextToken()),
                    parseInt(st.nextToken()),
                    parseInt(st.nextToken())));
        }

        arr = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            arr[i] = i;
        }
        int max = 0;
        int sum = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(find(edge.a) == find(edge.b)) continue;
            union(edge.a, edge.b);
            sum += edge.weight;
            max = Math.max(max, edge.weight);
        }
        System.out.println(sum-max);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            arr[b] = a;
        }else{
            arr[a] = b;
        }
    }
    static int find(int a){
        if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }

    static class Edge{
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