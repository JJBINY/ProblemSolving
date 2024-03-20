package 프로그래머스.자료구조.스택;

import java.util.Stack;

public class Lv2_택배상자 {
    public int solution(int[] order) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < order.length; i++) {
            stack.push(i + 1);
            while (!stack.isEmpty() && order[ans] == stack.peek()) {
                ans += 1;
                stack.pop();

            }
        }
        return ans;
    }
}
/*
5 4 3 2 1
=========





*/
