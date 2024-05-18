package SWEA.그래프.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 1868. 파핑파핑 지뢰찾기 D4
 * DFS
 */
public class D4_1868_파핑파핑지뢰찾기 {

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

    static int N, result;
    static int[] dr = {-1,1,0,0,1,1,-1,-1};
    static int[] dc = {0,0,-1,1,1,-1,1,-1};
    static char[][] arr;
    public static Object solve(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for(int i = 0;i<N;i++) {
            arr[i] = br.readLine().toCharArray();
        }

        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == '.' && check(i,j)) {
                    result++;
                    click(i,j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == '.') {
                    result++;
                }
            }
        }


        return result;
    }
    static void click(int r, int c) {
        arr[r][c] = 'C';
        for(int i = 0; i<8;i++) {
            int nr = r+dr[i];
            int nc= c+dc[i];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(arr[nr][nc] == '*') continue;
            if(arr[nr][nc] =='C') continue;
            arr[nr][nc] = 'C';
            if(check(nr,nc))
                click(nr,nc);
        }
    }

    static boolean check(int r, int c) {
        for(int i = 0; i<8;i++) {
            int nr = r+dr[i];
            int nc= c+dc[i];
            if(nr<0||nr>=N||nc<0||nc>=N) continue;
            if(arr[nr][nc] == '*')
                return false;
        }
        return true;
    }
}
