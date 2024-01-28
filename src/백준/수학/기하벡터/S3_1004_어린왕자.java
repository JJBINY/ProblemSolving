package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S3 1004 어린 왕자
 * 수학, 기하학
 */
public class S3_1004_어린왕자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            Point start = new Point(x, y);
            x = parseInt(st.nextToken());
            y = parseInt(st.nextToken());
            Point end = new Point(x, y);
            int N = parseInt(br.readLine());
            Circle[] circles = new Circle[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                x = parseInt(st.nextToken());
                y = parseInt(st.nextToken());
                int r = parseInt(st.nextToken());
                circles[i] = new Circle(x, y, r);
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if(start.isInCircle(circles[i]) ^ end.isInCircle(circles[i])) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        br.close();
        System.out.println(sb.toString());
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDistWith(Point dest) {
            return Math.sqrt(Math.pow(x - dest.x, 2) + Math.pow(y - dest.y, 2));
        }

        public boolean equals(Point p) {
            return x == p.x && y == p.y;
        }

        public boolean isInCircle(Circle c){
            Point point = c.point;
            double dist = getDistWith(point);
            return dist<=c.r;
        }

    }

    static class Circle {
        Point point;
        int r;

        public Circle(int x,int y,int r) {
            this(new Point(x, y), r);
        }

        public Circle(Point point, int r) {
            this.point = point;
            this.r = r;
        }

        public boolean equals(Circle c) {
            return point.equals(c.point) && r == c.r;
        }

    }
}
