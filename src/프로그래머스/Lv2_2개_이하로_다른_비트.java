package 프로그래머스;

import java.util.Arrays;


public class Lv2_2개_이하로_다른_비트 {
    public long[] solution(long[] numbers) {

        for(int i =0; i<numbers.length;i++){
            numbers[i] = function(numbers[i]);
        }
        return numbers;
    }

    public long function(long num){
        StringBuilder sb = new StringBuilder(Long.toBinaryString(num));
        sb.append('2');
        sb.reverse().append('2');
        for (int i = 1; i < sb.length()-1; i++) {
            if(sb.charAt(i) == '0'){
                sb.setCharAt(i,'1');
                sb.setCharAt(i-1,'0');
                break;
            }
        }
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        return Long.parseLong(sb.reverse().toString(),2);
    }
}


