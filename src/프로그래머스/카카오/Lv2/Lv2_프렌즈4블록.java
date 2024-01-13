package 프로그래머스.카카오.Lv2;

import java.util.*;


public class Lv2_프렌즈4블록 {
    int[] dy = new int[]{0, 1, 1};
    int[] dx = new int[]{1, 1, 0};
    HashSet<Point> set = new HashSet<>();
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (isFour(board, i, j)) {
                    addSet(board, i, j);
                }
            }
        }

        //내려오는거 체크
//        String[] newBoard = new String[];
        for (int i = n-1; i >= 0; i++) {
            for (int j = m-1; j >=0; j++) {
            }
        }
        return answer;
    }

    public class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public boolean isFour(String[] board, int y, int x) {
        for (int i = 0; i < 4; i++) {
            if (board[y].charAt(x) != board[y + dy[i]].charAt(x + dx[i])) {
                return false;
            }
        }
        return true;
    }
    public void addSet(String[] board, int y, int x) {
        for (int i = 0; i < 4; i++) {
            set.add(new Point(y, x));
        }
    }
}
