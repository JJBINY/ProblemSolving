package 백준.DP.그냥DP.tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * G5_15486_퇴사_2
 */
public class G5_15486_퇴사_2 {
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
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Counsel> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.endAt));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            pq.offer(new Counsel(i + 1, i + time, price));
        }

        int[] dp = new int[N + 1];
        for (int cur = 1; cur <= N; cur++) {
            dp[cur] = dp[cur - 1];
            while (!pq.isEmpty() && pq.peek().endAt == cur) {
                Counsel c = pq.poll();
                dp[cur] = Math.max(dp[cur], dp[c.startAt-1] + c.price);
            }
        }
//        System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp));
        return Arrays.stream(dp).max().getAsInt();
    }

    static class Counsel {
        int startAt;
        int endAt;
        int price;

        public Counsel(int startAt, int endAt, int price) {
            this.startAt = startAt;
            this.endAt = endAt;
            this.price = price;
        }
    }
}