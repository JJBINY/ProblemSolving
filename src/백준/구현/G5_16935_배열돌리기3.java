package 백준.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G5 16935 배열돌리기3
 * n^3
 */
public class G5_16935_배열돌리기3 {

    /*
    1번 상하반전
    2번 좌우반전
    3번 90도 시계방향
    4번 90도 반시계방향
    5번 4분면 시계방향
    6번 4분면 반시계방향
     */
    static List<Point> points = new ArrayList<>();
    static int N, M; //짝수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        int R = parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                points.add(new Point(i, j, parseInt(st.nextToken())));
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            switch (parseInt(st.nextToken())) {
                case 1:
                    func1();
                    break;
                case 2:
                    func2();
                    break;
                case 3:
                    func3();
                    break;
                case 4:
                    func4();
                    break;
                case 5:
                    func5();
                    break;
                case 6:
                    func6();
                    break;


            }
        }

        int[][] ans = new int[N][M];
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
//            System.out.println("point = " + point);
            ans[point.r][point.c] = point.val;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void func1() {
        for (Point point : points) {
            point.r = N - point.r - 1;
        }
    }

    static void func2() {
        for (Point point : points) {
            point.c = M - point.c - 1;
        }
    }

    static void func3() {
        for (Point point : points) {
            int nr = point.c;
            int nc = N-1-point.r;
            point.r = nr;
            point.c = nc;
        }
        int temp = N;
        N =M;
        M = temp;
    }
    static void func4() {
        for (Point point : points) {
            int nr = M-1-point.c;
            int nc = point.r;
            point.r = nr;
            point.c = nc;
        }
        int temp = N;
        N =M;
        M = temp;
    }
    static void func5() {
        int mr = N/2;
        int mc = M/2;
        for (Point point : points) {
            if(point.r<mr){//상단
                if(point.c<mc){ //좌측
                    point.c += mc;
                }else{ //우측
                    point.r += mr;
                }
            }else{//하단
                if(point.c<mc) { // 좌측
                    point.r -= mr;
                }else{ // 우측
                    point.c -= mc;
                }
            }
        }
    }
    static void func6() {
        int mr = N/2;
        int mc = M/2;
        for (Point point : points) {
            if(point.r<mr){//상단
                if(point.c<mc){ //좌측
                    point.r += mr;
                }else{ //우측
                    point.c -= mc;
                }
            }else{//하단
                if(point.c<mc) { // 좌측
                    point.c += mc;
                }else{ // 우측
                    point.r -= mr;
                }
            }
        }
    }


    static class Point {
        int r;
        int c;
        int val;

        public Point(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", val=" + val +
                    '}';
        }
    }

}
