package 프로그래머스.자료구조.스택;

import java.util.*;
import java.util.stream.*;

public class Lv2_괄호회전하기 {

    //자료구조, 스택, 큐
    public int solution(String s) {

        int answer = 0;
        Queue<Character> q = Arrays.stream(s.split(""))
                .map(sc -> sc.charAt(0))
                .collect(Collectors.toCollection(LinkedList::new));


        for (int i = 0; i < s.length() - 1; i++) {
            Stack<Character> stack = new Stack<>();
            Iterator<Character> iter = q.iterator();
            while (iter.hasNext()) {
                char c = iter.next();
                if (!stack.isEmpty() && isCorrect(stack.peek(), c)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }//while
            if (stack.isEmpty()) {
                answer++;
            }
            q.add(q.poll());
        }//for

        return answer;
    }

    public boolean isCorrect(char a, char b) {
        return (a == '[' && b == ']') ||
                (a == '{' && b == '}') ||
                (a == '(' && b == ')');
    }
}