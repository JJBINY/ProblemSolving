package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Integer.parseInt;


/**
 * G3 15926 현욱은 괄호왕이야!!
 * 스택
 */
public class G3_15926_현욱은괄호왕 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        int n = parseInt(br.readLine());
        String str = br.readLine();
        int open = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] arr = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                continue;
            }

            if (!stack.isEmpty()) {
                arr[i] = true;
                arr[stack.pop()] = true;
            }
        } // for str
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i]){
                cnt++;
                ans = Math.max(ans, cnt);
            }else{
                cnt = 0;
            }
        }
        System.out.println(ans);
    }
}
/*
8
(())((()
ans : 4
 */