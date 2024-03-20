package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S2 10971 외판원 순회 2
 * 백트래킹
 */
public class S2_10971_외판원순회2 {

    static int N;
    static int[][] edges = new int[10][10];
    static boolean[] visited = new boolean[10];
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                edges[i][j] = parseInt(st.nextToken());
            }
        }
        br.close();

        backtrack(0, 0, 0);
        System.out.println(ans);
    }

    static void backtrack(int now, int depth, int cost) {
        if(depth == N-1){
            if(edges[now][0]>0) {
                ans = Math.min(ans, cost + edges[now][0]);
            }
            return;
        }
        for (int next = 1; next < N; next++) {
            if (visited[next] || edges[now][next] == 0) {
                continue;
            }
            visited[next] = true;
            backtrack(next, depth + 1, cost + edges[now][next]);
            visited[next] = false;
        }
    }

}

