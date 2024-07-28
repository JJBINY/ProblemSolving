package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4_20056_마법사_상어와_파이어볼
 * 구현, 시뮬레이션
 */
public class G4_20056_마법사_상어와_파이어볼 {
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
        0~7 까지 8방향으로 이동
        속력 s만큼 이동
        격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
     */
    static int N, M, K;
    static List<Fireball> fireballs = new ArrayList<>();

    static Object solve(BufferedReader br) throws IOException {
        init(br);
        while (K-- > 0) {
            fireballs.forEach(Fireball::move);
            mergeOverlapped(); // 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
            divideMerged(); // 파이어볼은 4개의 파이어볼로 나누어진다.
        }

        return fireballs.stream().mapToInt(f -> f.m).sum();
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken()); // N x N  격자
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken()) - 1;
            int c = parseInt(st.nextToken()) - 1;
            int m = parseInt(st.nextToken());
            int s = parseInt(st.nextToken());
            int d = parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }
    }

    private static void mergeOverlapped() {
        Iterator<Fireball> iter = fireballs.iterator();
        HashMap<Integer, Fireball> map = new HashMap<>();
        while (iter.hasNext()) {
            Fireball f = iter.next();
            f.cnt = 1;

            if (!map.containsKey(f.getIdx())) {
                map.put(f.getIdx(), f);
                continue;
            }

            Fireball other = map.get(f.getIdx());
            other.s += f.s; // 속도 합치기
            other.m += f.m; // 질량 합치기
            other.cnt++; // 합쳐진 파이어볼 개수 기록
            other.md |= 1 << (f.d % 2); // 합쳐진 파이어볼들의 방향(짝홀) 기록
            iter.remove();
        }
    }

    private static void divideMerged() {
        List<Fireball> divided = new ArrayList<>();
        Iterator<Fireball> iter = fireballs.iterator();
        while (iter.hasNext()) {
            Fireball f = iter.next();
            if (f.cnt == 1) {
                continue;
            }

            int m = f.m / 5;
            if (m == 0) {
                iter.remove();
                continue;
            }

            int r = f.r;
            int c = f.c;
            int s = f.s / f.cnt;

            if (isBiased(f)) {
                divided.add(new Fireball(r, c, m, s, 0));
                divided.add(new Fireball(r, c, m, s, 2));
                divided.add(new Fireball(r, c, m, s, 4));
                divided.add(new Fireball(r, c, m, s, 6));
            } else {
                divided.add(new Fireball(r, c, m, s, 1));
                divided.add(new Fireball(r, c, m, s, 3));
                divided.add(new Fireball(r, c, m, s, 5));
                divided.add(new Fireball(r, c, m, s, 7));
            }
            iter.remove();
        }
        fireballs.addAll(divided);
    }

    /**
     * 합쳐진 파이어볼이 모두 짝수(01) 혹은 홀수(10) 방향인지 검사 (비트마스킹)
     *
     * @param f
     * @return
     */
    private static boolean isBiased(Fireball f) {
        // 가독성을 위해 이진수를 문자열로 표현
        return !Integer.toBinaryString(f.md).equals("11");
    }


    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getIdx() {
            return r * N + c;
        }

        /**
         * 위치가 같은지 검사
         *
         * @param o
         * @return true if o is at the same point, else false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            if (r != point.r) return false;
            return c == point.c;
        }
    }

    static class Fireball extends Point {
        // 방향 순선 : 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
        static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
        int m; // 질량
        int s; // 속력
        int d; // 방향
        int cnt = 1; // 합쳐진 파이어볼의 개수
        int md; // 합쳐진 파이어볼들의 방향 (짝홀만 비트마스킹으로 기록)

        public Fireball(int r, int c, int m, int s, int d) {
            super(r, c);
            this.m = m;
            this.s = s;
            this.d = d;
            md = 1 << (d % 2);
        }

        public void move() {
            // 범위 넘어가는 경우 순환함에 주의
//            r = (r + dr[d] * s + N) % N; // dr[d] * s가 -(N+r) 보다 작은 값일 경우 음수가 됨
//            c = (c + dc[d] * s + N) % N;
            r = (r + (dr[d] * s) + (N * s)) % N;
            c = (c + (dc[d] * s) + (N * s)) % N;
        }
    }
}