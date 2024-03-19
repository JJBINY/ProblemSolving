package 프로그래머스.구현;

import java.util.*;

public class Lv1_가장많이받은선물 {
    //구현
    public int solution(String[] friends, String[] gifts) {

        Map<String, Integer> idxMap = new HashMap<>();
        int N = friends.length;

        for (int i = 0; i < N; i++) {
            idxMap.put(friends[i], i);
        }

        int[][] arr = new int[N][N];
        int[] points = new int[N];
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g);
            int from = idxMap.get(st.nextToken());
            int to = idxMap.get(st.nextToken());
            arr[from][to] += 1;
            points[from]++;
            points[to]--;
        }

        int[] answers = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                if (arr[i][j] > arr[j][i]) {
                    answers[i]++;
                    continue;
                } else if (arr[i][j] < arr[j][i]) {
                    answers[j]++;
                    continue;
                }

                // 주고받은 선물 개수 같은 경우 선물지수 확인
                if (points[i] > points[j]) {
                    answers[i]++;
                } else if (points[i] < points[j]) {
                    answers[j]++;
                }
            } // for j
        } // for i

        return Arrays.stream(answers).max().getAsInt();
    }
}