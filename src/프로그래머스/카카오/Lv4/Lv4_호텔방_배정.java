package 프로그래머스.카카오.Lv4;

import java.util.*;

public class Lv4_호텔방_배정 {


    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        int idx = 0;

        for (long req : room_number) {
            answer[idx++] = findEmptyRoom(req);
        }


        return answer;
    }

    Map<Long, Long> map = new HashMap<>();
    public long findEmptyRoom(long req){
        if(map.containsKey(req)){
            long assigned = findEmptyRoom(map.get(req));
            map.put(req, assigned);
            return assigned;
        }else{
            map.put(req, req + 1);
            return req;
        }
    }
}