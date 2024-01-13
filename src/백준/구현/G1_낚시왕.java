package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G1 낚시왕
 * https://www.acmicpc.net/problem/17143
 */
public class G1_낚시왕 {

    static int[][] arr;
    static List<Shark> sharks = new ArrayList<>();
    static int R;
    static int C;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[R + 1][C + 1];
        sharks = new ArrayList<>();
        sharks.add(new Shark(0, 0, 0, 0, 0));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r, c, speed, direction, size));
            arr[r][c] = i + 1;
        }

        int ans = 0;
        for (int fisher = 1; fisher < C + 1; fisher++) {
            ans += catchShark(fisher);
            arr = moveSharks();

        }
        System.out.println(ans);
    }

    static void printArr() {
        for (int i = 1; i < R + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] dr = new int[]{0, -1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 1, -1};

    static int[][] moveSharks() {
        int[][] moved = new int[R + 1][C + 1];
        for (int i = 1; i < M + 1; i++) {
            Shark shark = sharks.get(i);
            if (!shark.isActivated()) continue;

            if (shark.direction < 3) {//상
                int move = shark.speed % ((R - 1) * 2);
                int nr = shark.r;
                while (move-- > 0) {
                    nr += dr[shark.direction];
                    if (nr < 1) {
                        nr = 2;
                        shark.direction = 2;
                    } else if (nr > R) {
                        nr = R - 1;
                        shark.direction = 1;
                    }
                }
                shark.r = nr;
            } else {
                int move = shark.speed % ((C - 1) * 2);
                int nc = shark.c;
                while (move-- > 0) {
                    nc += dc[shark.direction];
                    if (nc < 1) {
                        nc = 2;
                        shark.direction = 3;
                    } else if (nc > C) {
                        nc = C - 1;
                        shark.direction = 4;
                    }
                }
                shark.c = nc;
            }
            moveShark(moved, i, shark);
        }
        return moved;
    }

    private static void moveShark(int[][] moved, int i, Shark shark) {
        if (moved[shark.r][shark.c] > 0) {
            Shark other = sharks.get(moved[shark.r][shark.c]);
            if (shark.size < other.size) {
                shark.deactivate();
            } else {
                other.deactivate();
                moved[shark.r][shark.c] = i;
            }
        } else {
            moved[shark.r][shark.c] = i;
        }
    }

    static int catchShark(int fisher) {
        for (int i = 1; i < R + 1; i++) {
            if (arr[i][fisher] > 0) {
                Shark target = sharks.get(arr[i][fisher]);
                target.deactivate();
//                arr[i][fisher] = 0;
                return target.size;
            }
        }
        return 0;
    }

    static class Shark {
        int r;
        int c;
        int speed;
        int direction; //1234 상하우좌
        int size;
        boolean activated = true;

        public Shark(int r, int c, int speed, int direction, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public boolean isActivated() {
            return activated;
        }

        public void deactivate() {
            activated = false;
        }
    }
}
