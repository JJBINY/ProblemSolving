package 프로그래머스.완전탐색;

import java.util.*;

public class Lv3_외벽점검 {
    //완전탐색, 백트래킹
    static int ans = Integer.MAX_VALUE;
    static int n, complete;
    static int[] weak, dist;
    static boolean[] used;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        Arrays.sort(dist);
        used = new boolean[dist.length];
        for (int i = 0; i < weak.length; i++) {
            inspect(0, 0);
            rotate();
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public void rotate() {
        int temp = weak[0];
        for (int j = 0; j < weak.length - 1; j++) {
            weak[j] = weak[j + 1];
        }
        weak[weak.length - 1] = n + temp;
    }

    public void inspect(int idx, int friend) {

        if (friend >= ans) return;
        if (friend > dist.length) return;
        if (idx == weak.length) {
            ans = Math.min(ans, friend);
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (used[i]) continue;
            int cnt = 1;
            int covered = weak[idx] + dist[i];
            for (int j = idx + 1; j < weak.length; j++) {
                if (weak[j] <= covered) {
                    cnt++;
                } else {
                    break;
                }
            }

            used[i] = true;
            inspect(idx + cnt, friend + 1);
            used[i] = false;
        }
    }
}