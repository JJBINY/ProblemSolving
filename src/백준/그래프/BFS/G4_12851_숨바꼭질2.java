package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4_12851_숨바꼭질2
 * BFS
 */
public class G4_12851_숨바꼭질2 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{N, 0});
        int[] visited = new int[200_001];
        Arrays.fill(visited, -1);
        visited[N] = 0;

        int res = 0;
        int cnt = 0;
        int[] d1 = {-1, 1, 0}, d2 = {1, 1, 2};
        while (!dq.isEmpty()) {
            int now = dq.peek()[0];
            int time = dq.poll()[1];
            if (now == K) {
                if (res == 0) {
                    res = time;
                } else if (time > res) {
                    break;
                }
                cnt++;
            }

            for (int i = 0; i < 3; i++) {
                int next = now * d2[i] + d1[i];
                int nt = time + 1;
                if (next > 200_000 || next < 0) continue;
                if (visited[next] >-1){
                    if(nt != visited[next]){
                        continue;
                    }
                }
                visited[next] = nt;
                dq.add(new int[]{next, nt});
            }
        }
        return new StringBuilder().append(res).append("\n").append(cnt);
    }
}