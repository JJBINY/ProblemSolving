package 백준.DP.그냥DP.memoization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * P4 경찰차
 * https://www.acmicpc.net/problem/2618
 */
public class P4_경찰차_TopDown {

    static int[][] dp;
    static Pair[] events;
    static int n;
    static int w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        events = new Pair[w + 1];
        for (int i = 1; i <= w; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[w + 1][w + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        getReverseMinDist(0, 0);

        int a = 0;
        int b = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(dp[0][0]).append("\n");
        while(true){
            if(a==w || b==w)break;
            int next = Math.max(a, b) + 1;

            int r1 = getReverseMinDist(next,b) + getDistA(a, next);
            int r2 = getReverseMinDist(a,next) + getDistB(b, next);
            if(r1<r2){
                a=next;
                sb.append(1).append("\n");
            }else{
                b=next;
                sb.append(2).append("\n");
            }
        }

        System.out.println(sb.toString());

    }

    static int getReverseMinDist(int a, int b) {

//        System.out.println(a+", "+b);
        if (a == w || b == w) return 0;
        if(dp[a][b] < Integer.MAX_VALUE){
            return dp[a][b];
        }
        int next = Math.max(a, b) + 1; // 다음 사건 번호

        int r1 = getReverseMinDist(next, b) + getDistA(a, next);
        int r2 = getReverseMinDist(a, next) + getDistB(b, next);

        return dp[a][b] = Math.min(r1, r2);
    }


    static int getDistA(int from, int to) {
        if (from == 0) {
            return getDist(new Pair(1, 1), events[to]);
        }
        return getDist(events[from], events[to]);
    }

    static int getDistB(int from, int to) {
        if (from == 0) {
            return getDist(new Pair(n, n), events[to]);
        }
        return getDist(events[from], events[to]);
    }

    private static int getDist(Pair from, Pair to) {
        return Math.abs(from.a - to.a) + Math.abs(from.b - to.b);
    }

    static class Pair {
        final int a;
        final int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
/*
1000
10
457 934
692 104
225 832
926 527
149 652
735 828
432 504
91 451
268 27
25 942
 */