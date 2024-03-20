package 백준.그래프.DFS.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * G_4 스도쿠
 * https://www.acmicpc.net/problem/2239
 */
public class G4_스도쿠 {
    /*
        00 01 02
        10 11 12
        20 21 22
         */
    static List<List<Set<Integer>>> subSquares = new ArrayList<>();
    static String[][] board = new String[9][];
    static int[] rows = new int[9];
    static int[] cols = new int[9];
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 3; i++) {
            List<Set<Integer>> sets = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                sets.add(new HashSet<>());
            }
            subSquares.add(sets);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = 0; r < 9; r++) {
            board[r] = br.readLine().split("");
            for (int c = 0; c < 9; c++) {
                if(!board[r][c].equals("0")){
                    int num = Integer.parseInt(board[r][c]);
                    subSquares.get(r/3).get(c/3).add(num);
                    rows[r] |= 1 << num;
                    cols[c] |= 1 << num;
                }
            }
        }

        sudoku(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static boolean sudoku(int idx){
        if(idx >= 81){
            return true;
        }

        int r = idx/9;
        int c = idx%9;

        if(!board[r][c].equals("0")){
            if(sudoku(idx + 1)){
                return true;
            }
            return false;
        }

        for (int i = 1; i < 10; i++) {

            if(check(r,c,i)){
                subSquares.get(r/3).get(c/3).add(i);
                rows[r] |= 1 << i;
                cols[c] |= 1 << i;

                if(sudoku(idx + 1)){
                    board[r][c] = "" + i;
                    return true;
                }
                subSquares.get(r/3).get(c/3).remove(i);
                rows[r] &= ~(1 << i);
                cols[c] &= ~(1 << i);

            }
        }

        return false;
    }

    private static boolean check(int r, int c, int num){
        boolean square = !subSquares.get(r/3).get(c/3).contains(num);

        boolean row = ((rows[r] >> num)&1) == 0;
        boolean col = ((cols[c] >> num)&1) == 0;
        return square && row && col;
    }
}