package 프로그래머스;

import java.util.HashMap;
import java.util.Map;

public class Lv2_n진수_게임 {
    /*
    1. n진수 숫자 나열
    2. m명 참가 -> m개로 자름
    3. m개로 자른 것 중 p번 째 t개 출력
     */

    public String solution(int n, int t, int m, int p) {

        HashMap<Integer,Character> mapper = new HashMap<>();
        for (int i = 0; i <16 ; i++) {
            if (i > 9) {
                mapper.put(i, (char) ('A' + i - 10));
            }else{
                mapper.put(i, (char)('0'+i));
            }

        }
        int max = m*t;
        StringBuilder numbers = new StringBuilder();
        for (int num = 0; num <= max; num++) {
            StringBuilder sb = new StringBuilder();
            int temp = num;
            while (temp >= n) {
                sb.append(mapper.get(temp % n));
                temp = temp / n;
            }
            sb.append(mapper.get(temp));
            sb.reverse();
            numbers.append(sb);
        }
        System.out.println("numbers = " + numbers);
        String answer = "";
        int idx = p-1;
        for (int i = 0; i < t; i++) {
            answer+=numbers.charAt(idx);
            idx = idx+m;
        }

        return answer;
    }

}
