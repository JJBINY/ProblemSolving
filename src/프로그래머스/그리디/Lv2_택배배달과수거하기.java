package 프로그래머스.그리디;

import java.util.*;

public class Lv2_택배배달과수거하기 {

    /**
     * 그리디, 스택
     * 제일 먼곳부터 방문
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> D = new Stack<>();
        Stack<Integer> P = new Stack<>();
        for (int d : deliveries) {
            D.push(d);
        }
        for (int p : pickups) {
            P.push(p);
        }
        init(D);
        init(P);

        while (!D.isEmpty() || !P.isEmpty()) {
            answer += Math.max(D.size(), P.size()) * 2;
            func(cap, D);
            func(cap, P);

        } //while

        return answer;
    }

    public void init(Stack<Integer> stack) {
        while (!stack.isEmpty() && stack.peek() == 0) {
            stack.pop();
        }
    }

    public void func(int cap, Stack<Integer> stack) {
        while (!stack.isEmpty() && stack.peek() <= cap) {
            cap -= stack.pop();
        }
        if (!stack.isEmpty()) {
            stack.push(Math.max(stack.pop() - cap, 0));
        }
    }
}