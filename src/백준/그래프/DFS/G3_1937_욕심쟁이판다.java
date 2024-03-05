package 백준.그래프.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 1937 욕심쟁이 판다
 * 그래프, DFS, DP
 */
public class G3_1937_욕심쟁이판다 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N;
    static int[][] dp, bamboos;
    static void solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        bamboos = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                bamboos[i][j] = parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        System.out.println(ans+1);
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    static int dfs(int r, int c) {
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        if(dp[r][c]>=0){
            return dp[r][c];
        }

        dp[r][c] = 0; // visited
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0||nr>=N || nc<0||nc>=N) continue;

            if(bamboos[nr][nc] > bamboos[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
        return dp[r][c];
    }
}