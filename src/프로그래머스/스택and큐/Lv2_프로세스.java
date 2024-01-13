package 프로그래머스.스택and큐;

import java.util.*;

public class Lv2_프로세스 {

    /**
     * 1. 실행 대기 큐(Queue)에서 대기중인 프로세스 하나를 꺼냅니다.
     * 2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣습니다.
     * 3. 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스를 실행합니다.
     * 3.1 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료됩니다.
     */
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Process> queue = new LinkedList<>();
        for (int loc = 0; loc < priorities.length; loc++) {
            queue.add(new Process(loc, priorities[loc]));
        }

        int[] sortedPriorities = Arrays.stream(priorities).sorted().toArray();

        int cnt = 0;
        while (!queue.isEmpty()) {
            Process target = queue.poll();
            if (target.priority != sortedPriorities[priorities.length-1-cnt]) {
                queue.add(target);
                continue;
            }

            cnt++;
            if (target.number == location) {
                answer = cnt;
                break;
            }


        }

        return answer-1;
    }

    public class Process {
        int number;
        int priority;

        public Process(int number, int priority) {
            this.number = number;
            this.priority = priority;
        }
    }
}
