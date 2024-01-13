package 백준.DP.다중차원DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * G3 비즈 공예
 * https://www.acmicpc.net/problem/1301
 */
public class G3_비즈공예 {
    static long[][][][][][][] dp = new long[11][11][11][11][11][6][6];
    static boolean[][][][][][][] visited = new boolean[11][11][11][11][11][6][6];
    static int[] beads = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i < n + 1; i++) {
            beads[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(getCntFrom(beads, 0, 0));
    }

    static long getCntFrom(int[] v, int prev, int curr){

        if(visited[v[1]][v[2]][v[3]][v[4]][v[5]][prev][curr]){
            return dp[v[1]][v[2]][v[3]][v[4]][v[5]][prev][curr];
        }
        visited[v[1]][v[2]][v[3]][v[4]][v[5]][prev][curr] = true;
        if(Arrays.stream(v).sum() == 0){
            return dp[v[1]][v[2]][v[3]][v[4]][v[5]][prev][curr] = 1;
        }

        long result =0;
        for (int i = 1; i < 6; i++) {
            if(prev == i || curr == i) continue;
            if(v[i]>0){
                v[i] -= 1;
                result += getCntFrom(v, curr, i);
                v[i] += 1;
            }
        }

        return dp[v[1]][v[2]][v[3]][v[4]][v[5]][prev][curr] = result;
    }


}
