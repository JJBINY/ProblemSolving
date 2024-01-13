package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * P4 불끄기
 * https://www.acmicpc.net/problem/14939
 */
public class P4_불끄기 {


    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] lamps = new int[10];
        for (int i = 0; i < 10; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                if(s.charAt(j) == 'O'){
                    lamps[i] |= 1 << j;
                }
            }
        }
        combination(lamps, 0,0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void combination(int[] arr, int idx, int cnt){
        if(idx == 10) return;
        ans = Math.min(ans, simulate(arr.clone(),cnt));
        combination(arr.clone(), idx + 1,cnt);

        toggle(arr, 0, idx);
        cnt += 1;
        ans = Math.min(ans, simulate(arr.clone(),cnt));
        combination(arr, idx + 1,cnt);
    }

    private static int simulate(int[] lamps, int cnt) {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(((lamps[i-1] >> j)&1) == 1){
                    toggle(lamps, i, j);
                    cnt+=1;
                }
            }
        }

        if (Arrays.stream(lamps).sum() == 0) {
            return cnt;
        }
        return Integer.MAX_VALUE;
    }

    static int[] dr = new int[]{0, 1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 1, -1};
    static void toggle(int[] arr, int r, int c) {

        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || nr>=10 || nc<0 || nc>=10) continue;

            arr[nr] ^= (1 << nc);
        }
    }

}
