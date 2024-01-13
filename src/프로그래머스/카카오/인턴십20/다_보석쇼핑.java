import java.util.*;
import java.util.stream.Collectors;

class 다_보석쇼핑 {

    Map<String, Integer> map = new HashMap<>();

    public int[] solution(String[] gems) {
        /*

        투포인터
        모든 보석 포함할 때까지 r+
        모든 보석을 포함하는 경우 조건이 만족하는 동안 l-
        위 과정동안 구간의 [l,r]의 길이가 최소인 경우 갱신

        1:45:00 근데 히든테케 틀림

         */

        Set<String> set = Arrays.stream(gems).collect(Collectors.toSet());

        int[] answer = new int[2];
        int l =0;
        int min = Integer.MAX_VALUE;

        for (int r = 0; r < gems.length; r++) {
            map.put(gems[r], map.getOrDefault(gems[r],0) + 1);

            while (isOk(set) && l<gems.length){
                if(r-l < min){
                    min = r-l;
                    answer[0] = l+1;
                    answer[1] = r+1;
                }
                if(map.get(gems[l]) == 1){
                    map.remove(gems[l]);
                }else {
                    map.put(gems[l], map.get(gems[l]) - 1);
                }

                l+=1;
            }
        }
        return answer;
    }

    public boolean isOk(Set set){
        return map.keySet().size() == set.size();
    }
}