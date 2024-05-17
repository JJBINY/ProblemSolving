package 백준.그래프.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 12754 인하니카 공화국
 * 트리, DFS
 */
public class G3_12784_인하니카공화국 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            int T = 1;
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M;
    static int[][] edges;
    static boolean[] visited;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        edges = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());
            edges[a][b] = w;
            edges[b][a] = w;
        }

        visited = new boolean[N + 1];
        int result = dfs(1, Integer.MAX_VALUE);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    static int dfs(int now, int weight){
        visited[now] = true;

        int sum = 0;
        for (int next = 1; next <= N; next++) {
            if (visited[next]) continue;
            if(edges[now][next]>0) {
                sum += dfs(next, edges[now][next]);
            }
        }
        if(sum == 0){
            return weight;
        }
        return Math.min(sum, weight);
    }
}