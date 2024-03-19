package 프로그래머스.많은조건분기;

public class Lv2_혼자서하는틱택토 {

    public int solution(String[] board) {
        int o = 0, x = 0;

        for (String s : board) {
            for (char c : s.toCharArray()) {
                if (c == 'O') {
                    o++;
                } else if (c == 'X') {
                    x++;
                }
            }
        }
        if (o != x && o != x + 1) {
            return 0;
        }

        int of = 0, xf = 0;

        for (String s : board) { //가로
            if (s.equals("OOO")) {
                of++;
            } else if (s.equals("XXX")) {
                xf++;
            }
        }

        for (int i = 0; i < 3; i++) { //세로
            if (board[0].charAt(i) != '.' &&
                    board[0].charAt(i) == board[1].charAt(i) &&
                    board[0].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') {
                    of++;
                } else {
                    xf++;
                }
            }
        }

        //대각선1
        if (board[0].charAt(0) != '.' &&
                board[0].charAt(0) == board[1].charAt(1) &&
                board[2].charAt(2) == board[0].charAt(0)) {
            if (board[0].charAt(0) == 'O') {
                of++;
            } else {
                xf++;
            }
        }
        //대각선2
        if (board[2].charAt(0) != '.' &&
                board[2].charAt(0) == board[1].charAt(1) &&
                board[1].charAt(1) == board[0].charAt(2)) {

            if (board[0].charAt(2) == 'O') {
                of++;
            } else {
                xf++;
            }
        }

        if (of * xf > 0) { //동시에 이길 수 없음
            return 0;
        } else if (of > 0 && o == x) { //선공 이긴 경우 o > x
            return 0;
        } else if (xf > 0 && o != x) { // 후공 이긴 경우 o == x
            return 0;
        } else if (xf > 1) { //후공이 동시에 X자를 만들어 이길 수 없음
            return 0;
        }
        return 1;
    }

}