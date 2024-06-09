package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * G5_16509_장군
 * BFS, 구현
 */
public class G5_16509_장군 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int r2, c2;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int[] dr = {3, 3, -3, -3, 2, -2, 2, -2};
        int[] dc = {2, -2, 2, -2, 3, 3, -3, -3};

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{r1, c1, 0});

        boolean[][] visited = new boolean[10][9];
        visited[r1][c1] = true;
        while (!dq.isEmpty()) {
            int[] now = dq.poll();
            int r = now[0];
            int c = now[1];
            int dist = now[2];
            if (r == r2 && c == c2) {
                return dist;
            }
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= 10 || nc < 0 || nc >= 9) continue;
                if (visited[nr][nc]) continue;
                int rd = r - nr;
                int cd = c - nc;
                if (!check(rd, cd, r, c)) continue;
                visited[nr][nc] = true;
                dq.offer(new int[]{nr, nc, dist + 1});
            }
        }
        return -1;
    }

    static boolean check(int rd, int cd, int r, int c) {
        int rr = rd < 0 ? 1 : -1;
        int cc = cd < 0 ? 1 : -1;
        if (Math.abs(rd) > Math.abs(cd)) {
            r += rr;
        } else {
            c += cc;
        }
        if (r == r2 && c == c2) return false;
        r += rr;
        c += cc;
        if (r == r2 && c == c2) return false;

        return true;
    }
}