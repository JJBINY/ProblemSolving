package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * G5_19940_피자_오븐
 * BFS, 그리디
 */
public class G5_19940_피자_오븐 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
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

        int[] d = {60, 10, -10, 1, -1};
        int[][] pushed = new int[61][5];
        for (int i = 0; i < 60; i++) {
            pushed[i][0] += N / 60;
        }

        boolean[] visited = new boolean[61];
        visited[0] = true;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);

        while (!dq.isEmpty()){
            int now = dq.poll();

            for (int i = 4; i >=0; i--) {
                int next = now + d[i];
                if(next<0 || next >60) continue;

                if(visited[next]) continue;
                visited[next] = true;

                pushed[next] = pushed[now].clone();
                pushed[next][i]++;

                dq.offer(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(pushed[N%60][i]).append(" ");
        }
        return sb;
    }
}