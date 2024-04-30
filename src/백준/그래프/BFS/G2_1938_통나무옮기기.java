package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import static java.lang.Integer.parseInt;


/**
 * G2 1938 통나무 옮기기
 * bfs, 구현
 */
public class G2_1938_통나무옮기기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N;
    static int[] dr = {0, 0, -1, 1}, dc = {1, -1, 0, 0};
    static char[][] arr;
    static Log start, end;

    static void solve(BufferedReader br) throws IOException {
        //init
        init(br);

        // bfs
        Queue<Pair<Log, Integer>> q = new ArrayDeque<>();
        q.offer(new Pair<>(start, 0));
        boolean[][][] visited = new boolean[N][N][2];
        visited[start.center.r][start.center.c][start.state.val] = true;
        while (!q.isEmpty()) {
            Pair<Log, Integer> pair = q.poll();
            Log now = pair.a;
            int dist = pair.b;

            if (now.equals(end)) {
                System.out.println(dist);
                return;
            }
            move(q, visited, now, dist);
            rotate(q, visited, now, dist);

        } // while

        System.out.println(0);
    }

    private static void move(Queue<Pair<Log, Integer>> q, boolean[][][] visited, Log now, int dist) {
        for (int i = 0; i < 4; i++) {
            int nr = now.center.r + dr[i];
            int nc = now.center.c + dc[i];
            if (check(nr, nc, now.state)) {
                continue;
            }
            if (visited[nr][nc][now.state.val]) {
                continue;
            }
            visited[nr][nc][now.state.val] = true;
            q.offer(new Pair<>(
                    new Log(new Point(nr, nc), now.state),
                    dist + 1));
        }
    }

    private static void rotate(Queue<Pair<Log, Integer>> q, boolean[][][] visited, Log now, int dist) {
        if (canRotate(now.center, now.state)) {
            Log next = new Log(now.center, now.state.rotate());
            if (visited[next.center.r][next.center.c][next.state.val]) {
                return;
            }
            visited[next.center.r][next.center.c][next.state.val] = true;
            q.offer(new Pair<>(next, dist + 1));
        }
    }

    private static void init(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            arr[i] = input.toCharArray();
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'B' && start == null) {
                    if (j + 1 < N && arr[i][j + 1] == 'B') {
                        start = new Log(new Point(i, j + 1), State.HORIZONTAL);
                    } else {
                        start = new Log(new Point(i + 1, j), State.VERTICAL);
                    }
                } else if (arr[i][j] == 'E' && end == null) {
                    if (j + 1 < N && arr[i][j + 1] == 'E') {
                        end = new Log(new Point(i, j + 1), State.HORIZONTAL);
                    } else {
                        end = new Log(new Point(i + 1, j), State.VERTICAL);
                    }
                }
            }
        } // for
    }

    private static boolean check(int nr, int nc, State state) {
        if (state == State.HORIZONTAL) {
            if (nr < 0 || nr >= N || nc - 1 < 0 || nc + 1 >= N)
                return true;
            for (int i = -1; i <= 1; i++) {
                if (arr[nr][nc + i] == '1')
                    return true;
            }
        } else {
            if (nr - 1 < 0 || nr + 1 >= N || nc < 0 || nc >= N)
                return true;
            for (int i = -1; i <= 1; i++) {
                if (arr[nr + i][nc] == '1')
                    return true;
            }
        }
        return false;
    }

    static boolean canRotate(Point head, State state) {

        for (int i = -1; i <= 1; i++) {
            int nr = head.r + i;
            for (int j = -1; j <= 1; j++) {
                int nc = head.c + j;
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    return false;
                }
                if (arr[nr][nc] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    static class Pair<A, B> {
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    public static class Log {

        Point center;
        State state;

        public Log(Point center, State state) {
            this.center = center;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Log)) return false;
            Log log = (Log) o;
            if (!Objects.equals(center, log.center)) return false;
            return state == log.state;
        }

        @Override
        public int hashCode() {
            int result = center != null ? center.hashCode() : 0;
            result = 31 * result + (state != null ? state.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Log{" +
                    "center=" + center +
                    ", state=" + state +
                    '}';
        }
    }

    public enum State {
        HORIZONTAL(0), VERTICAL(1);

        int val;

        State(int val) {
            this.val = val;
        }

        public State rotate() {
            return this.val == 0 ? VERTICAL : HORIZONTAL;
        }
    }

    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;

            if (r != point.r) return false;
            return c == point.c;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            return result;
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