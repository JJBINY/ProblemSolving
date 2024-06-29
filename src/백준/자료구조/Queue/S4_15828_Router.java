package 백준.자료구조.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;


/**
 * S4_15828_Router
 * 자료구조, 큐
 */
public class S4_15828_Router {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int N = parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        int input;
        while ((input = parseInt(br.readLine())) != -1) {
            if(input == 0){
                q.poll();
            } else if (q.size()<N) {
                q.offer(input);
            }
        }

        if(q.isEmpty()){
            return "empty";
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : q) {
            sb.append(integer).append(" ");
        }
        return sb;
    }
}