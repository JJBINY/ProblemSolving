package 프로그래머스.카카오.Lv4;

import java.util.HashSet;
import java.util.Set;

public class Lv4_호텔방_배정_이분탐색_효율성실패 {
    /*
        k는 1000억이므로 이분탐색 필수
        n번 방을 원한다고 할 때, 비어있으면 그대로 배치
        이미 배정된 방이면 [n+1, k]에서 비어있으면서 가장 번호가 작은 방 재귀적으로 이분탐색
        mid가 비어있다면 배정방 mid로 갱신
        mid기준 작은 번호로 먼저 이분탐색
        이분탐색 끝난 뒤 mid보다 배정할 방 번호가 작거나 같다면 해당 방 배정
        크다면 mid기준 큰 번호로 다시 이분탐색

        k=10
        배정된 방 1 3 4
        원하는 방 1 참
        -> [2,10] 이분탐색 mid 5 빔 기록
        -> [2,4] 이분탐색 mid 3 참
        -> [2,2] 빔 -> 2 배정

        배정된 방 1 2 3 4
        원하는 방 3 참
        -> [4,10] mid 7빔 기록
        -> [4,6] mid 5빔 기록
        -> [4,4] 참 -> 5배정

        배정된 방 1 2 3 4 5
        원하는 방 1 참
        -> [2,10] mid 6빔 기록
        -> [2,5] mid 3참
        -> [2,2] 참
        -> [4,5] mid 4참
        -> [5,5] 참 -> 6배정

        여기서 시간을 더 줄이기 위해 해시 적용
        요청받은 번호에 대해 방을 할당 해준 뒤
        K:V = 요청받은 번호 : 비어있는 다음 방
        최대 요청 횟수는 200000번이므로 공간은 충분함
        */

    Set<Long> reserved = new HashSet<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        int idx = 0;

        for (long req : room_number) {
            long assignedNumber = req;
            if(reserved.contains(req)){
                assignedNumber = findRoom(req + 1, k, k);
            }
            reserved.add(assignedNumber);
            answer[idx++] = assignedNumber;
        }


        return answer;
    }
    public long findRoom(long start, long end, long min){
        if(start == end) return min;

        long mid = (start + end)/2;
        if(isEmptyRoom(mid)) min = mid;

        min = findRoom(start, mid - 1, min);
        if(min <= mid) return min;
        return findRoom(mid+1, end, min);
    }

    public boolean isEmptyRoom(long number){
        return !reserved.contains(number);
    }
}