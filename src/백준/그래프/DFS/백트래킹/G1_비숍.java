package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1 비숍
 * https://www.acmicpc.net/problem/1799
 */
public class G1_비숍 {

    static int n;
    static int[][] arr;
    static int tmp = 0;

    /*
    체스판의 검은색과 흰색이 다르면 비숍 경로 겹치지 않음
    검은색 기준 최대 + 흰색 기준 최대 구하기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tmp=0;
        countBishop(0, 0);
        int a = tmp;
//        System.out.println("a = " + a);

        tmp =0;
        countBishop(1, 0);
        int b = tmp;
//        System.out.println("b = " + b);

        System.out.println(a+ b);
    }

    private static void countBishop(int idx, int cnt) {
        if (idx >= n * n) {
            tmp = Math.max(tmp, cnt);
            return;
        }
        int r = idx / n;
        int c = idx % n;

        /*
         n이 짝수인 경우 2 더하면 줄 넘어가면서 색깔 바뀌어 버림
         흰 검
         검 흰
         */
        if(n%2 == 0 && c+1 == n){
            idx-=1;
        }
        else if(n%2 == 0&& c+2 == n){
            idx+=1;
        }
//        System.out.println(r+","+c);
        if (arr[r][c] == 1 && isSafe(r,c)) {
//            System.out.println(" cnt = "+cnt);
            arr[r][c] = 2;
            countBishop(idx + 2, cnt + 1);
            arr[r][c] = 1;
        }
        countBishop(idx + 2, cnt);
    }

    static int[] dr = new int[]{1, 1, -1, - 1};
    static int[] dc = new int[]{1, -1, 1, -1};
    private static boolean isSafe(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r;
            int nc = c;
            while (nr>=0 && nr<n &&nc>=0&&nc<n){
                if(arr[nr][nc] == 2) return false;
                nr += dr[i];
                nc += dc[i];
            }
        }
        return true;
    }
}