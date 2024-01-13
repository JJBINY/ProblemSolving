package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * P5 선분그룹
 * https://www.acmicpc.net/problem/2162
 */
public class P5_선분그룹 {

    static int[] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        parent = IntStream.range(0, n).toArray();
        for (int i = 0; i < n; i++) {
            int[] points = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            lines.add(Line.of(points[0], points[1], points[2], points[3]));
        }

        for (int i = 0; i < n; i++) {
            Line line = lines.get(i);
            for (int j = i+1; j < n; j++) {
                Line other = lines.get(j);
                if(find(i)==find(j)) continue;
                if(line.isCrossWith(other)){
                    union(i, j);
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(find(i), map.getOrDefault(parent[i], 0) + 1);
        }

        System.out.println(map.keySet().size());
        System.out.println(map.values().stream().max(Comparator.comparingInt(i->i)).get());
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b) parent[b] = a;
        else parent[a] = b;
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static class Line {
        Point p1;
        Point p2;

        private Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public static Line of(int x1, int y1, int x2, int y2) {
            Point p1 = new Point(x1,y1);
            Point p2 = new Point(x2,y2);
            if (p1.compareTo(p2) >= 0) {
                swap(p1, p2);
            }
            return new Line(p1, p2);
        }

        public boolean isCrossWith(Line other) {
            long v1 = CCW(p1, p2, other.p1);
            long v2 = CCW(p1, p2, other.p2);

            long v3 = CCW(other.p1, other.p2, p1);
            long v4 = CCW(other.p1, other.p2, p2);
            long r1 = v1 * v2; //각각의 선분의 한 점에서 다른 선분의 점들에 대해 그은 벡터
            long r2 = v3 * v4;
            if (r1 == 0 && r2 == 0) {
                if (p1.compareTo(other.p2) <= 0 && other.p1.compareTo(p2) <= 0) {
                    return true;

                } else {
                    return false;
                }
            }

            if (r1 <= 0 && r2 <= 0) {
                return true;
            } else {
                return false;
            }
        }

        private long CCW(Point a, Point b, Point c) {
            long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
            if (result > 0) {
                return 1;
            } else if (result == 0) {
                return 0;
            } else {
                return -1;
            }
        }

        static public void swap(Point p1, Point p2) {
            long x = p1.x;
            long y = p1.y;
            p1.x = p2.x;
            p1.y = p2.y;
            p2.x = x;
            p2.y = y;
        }

        static class Point implements Comparable {
            long x;
            long y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }


            @Override
            public int compareTo(Object o) {
                Point p = (Point) o;
                if (x == p.x) {
                    return (int) (y - p.y);
                }
                return (int) (x - p.x);
            }
        }
    }
}
