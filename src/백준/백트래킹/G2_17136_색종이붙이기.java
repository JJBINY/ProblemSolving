package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G2_17136_색종이붙이기
 * 완전탐색, 백트래킹
 */
public class G2_17136_색종이붙이기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    n*n 5개씩 (n= 1,2,3,4,5)
     */

    static int[][] arr = new int[10][10];
    static int ans = Integer.MAX_VALUE;

    static void solve(BufferedReader br) throws IOException {
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        simulate(0, 0, new int[]{0, 5, 5, 5, 5, 5});

        System.out.println(ans == Integer.MAX_VALUE?-1:ans);
    }

    static void simulate(int idx, int cnt, int[] paper) {
        if (ans < cnt) {
            return;
        }
        if (idx == 100) {
            ans = Math.min(ans, cnt);
            return;
        }

        int r = idx / 10;
        int c = idx % 10;
        if (arr[r][c] == 0) {
            simulate(idx + 1, cnt, paper);
            return;
        }

        int m = findMaxMatchSize(r, c);

        for (int i = m; i > 0; i--) {
            if (paper[i] == 0) continue;
            paper[i]--;
            setArr(i, r, c, 0);
            simulate(idx + i, cnt + 1, paper);
            paper[i]++;
            setArr(i, r, c, 1);
        }
    }

    private static int findMaxMatchSize(int r, int c) {
        int m = 1;
        for (int i = 1; i <5 ; i++) {

            if(r +i >=10 || c +i>=10) break;

            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if(arr[r +i][c +j] == 0){
                    flag = false;
                    break;
                }
            }
            for (int j = 0; j <= i; j++) {
                if(arr[r +j][c +i] == 0){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                break;
            }
            m++;
        } //for i
        return m;
    }

    private static void setArr(int i, int r, int c, int x) {
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < i; k++) {
                arr[r + j][c + k] = x;
            }
        }
    }
}