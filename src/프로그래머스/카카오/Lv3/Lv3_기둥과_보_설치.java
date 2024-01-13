package 프로그래머스.카카오.Lv3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lv3_기둥과_보_설치 {
    /*
 해당 명령 수행 시 조건을 만족하는 지 검사
 -> 기둥, 보 따로 검사해야할듯
 예시에서
 (2,1)에 기둥이 존재하려면 (1,1) 혹은 (2,1)에 보가 존재하거나 (2,0)에 기둥이 존재해야함
 (3,2)에 보가 존재하려면 (2,2)와 (4,2)에 보가 존재하거나 (3,1) 혹은 (4,1)에 기둥이 존재해야함

 일반화하면

 2. (x,y)에 기둥이 존재하려면 아래 두 조건중 하나를 만족해야함
 2-1. (x-1,y) 혹은 (x,y)에 보가 존재
 2-2. (x,y-1)에 기둥이 존재
 2-3. y가 0인경우 (바닥이므로)

 3. (x,y)에 보가 존재하려면 아래 두 조건중 하나를 만족해야함
 3-1. (x-1,y)와 (x+1,y)에 보가 존재
 3-2. (x,y-1) 혹은 (x+1,y-1)에 기둥이 존재

 기둥과 보 배열을 따로 유지하여 추가, 삭제 시마다 위 규칙을 만족하는 지 검사
 명령어 [x, y, a, b] = (x,y)에 a(0:기둥, 1:보)를 b(0:삭제, 1:설치)하라

 기둥 지울 때,
 (x,y+1) 윗기둥
 (x-1,y+1), (x,y+1)위 좌우보 검사
 보 지울 때,
 (x,y), (x+1,y)기둥
 (x-1,y), (x+1,y) 보 검사
 */


    static boolean[][] rows;
    static boolean[][] cols;
    static int n;
    public int[][] solution(int n, int[][] build_frame) {

        rows = new boolean[n+1][n+1]; // 보
        cols = new boolean[n+1][n+1]; // 기둥
        this.n = n;
        for (int[] cmd : build_frame) {
            int x = cmd[0];
            int y = cmd[1];
            if(isAboutRow(cmd)){
                if(isAboutCreate(cmd)){
                    createRow(x, y);
                }else{
                    deleteRow(x, y);
                }
            }else{
                if(isAboutCreate(cmd)){
                    createCol(x, y);
                }else{
                    deleteCol(x, y);
                }
            }
        }

        List<int[]> result = new ArrayList<>();
        for (int x = 0; x < n+1; x++) {
            for (int y = 0; y < n+1; y++) {
                if (cols[x][y]) {
                    result.add(new int[]{x, y, 0});
                }
                if (rows[x][y]) {
                    result.add(new int[]{x, y, 1});
                }
            }
        }

        return result.toArray(new int[0][]);
    }

    private static void deleteCol(int x, int y) {
        cols[x][y] = false;
        if(!isSafeCol(x, y +1) || !isSafeRow(x -1, y +1) || !isSafeRow(x, y +1)){
            cols[x][y] = true;
        }
    }

    private static void createCol(int x, int y) {
        cols[x][y] = true;
        if(!isSafeCol(x, y)){
            cols[x][y] = false;
        }
    }

    private static void deleteRow(int x, int y) {
        rows[x][y] = false;
        if(!isSafeRow(x -1, y) || !isSafeRow(x +1, y) || !isSafeCol(x, y) || !isSafeCol(x +1, y)){
            rows[x][y] = true;
        }
    }

    private static void createRow(int x, int y) {
        rows[x][y] = true;
        if(!isSafeRow(x, y)){
            rows[x][y] = false;
        }
    }

    private static boolean isSafeCol(int x, int y) {
        if(x<0 || y<0 || y>n || x>n)return true; // 범위 벗어나는 경우
        if(!cols[x][y]) return true; //없으면 검사할 필요 없음
        if(y == 0)return true;
        if(x == 0) return cols[x][y - 1] || rows[x][y];
        return cols[x][y - 1] || rows[x - 1][y] || rows[x][y];
    }

    private static boolean isSafeRow(int x, int y) {
        if(x<0 || y<0 || y>n || x>n)return true;
        if(!rows[x][y]) return true; //없으면 검사할 필요 없음
        if(x == 0 || x==n-1)return cols[x][y - 1] || cols[x + 1][y - 1];
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    private static boolean isAboutRow(int[] cmd) {
        return cmd[2] == 1;
    }
    private static boolean isAboutCreate(int[] cmd) {
        return cmd[3] == 1;
    }

}
