package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * S2_21736_헌내기는_친구가_필요해
 * BFS
 */
public class S2_21736_헌내기는_친구가_필요해 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N][];
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 'I'){
                    dq.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (arr[i][j] == 'X') {
                    visited[i][j] = true; // 벽은 미리 방문 처리
                }
            }
        }


        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};
        int nMeet = 0;
        while (!dq.isEmpty()){
            int r = dq.peek()[0];
            int c = dq.poll()[1];

            if(arr[r][c] == 'P'){
                nMeet++;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nr>=N || nc<0 || nc>= M) continue; // outOfBound
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                dq.offer(new int[]{nr, nc});
            }
        }
        return nMeet == 0 ? "TT" : nMeet;
    }
}