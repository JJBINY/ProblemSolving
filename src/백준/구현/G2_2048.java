package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G2 2048
 * https://www.acmicpc.net/problem/12100
 */
public class G2_2048 {

    static int max = 0;
    static int[] dv = new int[]{-1, 1, -1, 1};//이동방향 = 상하좌우 : 0123
    //4^5 경우의 수 시뮬레이션
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
            }
        }

        dfs(board, 0);
        System.out.println(max);

        br.close();
    }


    static private void dfs(int[][] board, int depth){
        if(depth > 4) return;

        for (int i = 0; i < 4; i++) {
            int[][] nextBoard = move(board, i);
            dfs(nextBoard, depth + 1);
        }
    }

    private static int[][] move(int[][] now, int direction) {
        int[][] next = new int[now.length][now.length];
        Queue<Integer> queue = new LinkedList<>();
        int last = 0;
        int first = now.length-1;
        if(direction %2 == 0){
            last = now.length - 1;
            first = 0;
        }

        for (int i = 0; i < now.length; i++) {
            int a = first;
            while (isInRange(last, a,direction)) {
                while (isInRange(last, a,direction) && getValueAt(now,i,a,direction) == 0) {
                    a -= dv[direction];
                }
                if (a == last){
                    if(getValueAt(now,i,a,direction) > 0){
                        queue.add(getValueAt(now,i,a,direction));
                    }
                    break;
                }

                int b = a - dv[direction];
                while (isInRange(last, b,direction) && getValueAt(now,i,b,direction) == 0) {
                    b -= dv[direction];
                }

                if (getValueAt(now,i,a,direction)==getValueAt(now,i,b,direction)) {
                    queue.add(getValueAt(now,i,a,direction) * 2);
                    a = b - dv[direction];
                } else {
                    queue.add(getValueAt(now,i,a,direction));
                    a = b;
                }
                if(a == last){
                    queue.add(getValueAt(now, i, a, direction));
                }
            }
            int j = first;
            addValueAt(direction, queue, i,j, next);
        }
        return next;
    }

    private static void addValueAt(int direction, Queue<Integer> queue, int i, int j, int[][] next) {
        if(direction<2){
            while (!queue.isEmpty()) {
                next[j][i] = queue.poll();
                if(next[j][i] > max){
                    max = next[j][i];
                }
                j -= dv[direction];
            }
        }else{
            while (!queue.isEmpty()) {
                next[i][j] = queue.poll();
                if(next[i][j] > max){
                    max = next[i][j];
                }
                j -= dv[direction];
            }
        }
    }


    private static int getValueAt(int[][] now, int i, int a, int direction){
        if(direction<2){
            return now[a][i];
        }
        return now[i][a];

    }

    private static boolean isInRange(int last, int a, int direction) {
        if(direction %2 == 0){
            return a < last;
        }
        return a > last;
    }
}