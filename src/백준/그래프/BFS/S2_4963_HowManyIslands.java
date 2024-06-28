package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * S2_4963_HowManyIslands?
 * BFS
 */
public class S2_4963_HowManyIslands {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int w = parseInt(st.nextToken());
                int h = parseInt(st.nextToken());
                if(w == 0) break;
                ans.append(solve(w, h,br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] dr = {0, 0, 1, -1, 1, 1, -1, - 1};
    static int[] dc = {1, -1, 0, 0, 1, -1, 1, - 1};
    static Object solve(int w, int h, BufferedReader br) throws IOException {
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }
        int res = 0;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(arr[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] = true;
                Deque<int[]> dq = new ArrayDeque<>();
                dq.add(new int[]{i, j});
                while (!dq.isEmpty()){
                    int r = dq.peek()[0];
                    int c = dq.poll()[1];
                    for (int k = 0; k < 8; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if(nr<0||nr>=h||nc<0||nc>=w) continue;
                        if(arr[nr][nc] == 0) continue;
                        if (visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        dq.offer(new int[]{nr, nc});
                    }
                }
                res++;
            }
        }

        return res;
    }
}