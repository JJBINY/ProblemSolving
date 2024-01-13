package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * P5 히스토그램에서 가장 큰 직사각형
 * https://www.acmicpc.net/problem/6549
 */
public class P5_히스토그램에서가장큰직사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            if (n == 0) {
                System.out.println(sb.toString());
                br.close();
                return;
            }
            Stack<Rectangle> stack = new Stack<>();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                long now = Long.parseLong(st.nextToken());

                int idx = i;
                while (!stack.isEmpty() && now < stack.peek().height) {
                    idx = stack.peek().idx;
                    long width = i - idx;
                    long height = stack.pop().height;
                    ans = Math.max(ans, width * height);

                }

                stack.push(new Rectangle(idx, now));
            }
            while (!stack.isEmpty()) {
                long width = n - stack.peek().idx;
                ans = Math.max(ans, stack.pop().height * width);
            }
            sb.append(ans);
            sb.append("\n");
        }
    }

    static class Rectangle {
        int idx;
        long height;

        public Rectangle(int idx, long height) {
            this.idx = idx;
            this.height = height;
        }
    }
}