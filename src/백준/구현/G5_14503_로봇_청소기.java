package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5_14503_로봇_청소기
 * 구현, 시뮬레이션
 */
public class G5_14503_로봇_청소기 {
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

    static int N, M;
    static int[][] board;
    static boolean[][] cleaned;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        cleaned = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Robot robot = new Robot(sr, sc, sd);
        while (robot.doAction()) {
        };
        return robot.cnt;
    }

    static class Robot {
        private int r;
        private int c;
        int cnt;
        private int d; // 북 동 남 서

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public boolean doAction() {
            if (!cleaned[r][c]) {
                cleaned[r][c] = true;
                cnt++;
            } else if (!hasNonCleanRoom()) {
                int d = (this.d + 2) % 4;

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 1) {
                    return false;
                }
                r = nr;
                c = nc;
            } else {
                d = (d + 3) % 4;

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 1) {
                }else if (!cleaned[nr][nc]){
                    r = nr;
                    c= nc;
                }
            }

            return true;
        }

        private boolean hasNonCleanRoom() {
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 1) {
                    continue;
                }
                if (!cleaned[nr][nc]) {
                    return true;
                }
            }
            return false;
        }

    }
}