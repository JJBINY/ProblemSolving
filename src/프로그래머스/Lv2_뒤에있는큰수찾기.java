package 프로그래머스;

import java.util.*;


public class Lv2_뒤에있는큰수찾기 {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        Arrays.fill(answer,-1);
        Stack<Point> stack = new Stack<>();

        stack.push(Point.of(0, numbers[0]));
        for (int i = 1; i < len; i++) {
            while(!stack.isEmpty() && stack.peek().number < numbers[i]) {
                answer[stack.pop().idx] = numbers[i];
            }
            stack.push(Point.of(i, numbers[i]));
        }

//        while (!stack.isEmpty()) {
//            answer[stack.pop().idx] = -1;
//        }

        return answer;
    }

    public static class Point{
        int idx;
        int number;

        public Point(int idx, int number) {
            this.idx = idx;
            this.number = number;
        }

        public static Point of(int idx, int number) {
            return new Point(idx, number);
        }
    }
}
