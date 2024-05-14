package SWEA.그래프.최단경로.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;


/**
 * D4 1249 보급로
 * 최단경로, 다익스트라
 */
public class D4_1249_보급로 {

    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[][] weights = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                weights[i][j] = parseInt(split[j]);
            }
        }

        int[][] distances = new int[N][N];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        distances[0][0] = 0;

        int[] dr = {0, 0, -1, 1};
        int[] dc = {1, -1, 0, 0};

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()){
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int dist = now[2];
            if (dist > distances[r][c]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=N || nc<0 || nc>= N) continue;

                int nd = dist + weights[nr][nc];
                if(nd < distances[nr][nc]){
                    distances[nr][nc] = nd;
                    pq.offer(new int[]{nr, nc, nd});
                }
            }
        }

        return distances[N-1][N-1];
    }
}