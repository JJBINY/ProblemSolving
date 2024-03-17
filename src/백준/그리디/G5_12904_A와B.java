package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;


/**
 * G5 12904 A와B
 * 그리디, 문자열, 구현,
 */
public class G5_12904_A와B {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        String S = br.readLine();
        String T = br.readLine();

        boolean reversed = false;
        Deque<Character> deque = Arrays.stream(T.split("")).map(s -> s.charAt(0)).collect(Collectors.toCollection(LinkedList::new));
        for (int i = 0; i < T.length() - S.length(); i++) {
            char c = reversed ? deque.pollFirst() : deque.pollLast();
            if (c == 'B') {
                reversed = !reversed;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            sb.append(reversed ? deque.pollLast() : deque.pollFirst());
        }

        System.out.println(sb.toString().equals(S) ? 1 : 0);
    }
}