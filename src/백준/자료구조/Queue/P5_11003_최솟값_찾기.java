package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * P5_11003_최솟값_찾기
 * 자료구조, 덱, 모노톤 덱, 우선순위 큐
 */
public class P5_11003_최솟값_찾기 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Number> dq = new ArrayDeque<>();

        StringBuilder res = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Math.max(i - L + 1, 0);
            int value = Integer.parseInt(st.nextToken());

            Number now = new Number(value, i);


            // 현재 수보다 큰 수는 남길 필요가 없음
            while (!dq.isEmpty() && now.value < dq.peekLast().value){
                dq.pollLast();
            }
            // 덱에는 현재 수보다 작은 수들만 남아 있음
            dq.offerLast(now);

            // 범위 벗어나는 수는 제거
            if( idx > dq.peekFirst().idx){
                dq.pollFirst();
            }

            res.append(dq.peekFirst().value).append(" ");
        }
        return res;
    }

    static class Number{
        int value;
        int idx;

        public Number(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "value=" + value +
                    ", idx=" + idx +
                    '}';
        }
    }
}