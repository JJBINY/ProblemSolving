package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G2 선분교차2
 * https://www.acmicpc.net/problem/17387
 */
public class G2_선분교차2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p4 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        if (p1.compareTo(p2) >= 0) {
            swap(p1, p2);
        }
        if (p3.compareTo(p4) >= 0) {
            swap(p3, p4);
        }


        long v1 = CCW(p1, p2, p3);
        long v2 = CCW(p1, p2, p4);

        long v3 = CCW(p3, p4, p1);
        long v4 = CCW(p3, p4, p2);
        long r1 = v1 * v2; //각각의 선분의 한 점에서 다른 선분의 점들에 대해 그은 벡터
        long r2 = v3 * v4;
        if (r1 == 0 && r2 == 0) {
            if (p1.compareTo(p4) <= 0 && p3.compareTo(p2) <= 0) {
                System.out.println(1);

            }else{
                System.out.println(0);
            }
            return;
        }else if (r1 <= 0 && r2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    
    static public long CCW(Point a, Point b, Point c) {
        long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
        if(result>0){
            return 1;
        }else if( result == 0){
            return 0;
        }else{
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