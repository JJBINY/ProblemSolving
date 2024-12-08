package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * G4_23085_판치기
 * BFS
 */
public class G4_23085_판치기 {
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
        int K = Integer.parseInt(st.nextToken());

        int h = (int) Arrays.stream(br.readLine().split("")).filter(s -> s.equals("H")).count();
        int t = N - h;

        Deque<State> queue = new ArrayDeque<>();
        queue.offer(new State(h, t, 0));
        boolean[] visited = new boolean[N + 1]; // 앞면인 코인 갯수
        visited[h] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
//            System.out.println("cur = " + cur);
            if (cur.t == N) {
                return cur.cnt;
            }

            int x = Math.min(cur.h, K);
            for (int i = x; i >= 0 && cur.t >= K-i; i--) {
                //뒤집기 H -> T: i개, T->H: K-i개
                int nh = cur.h + K - 2 * i;
                if (visited[nh]) continue;
                visited[nh] = true;
                int nt = cur.t - K + 2 * i;
                queue.offer(new State(nh, nt, cur.cnt + 1));
            }
        }
        return -1;
    }

    static class State {
        int h;
        int t;
        int cnt;

        public State(int h, int t, int cnt) {
            this.h = h;
            this.t = t;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "State{" +
                    "h=" + h +
                    ", t=" + t +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}