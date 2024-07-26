package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G2_19238_스타트_택시
 * 구현, 시뮬레이션, BFS
 */
public class G2_19238_스타트_택시 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        택시 연료 바닥나면 업무 끝
        손님을 도착지로 데려다줄 때마다 연료 충전
        도착과 동시에 연료 떨어진 경우 실패하지 않은 것

        - NxN크기 격자에서 이동
            1. 각 칸은 비어 있거나 벽이 존재
            2. 상하좌우 인접칸으로 이동 가능
                - 한 칸 이동할 때마다 연료 1만큼 소모
                - 승객을 목적지로 이동시키면 그 과정에서 소모한 연료 두 배만큼 충전
            3. 항상 목적지까지 최단경로로만 이동 -> BFS(너비우선탐색)

        - M명의 승객을 태우는 것이 목표
            1. M명의 승객은 빈칸 중 하나에서 다른 칸으로 이동하려 함
            2. 한번에 한 승객만 탑승
            3. 출발지와 목적지에서만 승하차 가능
            4. 현재 위치에서 최단거리가 가장 짧은 승객을 태움 => 1
                - 그런 승객이 여러명일 경우 행번호, 열번호 순으로 가장 번호가 작은 승객 태움
                --> BFS 탐색 시 상좌우하 순으로 탐색하면 될것이라 예상
                    - 올라가는 것이 우선순위 가장 높고 내려가는 것이 우선순위 가장 낮으니까..?

     */
    /*
    fail point 1.
    직관적으로 상좌우하 순이 최적일 것이라 생각했으나 움직임을 손으로 그려보면 아님을 알 수 있음
    -> 비교 연산을 통해 우선순위 구현하여 해결
     */

    static Object solve(BufferedReader br) throws IOException {
        Taxi taxi = init(br);

        try {
            for (int i = 0; i < Taxi.M; i++) { // M명의 승객 수송
                taxi = moveToNextPassenger(taxi);
                taxi = transport(taxi, taxi.getPassenger());
            }
            return taxi.fuel;
        } catch (OperationFailException e) {
            return -1;
        }
    }

    private static Taxi init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken()); //초기 연료

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1; //행 번호 열 번호 1~N까지 이므로 인덱스 맞추기 위해 -1
        int c = Integer.parseInt(st.nextToken()) - 1;
        Taxi taxi = new Taxi(r, c, fuel);
        Taxi.N = N;
        Taxi.M = M;
        Taxi.arr = arr;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int dr = Integer.parseInt(st.nextToken()) - 1;
            int dc = Integer.parseInt(st.nextToken()) - 1;
            arr[sr][sc] = i + 2; // 1은 벽이므로 2부터 승객 idx 표현 및 탑승 위치 기록
            Taxi.passengers.put(i + 2, new Point(dr, dc)); // 승객 목적지 위치 기록
        }

        return taxi;
    }

    /**
     * BFS로 다음에 태울 승객 탐색
     *
     * @param src 탐색 시작 시점의 택시
     * @return 다음 승객 위치로 이동한 상태의 택시
     * @throws OperationFailException 연료 부족 등으로 인한 실패
     */
    private static Taxi moveToNextPassenger(Taxi src) throws OperationFailException {

        Deque<Taxi> dq = new ArrayDeque<>();
        dq.offer(src);
        boolean[][] visited = new boolean[Taxi.N][Taxi.N];
        visited[src.r][src.c] = true;

        Taxi opt = null; // 최단 거리에 있는 승객 위치 중 조건에 맞는 최적 승객 위치
        while (!dq.isEmpty()) {
            Taxi now = dq.poll();

            if (opt != null && now.fuel < opt.fuel) {
                break;
            }

            if (now.hasPassenger() && isMoreOptimal(opt, now)) {
                opt = now;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (!now.canMove(i)) {
                    continue;
                }

                Taxi next = now.move(i);
                if (visited[next.r][next.c]) {
                    continue;
                }
                visited[next.r][next.c] = true;
                dq.offer(next);
            }
        }
        if (opt == null) {
            throw new OperationFailException();
        }
        return opt;
    }

    private static boolean isMoreOptimal(Taxi opt, Taxi now) {
        return opt == null || now.compareTo(opt) < 0;
    }

    /**
     * BFS로 목적지까지 최단 경로로 이동
     *
     * @param src  이동 시작 시점의 택시
     * @param dest 이동 목적지
     * @return 목적지 도착 시점의 택시
     * @throws OperationFailException
     */
    private static Taxi transport(Taxi src, Point dest) throws OperationFailException {
        Deque<Taxi> dq = new ArrayDeque<>();
        dq.offer(src);
        boolean[][] visited = new boolean[Taxi.N][Taxi.N];
        visited[src.r][src.c] = true;

        while (!dq.isEmpty()) {
            Taxi now = dq.poll();

            if (now.equals(dest)) { //위치 같은 경우 (목적지 도달)
                int fuel = now.fuel + (src.fuel - now.fuel) * 2; //소모 연료 2배로 충전
                return new Taxi(now.r, now.c, fuel);
            }

            for (int i = 0; i < 4; i++) {
                if (!now.canMove(i)) {
                    continue;
                }

                Taxi next = now.move(i);
                if (visited[next.r][next.c]) {
                    continue;
                }
                visited[next.r][next.c] = true;
                dq.offer(next);
            }
        }

        throw new OperationFailException();
    }

    static class Point implements Comparable {
        final int r;
        final int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) {
                return false;
            }
            Point point = (Point) o;
            if (r != point.r) return false;
            return c == point.c;
        }

        @Override
        public int compareTo(Object o) {
            Point p = (Point) o;
            int rowCompared = Integer.compare(r, p.r);
            if (rowCompared < 0) {
                return -1;
            } else if (rowCompared > 0) {
                return 1;
            }

            int colCompared = Integer.compare(c, p.c);
            if (colCompared < 0) {
                return -1;
            } else if (colCompared > 0) {
                return 1;
            }
            return 0;
        }
    }

    static class Taxi extends Point {
        static int N;
        static int M;
        static int[][] arr;
        static HashMap<Integer, Point> passengers = new HashMap<>();
        static int[] dr = {-1, 0, 0, 1}; //상 좌 우 하
        static int[] dc = {0, -1, 1, 0};
        final int fuel;

        public Taxi(int r, int c, int fuel) {
            super(r, c);
            this.fuel = fuel;
        }

        public boolean canMove(int direction) {
            int nr = r + dr[direction];
            int nc = c + dc[direction];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
            if (arr[nr][nc] == 1) return false;
            if (fuel == 0) return false;

            return true;
        }

        public Taxi move(int direction) {
            if (!canMove(direction)) {
                throw new IllegalArgumentException(direction + " 방향으로 움직일 수 없습니다.");
            }
            int nr = r + dr[direction];
            int nc = c + dc[direction];
            return new Taxi(nr, nc, fuel - 1);
        }

        public boolean hasPassenger() {
            return arr[r][c] > 1;
        }

        public Point getPassenger() {
            if (!hasPassenger()) {
                throw new IllegalStateException("현재 위치에 태울 수 있는 손님이 없습니다.");
            }
            Point passenger = passengers.get(arr[r][c]);
            arr[r][c] = 0;
            return passenger;
        }
    }

    static class OperationFailException extends Exception {
    }
}