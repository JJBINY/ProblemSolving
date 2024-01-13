package 프로그래머스.카카오.Lv3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lv3_미로_탈출_명령어 {
    /*
    bfs+그리디

    1.
    - BFS -> DLRU 구함
    - dlru우선순위로 최단경로를 구하면 (dd)(ll), (lll)(uu), (rrr)(uu), (uu)등 과 같이 나옴
    - 각 문자 그룹(dd),(ll)등을 D,L,R,U라고 하자
    2.
    - left = k-최단경로길이
    - left가 홀수인 경우 도달 불가능
    3.
    - 현재 위치에서 D,L만큼 이동
    - 현재 위치에서 move = min(left/2,아래경계까지거리)만큼 sD, sU에 더하고 이동 및 left -= move*2
    - 현재 위치에서 move = min(left/2,왼쪽경계까지거리)만큼 sL, sR에 더하고 이동 및 left -= move*2
    - RL = left/2
    4.
    - D sD L sL RL R sR U sU 순으로 합침

    위 방법 말고 순수 그리디 혹은 우선순위를 부여한 bfs로도 정답 가능(사실 둘 중 하나만 쓰는게 더 깔끔함)
    */
    int sD, sU, sL, sR, RL= 0;
    String D, L, R, U;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String result = bfs(n, m, x, y, r, c, k);
        int left = k - result.length();
        if (result.equals("impossible") || left % 2 != 0) return "impossible";

        getDLRU(result);
        move(n, m, x, y, left);

        StringBuilder sb = new StringBuilder();

        sb.append(D).append("d".repeat(sD))
                .append(L).append("l".repeat(sL))
                .append("rl".repeat(RL))
                .append(R).append("r".repeat(sR))
                .append(U).append("u".repeat(sU));

        return sb.toString();
    }

    public String bfs(int n, int m, int x, int y, int r, int c,int k) {
        int[] dx = new int[]{1, 0, 0, -1}; //d,l,r,u
        int[] dy = new int[]{0, -1, 1, 0};
        String[] direction = new String[]{"d", "l", "r", "u"};
        Queue<Point> queue = new LinkedList<>();
        queue.add(Point.of(x, y, ""));
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.route.length() == k) continue;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if (!visited.add((nx - 1) * m + (ny - 1))) continue; //이미 방문한 적이 있는 경우
                Point np = Point.of(nx, ny, now.route + direction[i]);
                if (nx == r && ny == c) {
                    return np.route;
                }
                queue.add(np);
            }
        }
        return "impossible";
    }

    static class Point {
        int x; //row
        int y; // col
        String route;

        private Point(int x, int y, String route) {
            this.x = x;
            this.y = y;
            this.route = route;
        }

        public static Point of(int x, int y, String route) {
            return new Point(x, y, route);
        }
    }

    private void getDLRU(String result) {
        Pattern pattern = Pattern.compile("(d*)(l*)(r*)(u*)");
        Matcher matcher = pattern.matcher(result);
        matcher.find();
        D = matcher.group(1);
        L = matcher.group(2);
        R = matcher.group(3);
        U = matcher.group(4);
    }

    private void move(int n, int m, int x, int y, int left) {
        x += D.length();
        y -= L.length();

        int move = Math.min(left / 2, n - x);
        sD += move;
        sU += move;
        left -= move * 2;
        x += move;

        move = Math.min(left / 2, y - 1);
        sL += move;
        sR += move;
        left -= move * 2;
        y -= move;

        RL = left/2;
    }

    /*
        k = 12
    . . . . .   str = (dd)(rr)
    . . S . .   left = 8; (dd)dddlr(rr)uuu
    . . . . .
    . . . . E
    . . . . .
    . . . . .
    . . . . .   dd ddd l rr r uuu

    k = 12
    . . . . .   str = (rr)(uuuu)
    . . E . .   left = 6; d(rr)(uuuu)u
    . . . . .   left = 4; drlrl(rr)(uuuu)u
    . . . . .
    . . . . .
    S . . . .
    . . . . .

    k = 11
    . . . . .   left =6; (r)(uuuu)
    . . E . .   left = 4; d(r)(uuuu)u
    . . . . .   left = 2; dlr(r)(uuuu)u
    . . . . .   left = 0; dlrlr(r)(uuuu)u
    . . . . .
    . S . . .
    . . . . .
     */
}
