package 프로그래머스.카카오.Lv1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lv1_실패율 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.solution();
    }

    // 스테이지 별 멈춰있는 사용자를 카운트
    // 실패율을 계산 -> 스테이지에 멈춘 수 / 스테이지 도달한 플레이어 수
    // 실패율이 높은 스테이지부터 내림차순으로 스테이지 번호 정렬
    static class Solution {

        public int[] solution(int N, int[] players) {
            Calculator calculator = new Calculator(N);

            for (int stage : players) {
                calculator.count(stage);
            }

            Stage[] stages = IntStream.range(1, N + 1)
                    .mapToObj(i -> new Stage(i, calculator.calculateFailure(i)))
                    .collect(Collectors.toList())
                    .toArray(new Stage[0]);
            Arrays.sort(stages);

            // Arrays.stream(stages).mapToDouble(s->s.failure).forEach(System.out::println);

            return Arrays.stream(stages)
                    .mapToInt(s -> s.num)
                    .toArray();

        }

        class Calculator {
            private int[] reached;
            private int[] challenging;

            public Calculator(int N) {
                this.reached = new int[N + 2];
                this.challenging = new int[N + 2];
            }

            public void count(int stage) {
                for (int i = 1; i <= stage; i++) {
                    reached[i] += 1;
                }
                challenging[stage] += 1;
            }

            public double calculateFailure(int stage) {
                if (reached[stage] == 0) {
                    return 0;
                }
                return (double) challenging[stage] / reached[stage];
            }
        }

        class Stage implements Comparable {
            int num;
            double failure;

            public Stage(int num, double failure) {
                this.num = num;
                this.failure = failure;
            }

            @Override
            public int compareTo(Object o) {
                Stage s = (Stage) o;
                int compared = -1 * Double.compare(failure, s.failure);
                if (compared == 0) {
                    return Integer.compare(num, s.num);
                }
                return compared;
            }
        }
    }
}
