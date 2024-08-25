package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * S1_7507_올림픽_게임
 * 그리디, 정렬
 */
public class S1_7507_올림픽_게임 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("Scenario #").append(i).append(":\n");
                ans.append(solve(br));
                ans.append("\n\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Game> pq = new PriorityQueue<>(Comparator
                .comparingInt((Game g) -> g.day)
                .thenComparingInt(g -> g.end));
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Game(d, s, e));
        }

        int cnt = 0;
        int time = 0;
        int day = 0;
        while (!pq.isEmpty()){
            Game now = pq.poll();
            if(now.day != day){
                day = now.day;
                time = 0;
            }
            if(now.start < time){
                continue;
            }
            time = now.end;
            cnt++;
        }
        return cnt;
    }

    static class Game{
        int day;
        int start;
        int end;

        public Game(int day, int start, int end) {
            this.day = day;
            this.start = start;
            this.end = end;
        }
    }
}