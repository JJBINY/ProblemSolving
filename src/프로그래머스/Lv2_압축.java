package 프로그래머스;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Lv2_압축 {
    int nextIdx;
    List<Integer> answer = new ArrayList<>();
    public int[] solution(String msg) {

        HashMap<String, Integer> dict = new HashMap<>();

        //1. 1자리 단어들로 사전 초기화
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char)('A' + i)), i + 1);
        }
        nextIdx = 27;



        //4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.

        System.out.println(dict);
        findLongestString(dict,msg);
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }


    public void findLongestString(HashMap<String,Integer> dict, String input) {
//        if (dict.containsKey(input)) {
//            return; //이미 사전에 입력이 존재하는 경우
//        }
        int start = 0;

        while (!dict.containsKey(input.substring(start))) {

            StringBuilder sb = new StringBuilder();
            for (int i = start; i < input.length(); i++) {
                sb.append(input.charAt(i));
                String word = sb.toString();
                //2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
                if (!dict.containsKey(word)) {
                    //3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
                    answer.add(dict.get(word.substring(0, word.length() - 1))); // 압축 번호
                    dict.put(word, nextIdx++); //사전 추가
                    start = i;
                    break;
                }
            }
        }
        answer.add(dict.get(input.substring(start)));
    }
}
