package 백준.DP.비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * P5 발전소
 * https://www.acmicpc.net/problem/1029
 */
public class P5_발전소 {

    //dp[state] = state 에서 시작해서 발전소 p개 이상 키는 데 드는 최소비용
    static int[] dp = new int[1 << 16];
    static int[][] costs = new int[16][16];
    static int n;
    static int p;

    static int MAX_VALUE = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String status = br.readLine();
        p = Integer.parseInt(br.readLine());
        br.close();

        int state = 0;
        int cnt = 0;
        for (int i = 0; i < status.length(); i++) {
            if(status.charAt(i) == 'Y'){
                state |= 1 << i;
                cnt++;
            }
        }
        Arrays.fill(dp, MAX_VALUE);
        int ans = getCost(state, cnt);
        System.out.println(ans == MAX_VALUE ? -1 : ans);

    }


    static int getCost(int state,int cnt) {
        if(cnt>=p) {return  0;}
        if (dp[state] <MAX_VALUE) {return dp[state];}

        for (int from = 0; from < n; from++) {
            if(!isAlreadyFixed(state,from)) continue;
            for (int to = 0; to < n; to++) {
                if (isAlreadyFixed(state, to)) continue;
                int cost = getCost(state | (1 << to), cnt + 1) + costs[from][to];
                dp[state] = Math.min(dp[state], cost);
            }
        }
        return dp[state];
    }

    private static boolean isAlreadyFixed(int state, int i) {
        return ((state >> i) & 1) == 1;
    }


}
