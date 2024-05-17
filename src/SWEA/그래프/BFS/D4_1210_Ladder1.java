package SWEA.그래프.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
/**
 * 1210. [S/W 문제해결 기본] 2일차 - Ladder1 D4
 * 그래프, BFS
 */
public class D4_1210_Ladder1 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
//						int T  = parseInt(br.readLine());
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

    static int[] dr = {0,0,-1}, dc = {-1,1,0};

    public static Object solve(BufferedReader br) throws Exception {
        br.readLine();
        int[][] arr = new int[100][100];

        int x = -1;
        for (int i = 0; i < 100; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            if(i == 99) {
                for(int j = 0; j<100;j++) {
                    if(arr[i][j] == 2) {
                        x = j;
                        break;
                    }
                }
            }
        }

        ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
        dq.add(new int[] {99,x});

        boolean[][] visited = new boolean[100][100];
        visited[99][x] = true;

        while(!dq.isEmpty()) {
            int r = dq.peekFirst()[0];
            int c = dq.pollFirst()[1];
            if(r == 0) {
                return c;
            }

            for (int i = 0; i <3; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0|| nc<0||nc>=100) continue;
                if(arr[nr][nc] != 1) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                dq.addLast(new int[] {nr,nc});
                break;
            }
        }
        return -1;
    }
}
