package SWEA.순열조합;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 2819. 격자판의 숫자 이어 붙이기 D4
 * DFS, 조합, 집합(Set)
 */
public class D4_2819_격자판의숫자이어붙이기 {

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

    static int[] dr = {0,0,-1,1}, dc = {-1,1,0,0};
    static int[][] arr;
    static Set<String> results;
    public static Object solve(BufferedReader br) throws Exception {
        arr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        results= new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dfs(i,j,0,new StringBuilder().append(arr[i][j]));
            }
        }
        return results.size();
    }

    static void dfs(int r, int c, int depth, StringBuilder sb) {
        if(depth == 6) {
            results.add(sb.toString());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || nr>=4 || nc<0 || nc>=4) continue;
            dfs(nr,nc,depth+1,new StringBuilder(sb).append(arr[nr][nc]));
        }
    }


}
