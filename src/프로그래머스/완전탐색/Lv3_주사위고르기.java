package 프로그래머스.완전탐색;

import java.util.*;

public class Lv3_주사위고르기 {

    //브루트포스, meet in the middle,
    //백트래킹, 조합, 비트마스킹

    static int N, M;
    static List<Integer> combis = new ArrayList<>(); //주사위 조합
    static int[][] dice;
    static int[] aSums;
    static int[] bSums;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        N = dice.length;
        M = N / 2;

        initCombis(0, 0, 0); // 주사위 조합 생성
        int[] results = getResultsByState(); // 승리 횟수 계산
        int state = getMaxState(results); // 정답 주사위 조합 선택

        // 출력 형태에 맞게 변환
        int[] answer = new int[N / 2];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((state & 1 << i) > 0) {
                answer[cnt++] = i + 1;
            }
        }

        return answer;
    }

    // N개의 주사위 중 N/2개 뽑는 조합
    // 10_C_5 = 252
    public void initCombis(int from, int cnt, int state) {
        if (cnt == M) {
            combis.add(state);
            return;
        }

        for (int i = from; i < N; i++) {
            initCombis(i + 1, cnt, state);
            initCombis(i + 1, cnt + 1, state | (1 << i));
        }
    }

    public int[] getResultsByState() {
        int[] results = new int[2000]; // 2^10 + 2^9 +...+2^6 = 1024 + 512 + 256 + 128 + 64 < 2000

        for (int state : combis) { // N_C_M
            aSums = new int[100 * M + 1]; // 점수별 경우의 수
            bSums = new int[100 * M + 1]; // 주사위 최대 눈금이 100이므로 최대 100*M점 까지 나올 수 있다.
            calculateASums(state, 0, 0, 0); // 6^M
            calculateBSums(state, 0, 0, 0); // 6^M
            int wins = 0;

            for (int a = 1; a < 100 * M + 1; a++) {
                if (aSums[a] == 0) continue;
                for (int b = 1; b < a; b++) { //1부터 100*M까지 반복하므로 대략 10^5번 반복 주사위 조합 갯수인 252와 곱해도 10^8 미만
                    wins += aSums[a] * bSums[b];
                }
            }

            results[state] = wins;

        } //for combis

        return results;
    }

    public void calculateASums(int state, int num, int cnt, int sum) {
        if (cnt == N / 2) {
            aSums[sum]++;
            return;
        }

        if ((state & 1 << num) > 0) {
            for (int i = 0; i < 6; i++) {
                calculateASums(state, num + 1, cnt + 1, sum + dice[num][i]);
            }
        } else {
            calculateASums(state, num + 1, cnt, sum);
        }
    }

    public void calculateBSums(int state, int num, int cnt, int sum) {
        if (cnt == N / 2) {
            bSums[sum]++;
            return;
        }

        if ((state & 1 << num) == 0) {
            for (int i = 0; i < 6; i++) {
                calculateBSums(state, num + 1, cnt + 1, sum + dice[num][i]);
            }
        } else {
            calculateBSums(state, num + 1, cnt, sum);
        }
    }

    public int getMaxState(int[] results) {
        int max = Arrays.stream(results).max().getAsInt();
        int state = 0;
        for (int i = 0; i < 2000; i++) {
            if (results[i] == max) {
                state = i;
                break;
            }
        }

        return state;
    }
}