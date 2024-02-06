package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S2 27497 알파벳 블록
 * 자료구조, Deque, Stack
 */
public class S2_27497_알파벳블록 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        Deque<String> deque = new LinkedList<>();
        Stack<Boolean> stack = new Stack<>();
        while (N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = parseInt(st.nextToken());
            switch (cmd){
                case 1:
                    deque.addLast(st.nextToken());
                    stack.push(true);
                    break;
                case 2:
                    deque.addFirst(st.nextToken());
                    stack.push(false);
                    break;
                case 3:
                    if(stack.isEmpty()) continue;
                    if(stack.pop()) deque.pollLast();
                    else deque.pollFirst();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        br.close();
        if(deque.isEmpty()){
            System.out.println(0);
        }else {
            StringBuilder sb = new StringBuilder();
            deque.stream().forEach(s -> sb.append(s));
            System.out.println(sb.toString());
        }
    }
}