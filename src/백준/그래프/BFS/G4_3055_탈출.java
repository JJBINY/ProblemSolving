package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * G4_3055_탈출
 * BFS
 */
public class G4_3055_탈출 {
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
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] arr = new char[R][C];
        int[] start = null;

        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        int[][] waters = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(waters[i], 1_000_000);
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == 'S') {
                    start = new int[]{i, j};
                }else if(arr[i][j] == '*'){
                    dq.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 물로부터의 거리 표시
        while (!dq.isEmpty()){
            int r = dq.peek()[0];
            int c = dq.peek()[1];
            int dist = dq.pop()[2];
            waters[r][c] = dist;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=R || nc<0 || nc>=C) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                char e = arr[nr][nc];
                if(e == 'X' || e == 'D') continue;
                dq.offer(new int[]{nr, nc, dist + 1});
            }
        }

//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(waters[i]));
//        }
//        System.out.println();

        dq.clear();
        dq.offer(new int[]{start[0], start[1], 0});
        visited = new boolean[R][C];
        visited[start[0]][start[1]] = true;

        while (!dq.isEmpty()){
            int r = dq.peek()[0];
            int c = dq.peek()[1];
            int dist = dq.pop()[2];

//            System.out.println(r+","+c);
            if(arr[r][c] == 'D'){
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr<0||nr>=R || nc<0 || nc>=C) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                char e = arr[nr][nc];
                if(e == 'X') continue;
                if(e != 'D' && waters[nr][nc]<= dist+1) continue; // 물이 더 빨리 혹은 동시에 도달하는 경우
                dq.offer(new int[]{nr, nc, dist + 1});
            }
        }

        return "KAKTUS";
    }
}

/*
3 3
D.*
...
.S.

3 3
D.*
...
..S
 */