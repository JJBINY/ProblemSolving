package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G2 17837 새로운 게임2
 * 구현, 시뮬레이션
 */
public class G2_17837_새로운게임2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        List<List<Deque<Piece>>> board = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            List<Deque<Piece>> list = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                list.add(new ArrayDeque<>());
            }
            board.add(list);
        }

        Piece[] pieces = new Piece[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken()) - 1;
            int c = parseInt(st.nextToken()) - 1;
            int d = parseInt(st.nextToken()) - 1;
            pieces[i] = new Piece(r, c, d);
            board.get(r).get(c).add(pieces[i]);
        }

        int[] dr = {0, 0, -1, 1}; //우좌상하
        int[] dc = {1, -1, 0, 0};
        for (int turn = 1; turn <= 1000; turn++) {
            for (int j = 0; j < K; j++) {

                Piece piece = pieces[j];
                int nr = piece.r + dr[piece.direction];
                int nc = piece.c + dc[piece.direction];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] == 2) {// 파란색
                    piece.changeDirection();
                    nr = piece.r + dr[piece.direction];
                    nc = piece.c + dc[piece.direction];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] == 2) {
                        continue;
                    }
                }

                Deque<Piece> now = board.get(piece.r).get(piece.c);
                Deque<Piece> next = board.get(nr).get(nc);

                Deque<Piece> dq = new ArrayDeque<>();
                int size = now.size();
                for (int i = size - 1; i >= 0; i--) {
                    dq.offerLast(now.pollLast());
                    if (dq.peekLast() == piece) {
                        break;
                    }
                }

                size = dq.size();
                for (int i = 0; i < size; i++) {
                    if (arr[nr][nc] != 1) {
                        next.add(dq.pollLast().move(nr, nc));
                    } else {
                        next.add(dq.pollFirst().move(nr, nc));
                    }
                }

                if (next.size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    static class Piece {
        int r;
        int c;
        int direction;

        public Piece(int r, int c, int direction) {
            this.r = r;
            this.c = c;
            this.direction = direction;
        }

        public void changeDirection() {
            if (direction % 2 == 0) {
                direction++;
            } else {
                direction--;
            }
        }


        public Piece move(int nr, int nc) {
            this.r = nr;
            this.c = nc;
            return this;
        }

    }

}