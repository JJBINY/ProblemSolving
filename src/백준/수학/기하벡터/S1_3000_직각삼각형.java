package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 3000 직각 삼각형
 * 기하학
 * 값의 범위에 주의
 */
public class S1_3000_직각삼각형 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parseInt(br.readLine());
        Map<Integer, Long> vertical = new HashMap<>();
        Map<Integer, Long> horizontal = new HashMap<>();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            points[i] = new Point(x, y);
            vertical.put(x, vertical.getOrDefault(x, -1L) + 1);
            horizontal.put(y, horizontal.getOrDefault(y, -1L) + 1);
        }
        long ans  =0;
        for (Point point : points) {
            ans += horizontal.get(point.y)*vertical.get(point.x);
        }
        System.out.println(ans);
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
