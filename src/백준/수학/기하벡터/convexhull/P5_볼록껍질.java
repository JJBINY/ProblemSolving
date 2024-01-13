package 백준.수학.기하벡터.convexhull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P5 볼록껍질 (Convex Hull)
 * https://www.acmicpc.net/problem/1708
 * https://ko.wikipedia.org/wiki/%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%97%84_%EC%8A%A4%EC%BA%94
 * graham's scan
 */
public class P5_볼록껍질 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();
        Point pivot = null;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Point point = new Point(Long.parseLong(st.nextToken()),
                    Long.parseLong(st.nextToken()));
            if(pivot == null){
                pivot = point;
            }
            else if(point.y<pivot.y || (point.y == pivot.y && point.x < pivot.x)){
                pivot = point;
            }
            points.add(point);
        }
        final Point p =pivot;
        PriorityQueue<Point> pq = new PriorityQueue<>((a,b)->{
            int angle = ccw(p, a, b);
            if(angle == 0){
                double cmp = dist(p, a) - dist(p, b);
                if(cmp<0){
                    return -1;
                }else if(cmp>0){
                    return 1;
                }else {
                   return 0;
                }
            }
            return -angle;
        });
        pq.addAll(points);
        Stack<Point> stack = new Stack<>();
        stack.push(pq.poll());
        stack.push(pq.poll());
        while (!pq.isEmpty()) {
            while (stack.size()>=2&&!isLeftRotation(pq.peek(),stack.get(stack.size()-2),stack.peek())){
                stack.pop();
            }
            stack.push(pq.poll());
        }
        System.out.println(stack.size());
    }

    static private double dist(Point a, Point b)
    {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    static private boolean isLeftRotation(Point a, Point b, Point c) {
        long result = ccw(a, b, c);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    static private int ccw(Point a, Point b, Point c) {
        long crossProduct = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
        if(crossProduct>0){
            return 1;
        }else if(crossProduct<0){
            return -1;
        }else{
            return 0;
        }

    }

    static class Point {

        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

    }

}