package 백준.스위핑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * G2 철로
 * https://www.acmicpc.net/problem/13334
 */
public class G3_철로 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        int BILLION = (int) Math.pow(10, 9);
        for (int i = 0; i < n; i++) {
            int[] idxes = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(x->Integer.parseInt(x)-BILLION).sorted().toArray();
            lines.add(new Line(idxes[0], idxes[1]));
        }
        int d = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Line line = lines.get(i);
            if(line.end - line.start >d) continue;
            int start = line.end;
            int end = line.start + d;
            points.add(new Point(start, 0));
            points.add(new Point(end, 1));
        }
        points.sort(Comparator.comparingInt(Point::getIdx)
                .thenComparingInt(Point::getType));
        int cnt = 0;
        int ans = 0;
        for (Point point : points) {
            if(point.isStart()){
                cnt+=1;
                ans = Math.max(ans, cnt);
            }else{
                cnt -= 1;
            }
        }
        System.out.println(ans);
    }

    static class Point{
        int idx;
        int type;
        public Point(int idx, int type) {
            this.idx = idx;
            this.type = type;
        }

        public int getIdx() {
            return idx;
        }
        public int getType() {
            return type;
        }
        public boolean isStart(){
            return type == 0;
        }
    }

    static class Line {
        int start;
        int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
