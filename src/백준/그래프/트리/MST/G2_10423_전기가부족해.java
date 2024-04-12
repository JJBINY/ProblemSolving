package 백준.그래프.트리.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 10423 전기가 부족해
 * MST, 크루스칼, 분리집합
 */
public class G2_10423_전기가부족해 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, M, K;
    static int[] parent;
    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int yny = parseInt(st.nextToken());
        for (int i = 1; i < K; i++) {
            union(yny, parseInt(st.nextToken()));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(ar -> ar[2]));
        for (int i = 0; i < M; i++) {
            int[] cable = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.offer(cable);
        }




        int ans = 0;
        while (!pq.isEmpty()){
            int[] cable = pq.poll();
            int a = cable[0];
            int b = cable[1];
            if(find(a) == find(b)) continue;
            union(a, b);
//            System.out.println(a+" "+b+" "+cable[2]);
            ans += cable[2];
        }

        System.out.println(ans);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }
    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }

}
