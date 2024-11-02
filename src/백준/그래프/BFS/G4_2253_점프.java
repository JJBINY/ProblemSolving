package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4_2253_점프
 * BFS
 */
public class G4_2253_점프 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int tc = 1;
//            int T = Integer.parseInt(br.readLine());
//            long s = System.currentTimeMillis();
            for (int i = 1; i <= tc; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
//            long e = System.currentTimeMillis();
//            System.out.println((e - s)/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }

        Deque<State> dq = new ArrayDeque<>();
        dq.offer(new State(1, 0, 0));
        boolean[][] visited = new boolean[N + 1][N];
        visited[1][0] = true;

        while (!dq.isEmpty()) {
            State cur = dq.poll();
            if (cur.x == N) {
                return cur.cnt;
            }

            int ncnt = cur.cnt + 1;
            for (int a = -K; a <= K; a++) {
                int nv = cur.v + a;
                int nx = cur.x + nv;
                if (nv < 1 || nx > N) continue;
                if (set.contains(nx)) continue;
//                if (!canReachable(N - nx, nv, K)) continue;
                if (visited[nx][ncnt]) continue;
                visited[nx][ncnt] = true;
                dq.offer(new State(nx, nv, ncnt));
            }
        }
        return -1;
    }

    private static boolean canReachable(double at, double v, double k) {
        double n = Math.ceil(v / k - 1);
        return n * v - k * n * (n + 1) / 2 <= at;
    }

    static class State {
        int x;
        int v;
        int cnt;

        public State(int x, int v, int cnt) {
            this.x = x;
            this.v = v;
            this.cnt = cnt;
        }
    }
}