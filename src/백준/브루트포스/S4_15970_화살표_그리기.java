package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * S4_15970_화살표_그리기
 * 브루트포스, 정렬
 */
public class S4_15970_화살표_그리기 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<Integer, PriorityQueue<Integer>> pointsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            if(!pointsMap.containsKey(color)){
                pointsMap.put(color, new PriorityQueue<>());
            }
            pointsMap.get(color).add(idx);
        }

        int ans = 0;
        for (PriorityQueue<Integer> pq : pointsMap.values()) {
            int prev = pq.poll();
            int cur = pq.poll();
            ans += cur-prev;
            while (!pq.isEmpty()){
                 int next = pq.poll();
                ans += Math.min(next - cur, cur - prev);
                prev = cur;
                cur = next;
            }
            ans += cur - prev;
        }
        return ans;
    }

    static class Point{
        int idx;
        int color;

        public Point(int idx, int color) {
            this.idx = idx;
            this.color = color;
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