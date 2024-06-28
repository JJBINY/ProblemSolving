package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4_16197_두동전
 * BFS
 */
public class G4_16197_두동전 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
    static char[][] arr;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static Object solve(BufferedReader br) throws IOException {
        // init
        List<Coin> coins = new ArrayList<>();
        inputs(br, coins);
        Coin c1 = coins.get(0);
        Coin c2 = coins.get(1);

        // BFS 준비
        boolean[][][][] visited = new boolean[N][M][N][M];
        Deque<Pair<Pair<Coin, Coin>, Integer>> dq = new ArrayDeque<>(); // 코인2개 위치, 버튼 클릭 횟수
        dq.add(new Pair<>(new Pair<>(c1, c2), 0));

        // BFS 시작
        while (!dq.isEmpty()) {
            c1 = dq.peek().a.a;
            c2 = dq.peek().a.b;
            int seq = dq.pop().b;

            if (c1.isOut() && c2.isOut()) {
                continue;
            } else if (c1.isOut() || c2.isOut()) {
                return seq;
            } else if (seq >= 10) {
                continue;
            }else if (visited[c1.r][c1.c][c2.r][c2.c]) {
                continue;
            }
            visited[c1.r][c1.c][c2.r][c2.c] = true;

            for (int i = 0; i < 4; i++) {
                Coin nc1 = c1.move(i);
                Coin nc2 = c2.move(i);
                dq.add(new Pair<>(new Pair<>(nc1, nc2), seq + 1));
            }
        }
        return -1;
    }

    private static void inputs(BufferedReader br, List<Coin> coins) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'o') { // 초기 코인 위치
                    coins.add(new Coin(i, j));
                }
            }
        }
    }


    static class Coin {
        int r;
        int c;

        public Coin(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Coin move(int dir) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (!isOut(nr, nc) && arr[nr][nc] == '#') {
                nr = r;
                nc = c;
            }
            return new Coin(nr, nc);
        }

        public boolean isOut() {
            return isOut(r, c);
        }

        private boolean isOut(int r, int c) {
            if (r < 0 || r >= N || c < 0 || c >= M) {
                return true;
            }
            return false;
        }
    }

    static class Pair<A, B> {
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}