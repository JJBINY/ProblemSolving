package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * G5_6198_BadHairDay
 * 스택
 */
public class G5_6198_BadHairDay {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        long res = 0;
        Deque<Cow> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek().size <= size){
                res += i - stack.pop().idx - 1;
            }
            stack.push(new Cow(i, size));
        }
        while (!stack.isEmpty()){
            res += N - stack.pop().idx - 1;
        }

        return res;
    }

    static class Cow{
        int idx;
        int size;

        public Cow(int idx, int size) {
            this.idx = idx;
            this.size = size;
        }
    }
}