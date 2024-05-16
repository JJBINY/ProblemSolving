package SWEA.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;
/**
 * 2806. N-Queen D3
 * DFS,백트래킹
 */
public class D3_2806_NQueen {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = parseInt(br.readLine());
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

    static int N, cnt;
    static boolean[] row, col, x1, x2;
    public static Object solve(BufferedReader br) throws Exception {
        N = parseInt(br.readLine());
        row = new boolean[N];
        col= new boolean[N];
        x1 = new boolean[N*2];
        x2 = new boolean[N*2];
        cnt = 0;
        dfs(0,0);
        return cnt;
    }

    static void dfs(int r, int depth) {
        if(depth == N) {
            cnt++;
            return;
        }else if(r >=N) {
            return;
        }

        for(int j = 0; j<N;j++) {
            if(!possible(r,j)) continue;
            set(r,j,true);
            dfs(r+1, depth+1);
            set(r,j,false);

        }
    }

    static boolean possible(int r, int c) {
        if(row[r]) return false;
        if(col[c]) return false;
        if(x1[r-c+N]) return false;
        if(x2[r+c]) return false;

        return true;
    }

    static void set(int r, int c, boolean x) {
        row[r] = x;
        col[c] =x;
        x1[r-c+N] = x;
        x2[r+c] = x;
    }

}
