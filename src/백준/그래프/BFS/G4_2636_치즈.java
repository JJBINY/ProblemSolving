package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 2636 치즈
 * 구현, BFS
 */
public class G4_2636_치즈 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());
        int[][] arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        //bfs
        int prev = 0;
        int time = 0;
        while (true) {
            int removed = bfs(R, C, arr);
            if(removed == 0) break;
            prev = removed;
            time++;
        } //while

        System.out.println(time);
        System.out.println(prev);
    }

    private static int bfs(int R, int C, int[][] arr) {
        int removed = 0;
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        boolean[][] visited = new boolean[R][C];
        Queue<Integer> Q = new LinkedList<>();
        visited[0][0] = true;
        Q.offer(0);

        while (!Q.isEmpty()) {
            int r = Q.peek() / C;
            int c = Q.poll() % C;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if (arr[nr][nc] == 1) {
                    removed++;
                    arr[nr][nc] = 0;
                } else {
                    Q.offer(nr * C + nc);
                }
            }
        } // while
        return removed;
    }
}
