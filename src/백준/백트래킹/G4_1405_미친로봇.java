package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G4 1405 미친 로봇
 * 완전탐색, 백트래킹, 수학
 */
public class G4_1405_미친로봇 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;
    static double[] probs = new double[4];
    static boolean[][] visited;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            probs[i] = Double.parseDouble(st.nextToken()) / 100;
        }
        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;
        System.out.println(func(0, N, N));
    }

    //동서남북
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static double func(int depth, int r, int c) {
        if(depth == N) return 1;

        double ans = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(visited[nr][nc]) continue;
            visited[nr][nc] = true;
            ans += func(depth + 1, nr, nc) * probs[i];
            visited[nr][nc] = false;
        }

        return ans;
    }
}