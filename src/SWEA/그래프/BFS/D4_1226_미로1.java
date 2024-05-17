package SWEA.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1226. [S/W 문제해결 기본] 7일차 - 미로1 D4
 * BFS
 */
public class D4_1226_미로1 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
//			int T  = Integer.parseInt(br.readLine());
            int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static Object solve(BufferedReader br) throws Exception {
        br.readLine();

        int N = 16;
        char[][] arr = new char[N][N];
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == '2') {
                    start[0] = i;
                    start[1] = j;
                }else if(arr[i][j] == '3') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        } // for i

        //bfs
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(start);
        boolean[][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;
        while(!dq.isEmpty()) {
            int r = dq.peek()[0];
            int c = dq.poll()[1];
            if(r == end[0] && c == end[1]) {
                return 1;
            }

            for(int i =0;i<4;i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N) continue;
                if(arr[nr][nc] == '1') continue;
                if(visited[nr][nc])continue;
                visited[nr][nc] = true;
                dq.offer(new int[] {nr,nc});
            }
        }


        return 0;
    }
}
