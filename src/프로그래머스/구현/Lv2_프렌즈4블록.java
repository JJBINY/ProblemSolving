package 프로그래머스.구현;

import java.util.*;

public class Lv2_프렌즈4블록 {

    //구현
    static int H, W;
    static char[][] arr;
    static int[] dr = {0, 1, 0, 1};
    static int[] dc = {0, 0, 1, 1};

    public int solution(int m, int n, String[] board) {
        H = m;
        W = n;
        arr = new char[H][];
        for (int i = 0; i < H; i++) {
            arr[i] = board[i].toCharArray();
        }

        int answer = 0;
        int cnt;
        while ((cnt = find()) > 0) {
            answer += cnt;
            // for(int i =0;i<H;i++){
            //     for(int j =0;j<W;j++){
            //         System.out.print(arr[i][j]);
            //     }
            //     System.out.println();
            // }
        }


        return answer;
    }

    public int find() {

        boolean[][] isTarget = new boolean[H][W];
        for (int i = 0; i < H - 1; i++) {
            for (int j = 0; j < W - 1; j++) {
                if (arr[i][j] == '.') continue;
                if (isRemovable(i, j)) {
                    for (int k = 0; k < 4; k++) {
                        isTarget[i + dr[k]][j + dc[k]] = true;
                    }
                }
            }//forj
        }//fori
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isTarget[i][j]) cnt++;
            }
        }

        remove(isTarget);
        return cnt;
    }

    public boolean isRemovable(int r, int c) {
        for (int k = 1; k < 4; k++) {
            if (arr[r][c] != arr[r + dr[k]][c + dc[k]]) {
                return false;
            }
        }
        return true;
    }

    public void remove(boolean[][] isTarget) {
        Stack<Character> stack = new Stack<>();
        for (int c = 0; c < W; c++) {
            for (int r = 0; r < H; r++) {
                if (!isTarget[r][c]) stack.push(arr[r][c]);
                arr[r][c] = '.';
            }
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                arr[H - 1 - i][c] = stack.pop();
            }
        }
    }
}