package 백준.수학.기하벡터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G2 1007 벡터매칭
 * 수학, 기하학, 조합
 *
 * 변수명을 잘보자,,
 */
public class G2_1007_벡터매칭 {

    static double min;
    static Point[] points;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = parseInt(br.readLine());
            points = new Point[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken());
                int y = parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            min = Double.MAX_VALUE;
            selected = new int[N];
            Arrays.fill(selected, -1);
            simulate(0,0);
            sb.append(min).append("\n");
        }
        br.close();
        System.out.println(sb.toString());
    }

    static void simulate(int idx, int cnt){
        if(cnt == points.length/2){
            min = Math.min(min,getVectorSize());
            return;
        }

        for (int i = idx; i < points.length; i++) {
            selected[i] = 1;
            simulate(i+1,cnt + 1);
            selected[i] = -1;
        }
    }

    private static double getVectorSize() {
        int dx = 0;
        int dy = 0;
        for (int i = 0; i < points.length; i++) {
            dx += selected[i]* points[i].x;
            dy += selected[i]* points[i].y;
        }

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
