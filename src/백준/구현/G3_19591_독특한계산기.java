package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;


/**
 * G3 19591 독특한 계산기
 * 구현
 */
public class G3_19591_독특한계산기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    static int idx;
    static void solve(BufferedReader br) throws IOException {
        String expression = br.readLine();
        long ans;
        idx = 0;
        // 맨 앞 - 여부
        boolean isNegative = false;
        if(expression.charAt(idx) == '-'){
            isNegative = true;
            idx++;
        }
        //숫자, 연산자 순으로 반복
        long number = getNextNumber(expression);
        if(isNegative){
            number *= -1;
        }


        Deque<Long> numbers = new LinkedList<>();
        Deque<Character> opers = new LinkedList<>();
        numbers.add(number);

        while (idx < expression.length()) {
            opers.add(expression.charAt(idx++));
            numbers.add(getNextNumber(expression));
        }


        //numbers <=2 이면 걍 연산
        while (numbers.size() >1) {
            int p1 = getPriority(opers.peekFirst());
            int p2 = getPriority(opers.peekLast());
            if (p1 > p2) {
                numbers.addFirst(operate(numbers.removeFirst(), numbers.removeFirst(), opers.removeFirst()));
            } else if (p1 < p2) {
                long last = numbers.removeLast();
                numbers.addLast(operate(numbers.removeLast(), last, opers.removeLast()));
            } else if(numbers.size() == 2){
                    numbers.add(operate(numbers.remove(), numbers.remove(), opers.remove()));
            }else {
                long first = numbers.removeFirst();
                long last = numbers.removeLast();
                long v1 = operate(first, numbers.peekFirst(), opers.peekFirst());
                long v2 = operate(numbers.peekLast(),last, opers.peekLast());
                if (v1 >= v2) {
                    numbers.removeFirst();
                    opers.removeFirst();
                    numbers.addFirst(v1);
                    numbers.addLast(last);
                } else {
                    numbers.removeLast();
                    opers.removeLast();
                    numbers.addLast(v2);
                    numbers.addFirst(first);
                }
            }
        }
//        System.out.println(operate(numbers.remove(),numbers.remove(),opers.remove()));
        System.out.println(numbers.remove());
    }

    private static long getNextNumber(String expression) {
        int start = idx;
        while (idx < expression.length() && expression.charAt(idx) >= '0' && expression.charAt(idx) <= '9') {
            idx++;
        }
        return Long.parseLong(expression.substring(start, idx));
    }

    private static long operate(long a, long b, char oper){
        if(oper == '+'){
            return a + b;
        }else if(oper == '-'){
            return a - b;
        }else if(oper == '*'){
            return a*b;
        }else if(oper == '/'){
            return a / b;
        }else{
            throw new IllegalArgumentException();
        }
    }

    private static int getPriority(char oper){
        if(oper == '+' || oper == '-'){
            return 1;
        } else if(oper == '*' || oper == '/'){
            return 2;
        }else{
            throw new IllegalArgumentException();
        }
    }

}