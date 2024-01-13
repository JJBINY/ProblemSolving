package 프로그래머스.카카오.Lv2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Lv2_거리두기_확인하기 {

    /*
    1. 맨해튼 거리 |r1-r2| + |c1-c2| > 2 => 각 P마다 BFS 수행(2까지만)
    2. X인 경우 막혀서 갈 수 없는 것으로 판단
    3. 맨해튼 거리 2이하인 경우가 하나라도 존재하면 0 없으면 1
     */

    public int[] solution(String[][] places) {

        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        for (int i = 0; i < 5; i++) {
            String[] place = places[i];
            for (int idx = 0; idx < 5 * 5; idx++) {
                int r = idx / 5;
                int c = idx % 5;
                if (place[r].charAt(c) == 'P') {
                    if (!isSafe(place, r, c)) {
                        answer[i] = 0;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    int[] dr = new int[]{1, -1, 0, 0};
    int[] dc = new int[]{0, 0, 1, -1};

    private boolean isSafe(String[] place, int row, int col) {
        Queue<Point> queue = new LinkedList<>();
        Point init = Point.init(row, col);
        queue.add(init);
        int visited = 0 | (1 << init.getIdx());

        while (!queue.isEmpty() && queue.peek().dist < 2) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                Point next = now.next(now.row + dr[i], now.col + dc[i]);

                if (isOutOfBound(next) || isPartition(place, next) || isVisited(visited, next)) {
                    continue;
                }

                visited |= (1 << next.getIdx()); // 방문처리
                if (place[next.row].charAt(next.col) == 'P') {
                    return false;
                }
                queue.add(next);
            }
        }
        return true;
    }

    private boolean isOutOfBound(Point next) {
        return next.row < 0 || next.row >= 5 || next.col < 0 || next.col >= 5;
    }

    private boolean isPartition(String[] place, Point next) {
        return place[next.row].charAt(next.col) == 'X';
    }

    private static boolean isVisited(int visited, Point next) {
        return (visited >> next.getIdx() & 1) == 1;
    }

    static class Point {
        final int row;
        final int col;
        final int dist;

        private Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        public static Point init(int row, int col) {
            return new Point(row, col, 0);
        }

        public Point next(int row, int col) {
            return new Point(row, col, dist + 1);
        }

        public int getIdx() {
            return row * 5 + col;
        }
    }
}
