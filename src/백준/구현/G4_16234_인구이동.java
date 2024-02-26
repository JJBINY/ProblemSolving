package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G4 16234 인구 이동
 * 구현, BFS
 */
public class G4_16234_인구이동 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[] dr = new int[]{1, -1, 0, 0};
            int[] dc = new int[]{0, 0, 1, -1};
            int ans = 0;
            while (true) {
                //인구 이동
                boolean moved = move(N, L, R, arr, dr, dc);
//                printArr(arr);

                if(!moved) break;
                ans++;
            }
            System.out.println(ans);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printArr(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean move(int N, int L, int R, int[][] arr, int[] dr, int[] dc) {
        boolean moved = false;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                List<Point> union = BFS(N, L, R, arr, dr, dc, visited, i, j);
                if(union.size()>1) {
                    reassign(arr, union);
                    moved = true;
                }
            }
        }
        return moved;
    }

    private static void reassign(int[][] arr, List<Point> union) {
        int sum = union.stream().mapToInt(p -> arr[p.r][p.c]).sum();
        int val = sum / union.size();
        for (Point point : union) {
            arr[point.r][point.c] = val;
        }
    }

    private static List<Point> BFS(int N, int L, int R, int[][] arr, int[] dr, int[] dc, boolean[][] visited, int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        visited[i][j] = true;
        List<Point> union = new ArrayList<>();
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            union.add(now);
            for (int k = 0; k < 4; k++) {
                int nr = now.r + dr[k];
                int nc = now.c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                int diff = Math.abs(arr[now.r][now.c] - arr[nr][nc]);
                if (diff >= L && diff <= R) {
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                }
            }
        }
        return union;
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
