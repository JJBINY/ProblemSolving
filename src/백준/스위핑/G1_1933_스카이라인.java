package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G1_1933_스카이라인
 * 스위핑, 자료구조, 우선순위 큐, Set
 */
public class G1_1933_스카이라인 {
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

        PriorityQueue<Point> points = new PriorityQueue<>(Comparator
                .comparingInt((Point p)->p.idx)
                .thenComparingInt(p->p.flag)
                .thenComparingInt(p->-p.height));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            Point start = new Point(L, H, -1);
            Point end = new Point(R, H, 1);
            start.end = end;
            points.offer(start);
            points.offer(end);
        }

        StringBuilder result = new StringBuilder();

        PriorityQueue<Point> endPoints = new PriorityQueue<>(Comparator
                .comparingInt((Point p) -> -p.height));

        Set<Integer> visited = new HashSet<>();
        int skyline = 0;
        while (!points.isEmpty()){
            Point now = points.poll();
            if(now.isStart()){
                if(now.height > skyline){
                    skyline = now.height;
                    addResult(result, now.idx, skyline);
                }
                endPoints.offer(now.end);
            }else{
                while (!endPoints.isEmpty() && endPoints.peek().idx <= now.idx){
                    endPoints.poll();
                }

                if(!visited.add(now.idx)) continue;

                if(endPoints.isEmpty()){
                    skyline = 0;
                    addResult(result, now.idx, skyline);
                }else if(endPoints.peek().height < skyline){
//                    System.out.println(now.idx+ " "+now.height);
                    skyline = endPoints.peek().height;
                    addResult(result, now.idx, endPoints.peek().height);
                }
            }
        }

        return result;
    }

    private static void addResult(StringBuilder result, int idx, int skyline) {
        result.append(idx).append(" ").append(skyline).append(" ");
    }

    static class Point{
        int idx;
        int height;
        int flag; // start = -1, end = 1
        Point end;

        public Point(int idx, int height, int flag) {
            this.idx = idx;
            this.height = height;
            this.flag = flag;
        }

        public boolean isStart() {
            return flag == -1;
        }
    }
}