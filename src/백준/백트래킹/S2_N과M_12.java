package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/15666
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * <p>
 * N개의 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * 고른 수열은 비내림차순이어야 한다.
 * 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 * <p>
 * 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 * <p>
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * <p>
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */
public class S2_N과M_12 {
    static int m;
    static Stack<Integer> stack = new Stack<>();
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine().split(" ")[1]);
        List<Num> nums = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .map(Num::new).collect(Collectors.toList());

        // 백트래킹
        printPossibleSequence(nums, 0, 0);

    }

    static void printPossibleSequence(List<Num> nums, int idx, int depth) {
        if (depth == m) {
            String result = getResult();
            if (isNotDuplicated(result)) {
                System.out.println(result);
            }
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            Num target = nums.get(i);
            if (!stack.isEmpty() && target.value<stack.peek()) continue;
            stack.push(target.value);
            printPossibleSequence(nums, i + 1, depth + 1);
            stack.pop();
        }

    }

    private static boolean isNotDuplicated(String result) {
        return set.add(result);
    }

    private static String getResult() {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iterator = stack.elements().asIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(" ");
        }
        String result = sb.toString();
        return result;
    }

    static class Num {
        int value;

        public Num(int value) {
            this.value = value;
        }
    }
}
