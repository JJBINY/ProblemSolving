package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G1_구슬_탈출_2
 * 구현, 시뮬레이션, BFS
 */
public class G1_13460_구슬_탈출_2 {

    /*
     *  NxM 보드 내 1x1 구멍과 구슬 2개(빨강, 파랑) 존재
     *  해당 구멍으로 빨간 구슬만 빼내기
     *  중력과 기울기를 이용해서만 이동 가능
     *  - 벽 혹은 다른 구멍에 막히기 전까지 해당 방향으로 계속 이동
     *  - 상하좌우 4방향
     *  - 두 공은 동시에 움직임
     *  - 하나의 동작으로 빨간구멍이 먼저 탈출한 뒤 파란 구멍이 탈출한다면 실패
     *
     * sol)
     * - N과 M의 최댓값이 10으로 보드의 크기가 작다. -> 단순 구현 및 시뮬레이션 문제임을 유추
     * - 최소 움직임을 구해야 한다 = 최단경로로 움직여야 한다
     *      => 가중치 없는 최단경로는 BFS로 구할 수 있다.
     *      => 기울기 한 번에 움직이는 칸의 수는 매번 달라지겠지만
     *         여기서 중요한 요소(경로)는 기울기 횟수다.
     *          따라서 한 번의 기울이기를 하나의 경로로 보고 최단경로를 구한다.
     */

    static int N, M;
    static char[][] arr;


    public static void main(String[] args) throws IOException {
        Triple<Marble,Marble,Object> init = init();
        Marble red = init.a;
        Marble blue = init.b;

        // red와 blue의 상태는 각자의 위치에 따라 독립적인 것이 아니라 서로의 상대적인 위치에 따라 결정됨 따라서 4차원 배열 사용
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[red.r][red.c][blue.r][blue.c] = true;

        Queue<Triple<Marble,Marble,Integer>> queue = new LinkedList<>();
        queue.add(new Triple<>(red, blue, 0));

        while (!queue.isEmpty()) {
            red = queue.peek().a;
            blue = queue.peek().b;
            int cnt = queue.poll().c;

            if (cnt > 10) { // 종료 조건 1
                break;
            } else if (red.findHoll()) { // 종료 조건 2
                System.out.println(cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                /*
                    R,B가 기울이는 과정에서 부딪히는 경우에 주의 ... a
                    특히 예제 입력 7에 주의
                 */
                Marble nRed = red.move(i, blue);
                Marble nBlue = blue.move(i, nRed);
                nRed = nRed.move(i, nBlue); // 순서 구분할 필요 없이 그저 R을 한번 더 굴려서 a문제 해결

                if (visited[nRed.r][nRed.c][nBlue.r][nBlue.c]) continue;
                visited[nRed.r][nRed.c][nBlue.r][nBlue.c] = true;

                if (nBlue.findHoll()) continue;
                queue.add(new Triple<>(nRed, nBlue, cnt + 1));
            }
        }

        System.out.println(-1); //

    }
    private static Triple<Marble,Marble,Object> init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        arr = new char[N][];
        Marble red = null, blue = null;
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'R') {
                    red = new Marble(i, j);
                }
                if (arr[i][j] == 'B') {
                    blue = new Marble(i, j);
                }
            }
        }
        br.close();

        return new Triple<>(red, blue, null);
    }


    static class Marble {
        static int[] dr = new int[]{1, -1, 0, 0};
        static int[] dc = new int[]{0, 0, 1, -1};
        int r;
        int c;

        public Marble(int r, int c) {
            this.r = r;
            this.c = c;
        }


        public Marble move(int d, Marble other) {
            if (arr[r][c] == 'O') return this;
            Marble marble = new Marble(r, c);
            while (!isCrashed(marble, other)) {
                marble.moveOne(d);
                if (marble.findHoll()){
                    return marble;
                }
            }
            marble.r -= dr[d];
            marble.c -= dc[d];
            return marble;
        }

        private boolean findHoll() {
            return arr[r][c] == 'O';
        }

        private static boolean isCrashed(Marble marble, Marble other) {
            return arr[marble.r][marble.c] == '#' || marble.equals(other);
        }

        private void moveOne(int d){
            r += dr[d];
            c += dc[d];
        }

        /**
         * 구슬의 위치가 같으면 true 반환하도록 재정의
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Marble marble = (Marble) o;
            return r == marble.r && c == marble.c;
        }
    }

    static class Triple<A,B,C> {
        A a;
        B b;
        C c;

        public Triple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}