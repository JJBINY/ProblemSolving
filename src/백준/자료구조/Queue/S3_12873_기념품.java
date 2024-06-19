package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * S3_12873_기념품
 * 시뮬레이션, 큐
 */
public class S3_12873_기념품 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> dq = IntStream.range(1, N+1)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));

        for (int t = 1; t < N; t++) {
            int tg = (int) (Math.pow(t, 3) % (dq.size()));
            if(tg == 0){
                dq.pollLast();
            }else {
                for (int i = 1; i < tg; i++) {
                    dq.offer(dq.pop());
                }
                dq.poll();
            }
        }
        return dq.peek();
    }
}