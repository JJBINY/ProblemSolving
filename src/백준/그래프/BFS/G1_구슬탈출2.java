package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G1 구슬 탈출 2
 * https://www.acmicpc.net/problem/13460
 */
public class G1_구슬탈출2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        String[] arr = new String[h];
        Marble red = null;
        Marble blue = null;
        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine();
            for (int j = 0; j < w; j++) {
                if (arr[i].charAt(j) == 'R') {
                    red = new Marble(i, j);
                }
                if (arr[i].charAt(j) == 'B') {
                    blue = new Marble(i, j);
                }
            }
        }

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        boolean[][][][] visited = new boolean[h][w][h][w];
        visited[red.r][red.c][blue.r][blue.c] = true;
        Queue<Triple> queue = new LinkedList<>();
        queue.add(new Triple(red, blue, 0));

        while (!queue.isEmpty()) {
            red = (Marble) queue.peek().a;
            blue = (Marble) queue.peek().b;

//            System.out.println("from");
//            System.out.println(red.r + ", " + red.c);
//            System.out.println(blue.r + ", " + blue.c);
            int cnt = (int) queue.poll().c;

            if (cnt > 10) {
                break;
            } else if (arr[red.r].charAt(red.c) == 'O') {
                System.out.println(cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                Marble nextR = red.moveTo(arr, dr[i], dc[i], blue);
                Marble nextB = blue.moveTo(arr, dr[i], dc[i], nextR);
                nextR = nextR.moveTo(arr, dr[i], dc[i], nextB);

                if (visited[nextR.r][nextR.c][nextB.r][nextB.c]) continue;
                visited[nextR.r][nextR.c][nextB.r][nextB.c] = true;
//                System.out.println(nextR.r + ", " + nextR.c);
//                System.out.println(nextB.r + ", " + nextB.c);
//                System.out.println();
                if (arr[nextB.r].charAt(nextB.c) == 'O') continue;
                queue.add(new Triple(nextR, nextB, cnt + 1));
            }
        }

        System.out.println(-1);
        br.close();
    }


    static class Marble {
        int r;
        int c;

        public Marble(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Marble marble = (Marble) o;
            return r == marble.r && c == marble.c;
        }

        public Marble moveTo(String[] arr, int dr, int dc, Marble other) {
            if (arr[r].charAt(c) == 'O') return this;

            Marble marble = new Marble(r, c);
            while (true) {
                marble.r += dr;
                marble.c += dc;
                if (arr[marble.r].charAt(marble.c) == 'O') break;
                else if (arr[marble.r].charAt(marble.c) == '#'
                        || marble.equals(other)) {
                    marble.r -= dr;
                    marble.c -= dc;
                    break;
                }

            }
            return marble;
        }
    }

    static class Triple {
        Object a;
        Object b;
        Object c;

        public Triple(Object a, Object b, Object c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}