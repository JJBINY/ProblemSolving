package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 3190 뱀
 * 구현, 시뮬레이션, 자료구조, 덱
 */
public class G4_3190_뱀 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean[][] isSnake, isApple;
    static int N, time, direction;
    static int[] dr = new int[]{0, 1, 0, -1}; //우,하,좌,상
    static int[] dc = new int[]{1, 0, -1, 0};
    static Deque<Pair> snake = new LinkedList<>();


    static void solve(BufferedReader br) throws IOException {
        N = parseInt(br.readLine());
        int K = parseInt(br.readLine());

        isApple = new boolean[N][N];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken()) - 1;
            int c = parseInt(st.nextToken()) - 1;
            isApple[r][c] = true;
        }

        //방향전환
        int L = parseInt(br.readLine());
        int[] secs = new int[L];
        char[] direcs = new char[L];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            secs[i] = parseInt(st.nextToken());
            direcs[i] = st.nextToken().charAt(0); //L, D
        }


        isSnake = new boolean[N][N];
        isSnake[0][0] = true;
        direction = 0;
        time = 0;
        snake.add(new Pair(0, 0));
        int changed = 0;
        while (move()){
            if(changed<L && time == secs[changed]){
                if(direcs[changed++] == 'D'){
                    direction += 1;
                }else{
                    direction += 3;
                }
                direction %= 4;
            }
        }
        System.out.println(time);
    }

    static boolean move() {
        time++;
        Pair head = snake.peekFirst();
        int nr = head.r + dr[direction];
        int nc = head.c + dc[direction];

        if (nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
        if (isSnake[nr][nc]) return false;

        isSnake[nr][nc] = true;
        snake.offerFirst(new Pair(nr, nc));
        if (isApple[nr][nc]) {
            isApple[nr][nc] = false;
        } else {
            Pair tail = snake.removeLast();
            isSnake[tail.r][tail.c] = false;
        }
        return true;
    }

    static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}