package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P5_2104_부분배열_고르기
 * 스위핑, 스택
 */
public class P5_2104_부분배열_고르기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    히스토그램의 가장 넓은 직사각형을 푸는 문제와 같게 생각
    - 단 구간의 합이 너비에 대응
     */
    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N+2];
        long[] pSum = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            pSum[i] = arr[i] + pSum[i - 1];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        long ans = 0;
        for (int i = 1; i <= N+1; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i] ) {
                long min = arr[stack.pop()];
                long sum = pSum[i-1] - pSum[stack.peek()];
                ans = Math.max(ans, sum * min);
            }
            stack.push(i);
        }

        return ans;
    }
}

