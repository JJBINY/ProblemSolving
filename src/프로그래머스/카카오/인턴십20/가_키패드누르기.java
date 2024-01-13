import java.util.HashMap;
import java.util.Map;

class 가_키패드누르기 {
    // 2:41:46
    public String solution(int[] numbers, String hand) {
        Map<Integer, Point> map = new HashMap<>();
        int idx = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map.put(idx++, new Point(i, j));
            }
        }
        map.put(0, new Point(3, 1));
        Point left = new Point(3, 0);
        Point right = new Point(3, 2);

        hand = hand.substring(0, 1).toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            if(number%3 == 1){
                sb.append('L');
            }
            else if (number%3 ==0 && number != 0) {
                sb.append('R');
            }else {
                //2,5,8,0
                int l = getDist(map.get(number), left);
                int r = getDist(map.get(number), right);
                if (l < r) {
                    sb.append('L');
                } else if (l > r) {
                    sb.append('R');
                } else {
                    sb.append(hand);
                }
            }
            if(sb.charAt(sb.length()-1) == 'L'){
                left = map.get(number);
            }else{
                right = map.get(number);
            }
        }
        
        return sb.toString();
    }

    public int getDist(Point p1, Point p2){
        return Math.abs(p1.c - p2.c) + Math.abs(p1.r - p2.r);
    }

    class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}