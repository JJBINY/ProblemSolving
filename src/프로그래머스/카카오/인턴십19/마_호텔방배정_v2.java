package 프로그래머스.카카오.인턴십19;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 마_호텔방배정_v2 {
    /*

    00:05:00종료
    유니온 파인드
    요구 1 3 4 1 3 1
    배정 1 3 4 2 5 6

    빈방 1 2 3 4 5 6
    0- 1 2 3 4 5 6
    1- 2 2 3 4 5 6
    3- 2 2 4 4 5 6
    4- 2 2 5 5 5 6
    1- 5 5 5 5 5 6
    3- 6 6 6 6 6 6
    1- 7 7 7 7 7 7
     */

    Map<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = find(room_number[i]);
        }
        return answer;
    }

    public long find(long x) {
        if(!map.containsKey(x)){
            map.put(x, x + 1);
            return x;
        }
        map.put(x, find(map.get(x))+1);
        return map.get(x)-1;
    }
}
