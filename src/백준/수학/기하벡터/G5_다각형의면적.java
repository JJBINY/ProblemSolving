package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * G5_다각형의 면적
 * https://www.acmicpc.net/problem/2166
 */
public class G5_다각형의면적 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<Point> points = new ArrayList<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long[] arr = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            points.add(new Point(arr[0], arr[1]));
            if(i<2) continue;
            long area = CCW(points.get(0), points.get(i), points.get(i-1));
            ans += area;
        }

        System.out.printf("%.1f",Math.abs(ans)/2.0);

    }

    static public long CCW(Point a, Point b, Point c){
        return (a.x * b.y + b.x * c.y + c.x * a.y) - (a.y * b.x + b.y * c.x + c.y * a.x);
    }
    static class Point{
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}