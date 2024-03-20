package 프로그래머스.완전탐색.백트래킹;

import java.util.*;

public class Lv2_양궁대회 {


    /*
    완전탐색, 백트래킹, 조합, 정렬
    */
    static int n;
    static int[] info;
    static List<int[]> results = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;

        //경우의 수 구하기
        func(10, 0, new int[11]);

        // 점수 계산
        int max = 0;
        int[] answer = {-1};

        for (int[] rst : results) {
            int lion = 0;
            int apeach = 0;
            for (int i = 0; i < 11; i++) {
                if (rst[i] > info[i]) {
                    lion += 10 - i;
                } else if (info[i] > 0) {
                    apeach += 10 - i;
                }
            }
            if (lion - apeach > max) { //낮은 경우를 많이 쏜 경우 먼저 탐색했으므로 동점일 경우 낮은 점수를 더 많이 맞힌 경우가 선택됨
                max = lion - apeach;
                answer = rst;
            }
        } //for


        return answer;
    }

    //백트래킹
    public void func(int target, int cnt, int[] result) {

        if (cnt == n) {
            results.add(result);
            return;
        }
        if (target < 0) return;

        for (int i = n - cnt; i >= 0; i--) { //낮은 점수에 많이 쏜 경우를 먼저 탐색
            int[] r = result.clone();
            r[target] = i;
            func(target - 1, cnt + i, r);
        }
    }
}