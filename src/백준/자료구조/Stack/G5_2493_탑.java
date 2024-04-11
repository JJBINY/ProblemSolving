package 백준.자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 2493 탑
 * 스택
 */
public class G5_2493_탑 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] heights = new int[N+1];
        for (int i = 1; i <= N; i++) {
            heights[i] = parseInt(st.nextToken());
        }
        int[] receivers = new int[N + 1];
        Deque<Pair> stack = new ArrayDeque<>();

        for (int i = N; i > 0; i--) {
            int height = heights[i];
            while (!stack.isEmpty() && height>= stack.peek().height){
                receivers[stack.pop().id] = i;
            }
            stack.push(new Pair(i, heights[i]));
        }
        while (!stack.isEmpty()){
            receivers[stack.pop().id] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(receivers[i + 1]).append(" ");
        }
        System.out.println(sb.toString());
    }

    static class Pair{
        int id;
        int height;

        public Pair(int id, int height) {
            this.id = id;
            this.height = height;
        }
    }
}
