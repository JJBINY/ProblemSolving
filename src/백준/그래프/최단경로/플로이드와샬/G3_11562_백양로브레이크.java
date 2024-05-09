package 백준.그래프.최단경로.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 11562 백양로 브레이크
 * 최단경로, 플로이드 와샬
 */
public class G3_11562_백양로브레이크 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken()); // 250
        int m = parseInt(st.nextToken()); // 125*249 ~= 30,000
        int[][] dists = new int[n + 1][n + 1];
        int INF = 1_000_000;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dists[i], INF);
            dists[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            boolean isOneway = parseInt(st.nextToken()) == 0 ? true : false;
            dists[u][v] = 0;
            dists[v][u] = isOneway ? 1 : 0;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if(i==k) continue;
                for (int j = 1; j <= n; j++) {
                    dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int k = parseInt(br.readLine()); // 30,000
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            sb.append(dists[s][e]).append("\n");
        }
        System.out.print(sb.toString());
    }
}