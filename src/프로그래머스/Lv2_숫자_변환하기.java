package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;


public class Lv2_숫자_변환하기 {
    public int solution(int x, int y, int n) {
        int[] table = new int[y + 1];
        int MAX = 1000001;
        Arrays.fill(table, MAX);
        table[x] = 1;
        for (int i = x+1; i <= y; i++) {
            if (i-n >= x) {
                table[i] = Math.min(table[i],table[i-n]+1);
            }
            if (i % 2 == 0) {
                table[i] = Math.min(table[i],table[i/2]+1);
            }
            if (i % 3 == 0) {
                table[i] = Math.min(table[i],table[i/3]+1);
            }
        }

        if(table[y] == MAX){
            return -1;
        }

        return table[y]-1;
    }
    /*
    1111 15
    0111 7
    1000 8
    b01000
     */
}

