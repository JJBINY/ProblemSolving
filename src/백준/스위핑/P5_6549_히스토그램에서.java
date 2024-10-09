package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * P5_6549_히스토그램에서 가장 큰 직사각형
 * 스위핑, 스택
 */
public class P5_6549_히스토그램에서 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            String input;
            while (!(input = br.readLine()).equals("0")){
                ans.append(solve(input));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(String input) throws IOException {
        StringTokenizer st = new StringTokenizer(input);
        int N = Integer.parseInt(st.nextToken());
        long[] arr = new long[N+2];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Deque<Rectangle> stack = new ArrayDeque<>();
        stack.push(new Rectangle(-1,-1));
        arr[N+1] = -1;
        long ans = 0;
        for (int i = 1; i <= N+1; i++) {
            int idx = i;
            while (!stack.isEmpty() && arr[i] < stack.peek().height) {
                idx = stack.peek().idx;
                long width = i - idx;
                long height = stack.pop().height;
                ans = Math.max(ans, width * height);
            }
            stack.push(new Rectangle(idx, arr[i]));
        }
        return ans;
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

