package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * P5_23289_온풍기_안녕
 * 구현, 시뮬레이션
 */
public class P5_23289_온풍기_안녕 {
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
        RxC 격자 판에서 진행

        1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            - 바로 앞부터 부채꼴로 퍼져 나감
            - 벽에 막히면 퍼지지 않음
            - 5->4->3->2->1 만큼 해당 영역 온도를 상승 시킴
            - 바람이 퍼지는 과정은 다음과 같이 이해 가능
                - 만약 오른쪽으로 바람이 나오는 경우 다음과 같은 경로로 바람 퍼짐
                - 우, 상우, 하우
                - 만약 현재 칸 기준 오른쪽 칸과 벽이 있다면 상우, 하우에는 온풍이 퍼질 수 있음
                - 반면 현재 칸 기준으로 위쪽 칸과 벽이 있다면 우, 하우에는 온풍이 퍼지나 상우에는 퍼질 수 없음
                - 재귀적으로 구현하면 될 것으로 보임

        2. 온도가 조절됨
            - 상하좌우 인접한 두 칸 a, b에 대해서 발생
            - 각 칸의 온도를 Ta, Tb라하면 T = [(Ta-Tb)/4]만큼 온도가 조절
                - if Ta>Tb then Ta-=T, Tb+=T
            - 온도 조절 과정은 모든 칸에 대해서 동시에 발생
                - 실수하기 쉬운 부분이니 주의
            - 벽으로 가로막혀 있는 경우 온도 조절이 일어나지 않음

        3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            - 1행, R행, 1열, C열

        4. 초콜릿을 하나 먹는다.
            - **최종적으로 초콜릿을 몇 개 먹었는 지 출력해야함**
            - 100개 이상인 경우 101 출력

        5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.

        제한 사항
        2 ≤ R, C ≤ 20
        1 ≤ K ≤ 1,000
        온풍기는 하나 이상 있고, 온도를 조사해야 하는 칸도 하나 이상 있다.
        0 ≤ W ≤ R×C
        1 < x ≤ R, 1 ≤ y ≤ C (t = 0)
        1 ≤ x ≤ R, 1 ≤ y < C (t = 1)
        온풍기가 있는 칸과 바람이 나오는 방향에 있는 칸 사이에는 벽이 없다.
        온풍기의 바람이 나오는 방향에 있는 칸은 항상 존재한다.
        같은 벽이 두 번 이상 주어지는 경우는 없다.
    */

    static int R, C, K;
    static int[][] arr;
    static boolean[][] isWall;
    static List<Point> targets = new ArrayList<>();
    static List<Heater> heaters = new ArrayList<>();

    static Object solve(BufferedReader br) throws IOException {
        init(br);
        int chocolate = 0;

        while (chocolate <= 100) {
            for (Heater heater : heaters) {
                heater.operate();
            }
            adjustTemp();
            reduceBoundaryTemp();
            chocolate++; // eat chocolate
            if (checkTemp()) {
                return chocolate;
            }
        }
        return chocolate;
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        // 방 정보 입력
        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int d = parseInt(st.nextToken());
                if (d == 5) {
                    // 온도를 조사해야 하는 칸
                    targets.add(new Point(i, j));
                } else if (d > 0) {
                    // 온풍기 및 바람 방향(1부터 4까지 우좌상하)
                    heaters.add(new Heater(i, j, d));
                }
            } // for j
        } // for i

        // 벽 정보 입력
        int W = parseInt(br.readLine()); // 벽의 개수
        isWall = new boolean[R * C][R * C];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());

            // fail point 1 : 열 행 번호 offset으로 -1 넣어주는 것 깜빡함
            int r = parseInt(st.nextToken())-1;
            int c = parseInt(st.nextToken())-1;
            int t = parseInt(st.nextToken()); // 0 : (r, c) 위쪽 벽, 1 : (r, c) 오른쪽 벽 존재

            // 각 포인트 r,c를 직렬화하여 인덱스로 표현
            // ex) 4x4에서 (0,3)은 idx 3, (1,2)는 idx 6
            int a = r * C + c;
            int b;
            if (t == 0) {
                b = (r - 1) * C + c;
            } else {
                b = r * C + c + 1;
            }
            isWall[a][b] = true;
            isWall[b][a] = true;
        }
    }

    static void adjustTemp() {
        int[][] tmp = new int[R][C]; // 온도 이동 과정에서 원래 배열은 불변이어야 하므로 임시 배열 생성
        int[] dr = {0, 1};
        int[] dc = {1, 0};
        // 왼쪽 위부터 오른쪽 아래로 비교해 나갈 경우 각 칸마다 자신의 오른쪽과 아래만 비교하면 됨
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 2; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (isOutOfBound(nr, nc)) continue;

                    shiftTemp(tmp, r, c, nr, nc);
                } // for d
            } // for c
        } // for r

        // 원래 배열에 온도 이동 결과 적용
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += tmp[i][j];
            }
        }
    }

    private static void shiftTemp(int[][] tmp, int r1, int c1, int r2, int c2) {
        if (isWallBetween(r1, c1, r2, c2)) return;
        int shiftAmount = Math.abs(arr[r1][c1] - arr[r2][c2]) / 4;
        if (arr[r1][c1] > arr[r2][c2]) {
            tmp[r1][c1] -= shiftAmount;
            tmp[r2][c2] += shiftAmount;
        } else {
            tmp[r1][c1] += shiftAmount;
            tmp[r2][c2] -= shiftAmount;
        }
    }

    static void reduceBoundaryTemp() {
        for (int r = 0; r < R; r++) {
            if (arr[r][0] > 0) arr[r][0]--;
            if (arr[r][C - 1] > 0) arr[r][C - 1]--;
        }

        // fail point 1 : 모서리 중복되므로 0부터C-1이 아닌 1부터 C-2까지만 순회
        for (int c = 1; c < C-1; c++) {
            if (arr[0][c] > 0) arr[0][c]--;
            if (arr[R - 1][c] > 0) arr[R - 1][c]--;
        }
    }

    static boolean checkTemp() {
        for (Point target : targets) {
            if(arr[target.r][target.c]<K){
                return false;
            }
        }
        return true;
    }

    private static boolean isWallBetween(int r1, int c1, int r2, int c2) {
        int a = r1 * C + c1;
        int b = r2 * C + c2;
        return isWall[a][b];
    }

    private static boolean isOutOfBound(int r, int c) {
        if (r < 0 || r >= R || c < 0 || c >= C) return true;
        return false;
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Heater extends Point {
        static int[] dr = {0, 0, 0, -1, 1};
        static int[] dc = {0, 1, -1, 0, 0};
        int direction; //1:우, 2:좌, 3:상, 4:하
        boolean[][] heated;

        public Heater(int r, int c, int direction) {
            super(r, c);
            this.direction = direction;
        }

        public void operate() {
            heated = new boolean[R][C];
            operate(r + dr[direction], c + dc[direction], 5);
        }

        private void operate(int r, int c, int temp) {
            if (temp == 0) return;
            if (heated[r][c]) return;
            heated[r][c] = true;
            arr[r][c] += temp;

            // 직선 이동
            moveStraight(r, c, temp);
            // 대각선 이동
            moveDiagonal(r, c, temp, true);
            moveDiagonal(r, c, temp, false);
        }

        private void moveStraight(int r, int c, int temp) {
            int nr = r + dr[direction];
            int nc = c + dc[direction];
            if (isOutOfBound(nr, nc)) return;
            if (!isWallBetween(r, c, nr, nc)) {
                operate(nr, nc, temp - 1);
            }
        }

        private void moveDiagonal(int r, int c, int temp, boolean flag) {
            // 벽 체크 먼저
            int nr = r + (flag ? dc[direction] : -dc[direction]);
            int nc = c + (flag ? dr[direction] : -dr[direction]);
            if (isOutOfBound(nr, nc)) return;
            if (!isWallBetween(r, c, nr, nc)) {
                moveStraight(nr, nc, temp);
            }
        }
    }
}