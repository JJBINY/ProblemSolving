package 프로그래머스.데브매칭2021;

import java.util.ArrayList;
import java.util.List;

public class 나_행렬테두리회전 {
    public int[] solution(int rows, int columns, int[][] queries) {

        int[][] arr = new int[rows+1][columns+1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                arr[i][j] = num++;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int[] query : queries) {
            int min = Integer.MAX_VALUE;

            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            int temp = arr[r1][c1];


            for (int r = r1; r < r2; r++) {
                arr[r][c1] = arr[r+1][c1];
                min = Math.min(min, arr[r][c1]);
            }
            for (int c = c1; c < c2; c++) {
                arr[r2][c] = arr[r2][c+1];
                min = Math.min(min, arr[r2][c]);
            }
            for (int r = r2; r > r1; r--) {
                arr[r][c2] = arr[r-1][c2];
                min = Math.min(min, arr[r][c2]);
            }
            for (int c = c2; c > c1; c--) {
                arr[r1][c] = arr[r1][c-1];
                min = Math.min(min, arr[r1][c]);
            }

            arr[r1][c1+1] = temp;
            min = Math.min(min, temp);

            answer.add(min);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}