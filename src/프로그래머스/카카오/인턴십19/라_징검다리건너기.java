package 프로그래머스.카카오.인턴십19;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class 라_징검다리건너기 {
    /*
    1시간
    00:32:42 종료

    DP문제
    stones배열 최대 4*200 = 800kb
    dp로 풀기엔 메모리가 부족함

    친구들 한명 지나갈 때마다 시뮬레이션 돌리기에도 시간 부족함
    20만 개를 최악의 경우 20억번 검사해야함

    L출발지점 R 도착지점
    2 4 5 3 2 1 4 2 5 1
    --------------------
    2
    (2) 4
    (2 4) 5

    (2 4 5) 3 - 5
    2 (4 5 3) 2  - 5 3
    2 4 (5 3 2) 1 - 5 3 2
    2 4 5 (3 2 1) 3 - 3 2 1
    2 4 5 3 (2 1 3) 2 - 3
    2 4 5 3 2 (1 3 2) 3 - 3 2
    2 4 5 3 2 1 (3 2 3) 1 - 3 3
    2 4 5 3 2 1 3 (2 3 1) -> ans = 3 - 3

    현재 위치를 n이라 할 때,
     [n-k,n) 중 최대값 x와 n의 값을 비교해서 작은 게 현재 위치 n까지 도달할 수 있는 인원의 수
     */
    public int solution(int[] stones, int k) {


        int[] ans = new int[stones.length];
        Deque<Value> deque = new LinkedList();
        deque.addLast(new Value(-1, Integer.MAX_VALUE));
        for (int i = 0; i < stones.length; i++) {
            if(deque.peek().idx< i-k) deque.pollFirst();

            ans[i] = Math.min(stones[i], deque.peekFirst().val);

            while (deque.peekLast().val < ans[i]) deque.pollLast();
            deque.addLast(new Value(i, ans[i]));
        }

        int answer = 0;
        for (int i = 0; i < k; i++) {
            answer = Math.max(answer,ans[stones.length-k+i]);
        }

        return answer;
    }

    class Value{
        int idx;
        int val;

        public Value(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
