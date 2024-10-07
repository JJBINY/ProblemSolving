package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/**
 * S2_14248_점프점프
 * 그래프, BFS
 */
public class S2_14248_점프점프 {
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
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int s = Integer.parseInt(br.readLine()) - 1;

        boolean[] visited = new boolean[n];
        visited[s] = true;
        int ans = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(s);
        int[] d = {-1, 1};
        while (!dq.isEmpty()){
            int cur = dq.poll();
            ans++;

            for (int i = 0; i < 2; i++) {
                int next = cur + d[i] * arr[cur];
                if(next<0||next>=n) continue;
                if(visited[next]) continue;
                visited[next] = true;
                dq.offer(next);
            }

        }
        return ans;
    }
}