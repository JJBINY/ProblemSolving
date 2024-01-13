package 프로그래머스.카카오.인턴십19;

import java.util.*;

public class 마_호텔방배정 {
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
    public long[] solution(long k, long[] room_number) {

        Map<Long, Long> map = new HashMap<>();
        long[] answer = new long[room_number.length];
        Queue<Long> unassigned = new LinkedList<>();

        for (int i = 0; i < room_number.length; i++) {
            long room = room_number[i];
            while (true) {
                if (!map.containsKey(room)) {
                    answer[i] = room;
                    map.put(room, room + 1);
                    while (!unassigned.isEmpty()) {
                        map.put(unassigned.poll(), room + 1);
                    }
                    break;
                } else {
                    unassigned.add(room);
                    room = map.get(room);
                }
            }
        }
        return answer;
    }
}
