package 프로그래머스.카카오.Lv1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Lv1_키패드_누르기 {
    public static void main(String[] args) {
        Lv1_키패드_누르기 solution = new Lv1_키패드_누르기();
//        solution.solution();

    }
    /*
    rc
    00 01 02
    10 11 12
    20 21 22
    30 31 32
    거리 차이는 r+c의 차이
    i -> [0,12), r = i/3, c = i%3

    left, right 거리 같은 경우 주손으로
     */
    public String solution(int[] inputs, String hand) {

        List<Location> keypad = new ArrayList<>();
        keypad.add(Location.of(3, 1));
        for (int i = 0; i < 9; i++) {
            keypad.add(Location.of(i / 3, i % 3));
        }

        Location left = Location.of(3, 0);
        Location right = Location.of(3, 2);

        Set leftSet = Set.of(1, 4, 7);
        Set rightSet = Set.of(3, 6, 9);

        StringBuffer ans = new StringBuffer();
        for (int input : inputs) {
            Location number = keypad.get(input);
            if(leftSet.contains(input)){
                left = number;
                ans.append("L");
            }else if(rightSet.contains(input)){
                right = number;
                ans.append("R");
            }else {
                int ld = left.getDistance(number);
                int rd = right.getDistance(number);

                boolean condition = ld < rd || (ld == rd && hand.equals("left"));
                if (condition) {
                    left = number;
                    ans.append("L");
                } else {
                    right = number;
                    ans.append("R");
                }
            }

        }

        return ans.toString();
    }



    static class Location {
        int row;
        int col;

        private Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public static Location of(int row, int col) {
            return new Location(row, col);
        }

        public int getDistance(Location other){
            return Math.abs(this.col  - other.col) + Math.abs(this.row - other.row);
        }
    }
}
