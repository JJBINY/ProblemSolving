package SWEA.그래프.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 1861. 정사각형 방 D4
 * DFS, map
 */
public class D4_1861_정사각형방 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = Integer.parseInt(br.readLine());
            //			int T  = 10;
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

    static int N;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] arr, dp;

    public static Object solve(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0;i<N;i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        // 매핑 -> 오름차순으로 dfs
        Map<Integer,int[]> map = new HashMap<>();
        for(int i = 0; i<N;i++) {
            for(int j = 0; j<N;j++) {
                map.put(arr[i][j], new int[]{i,j});
            }
        }

        dp = new int[N][N];

        for (int i = 1; i <= N*N; i++) {
            int r = map.get(i)[0];
            int c = map.get(i)[1];

            if(dp[r][c] == 0) {
                dp[r][c] = dfs(r,c);
            }
        }

        int[] result = new int[2];
        for (int i = 1; i <= N*N; i++) {
            int r = map.get(i)[0];
            int c = map.get(i)[1];
            if(dp[r][c] > result[1]) {
                result[0] = i;
                result[1] = dp[r][c];
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append(" ").append(result[1]);
        return sb;
    }
    static int dfs(int r, int c) {
        dp[r][c] = 1;
        for(int i = 0; i<4;i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(arr[nr][nc] - arr[r][c] != 1) continue;
            if(dp[nr][nc] > 0) continue;
            dp[r][c] += dfs(nr,nc);
        }
        return dp[r][c];
    }
}
