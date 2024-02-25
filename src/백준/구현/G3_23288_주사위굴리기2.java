package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * G3 23288 주사위 굴리기 2
 * 구현, 시뮬레이션, BFS
 */
public class G3_23288_주사위굴리기2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][M];
            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int score = 0;
            Dice dice = Dice.getInstance();
            while (K-- > 0) {
                dice.move(N,M);
                int A = dice.bottom;
                int B = arr[dice.x][dice.y];
                int C = calculateC(N, M, arr, dice);

                score += B*C;

                if(A>B){
                    dice.changDirectionClockwise();
                }else if(A<B){
                    dice.changDirectionAntiClockwise();
                }

            }
            System.out.println(score);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int calculateC(int N, int M, int[][] arr, Dice dice) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(dice.x, dice.y));
        boolean[][] visited = new boolean[N][M];
        visited[dice.x][dice.y] = true;
        int C = 0;
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};
        while (!queue.isEmpty()){
            Point now = queue.poll();
            C += 1;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx<0||nx>= N ||ny<0||ny>= M){
                    continue;
                }
                if(visited[nx][ny]){
                    continue;
                }
                visited[nx][ny] = true;
                if (arr[nx][ny] == arr[dice.x][dice.y]) {
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return C;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Dice {

        enum Direction {
            EAST, WEST, SOUTH, NORTH;
        }

        int top;
        int bottom;
        int u, d, l, r;
        int x, y;
        Direction direction;

        public static Dice getInstance() {
            Dice dice = new Dice();
            dice.top = 1;
            dice.bottom = 6;
            dice.u = 2;
            dice.d = 5;
            dice.l = 4;
            dice.r = 3;
            dice.x = 0;
            dice.y = 0;
            dice.direction = Direction.EAST;
            return dice;
        }

        public void changDirectionClockwise() {
            switch (direction) {
                case EAST:
                    direction = Direction.SOUTH;
                    break;
                case WEST:
                    direction = Direction.NORTH;
                    break;
                case SOUTH:
                    direction = Direction.WEST;
                    break;
                case NORTH:
                    direction = Direction.EAST;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        public void changDirectionAntiClockwise() {
            switch (direction) {
                case EAST:
                    direction = Direction.NORTH;
                    break;
                case WEST:
                    direction = Direction.SOUTH;
                    break;
                case SOUTH:
                    direction = Direction.EAST;
                    break;
                case NORTH:
                    direction = Direction.WEST;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        public void move(int N, int M) {
            move();

            if (x < 0 || x >= N || y < 0 || y >= M){
                reverseDirection();
                move();
                move();
            }
        }

        private void move() {
            switch (direction) {
                case EAST:
                    moveEast();
                    break;
                case WEST:
                    moveWest();
                    break;
                case SOUTH:
                    moveSouth();
                    break;
                case NORTH:
                    moveNorth();
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        private void reverseDirection() {
            switch (direction) {
                case EAST:
                    direction = Direction.WEST;
                    break;
                case WEST:
                    direction = Direction.EAST;
                    break;
                case SOUTH:
                    direction = Direction.NORTH;
                    break;
                case NORTH:
                    direction = Direction.SOUTH;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        private void moveEast() {
            int temp = top;
            top = l;
            l = bottom;
            bottom = r;
            r = temp;
            y += 1;
        }

        private void moveWest() {
            int temp = top;
            top = r;
            r = bottom;
            bottom = l;
            l = temp;
            y -= 1;
        }

        private void moveSouth() {
            int temp = top;
            top = u;
            u = bottom;
            bottom = d;
            d = temp;
            x += 1;
        }

        private void moveNorth() {
            int temp = top;
            top = d;
            d = bottom;
            bottom = u;
            u = temp;
            x -= 1;
        }
    }
}
