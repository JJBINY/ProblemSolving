package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * S4_1158_요세푸스_문제
 * 자료구조, 큐
 */
public class S4_1158_요세푸스_문제 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int K = Integer.parseInt(st.nextToken());
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < K-1; j++) {
                dq.offer(dq.poll());
            }
            sb.append(dq.poll()).append(", ");
        }
        sb.append(dq.poll()).append(">");

        return sb;
    }
}