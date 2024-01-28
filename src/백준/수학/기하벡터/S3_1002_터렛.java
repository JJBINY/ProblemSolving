package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S3 1002 터렛
 * 수학, 기하학
 */
public class S3_1002_터렛 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = parseInt(st.nextToken());
            int y1 = parseInt(st.nextToken());
            int r1 = parseInt(st.nextToken());
            int x2 = parseInt(st.nextToken());
            int y2 = parseInt(st.nextToken());
            int r2 = parseInt(st.nextToken());
            Circle c1 = new Circle(new Point(x1, y1), r1);
            Circle c2 = new Circle(new Point(x2, y2), r2);
            double dist = c1.point.getDistWith(c2.point);
            int sum = r1 + r2;
            int sub = Math.abs(r1 - r2);
            if (c1.equals(c2)) {
                sb.append(-1);
            } else if (sub < dist && dist < sum) {
                sb.append(2);
            } else if (sum == dist || sub == dist) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
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

    }

    static class Circle {
        Point point;
        int r;

        public Circle(Point point, int r) {
            this.point = point;
            this.r = r;
        }

        public boolean equals(Circle c) {
            return point.equals(c.point) && r == c.r;
        }

    }
}
