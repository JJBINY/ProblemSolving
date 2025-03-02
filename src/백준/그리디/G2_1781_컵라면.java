package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G2_1781_컵라면
 * 그리디, 우선순위 큐
 */
public class G2_1781_컵라면 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> deadlineQueue = new PriorityQueue<>(Comparator
                .comparingInt(p -> -p.deadline));

        int maxDay = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            deadlineQueue.add(new Problem(d, r));
            maxDay = Math.max(maxDay, d);
        }

        PriorityQueue<Problem> rewardMaxQueue = new PriorityQueue<>(Comparator
                .comparingInt(p -> -p.reward));

        int day = maxDay;
        int res = 0;
        while (day > 0) {
            while (!deadlineQueue.isEmpty() &&
            deadlineQueue.peek().deadline >= day) {
                rewardMaxQueue.add(deadlineQueue.poll());
            }
            if (!rewardMaxQueue.isEmpty()) {
                Problem p = rewardMaxQueue.poll();
                res += p.reward;
            }
            day--;
        }
        return res;
    }

    static class Problem {
        int deadline;
        int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }
    }

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
}

/*
7
3 5
3 5
3 5
3 5
3 5
3 5
3 5
-> 15

4
1 50
2 30
3 60
3 70
-> 180
 */