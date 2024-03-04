package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/**
 * G4 25381 ABBC
 * 그리디, 큐
 */
public class G4_25381_ABBC {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        //input
        String s = br.readLine();
        boolean[] used = new boolean[s.length()];


        //BC 먼저 처리
        int ans = operate(s, used, 'B', 'C') + operate(s, used, 'A', 'B');

        System.out.println(ans);
    }

    private static int operate(String s, boolean[] used, char first, char second) {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (used[i]) {
                continue;
            } else if (s.charAt(i) == first) {
                queue.offer(i);
            } else if (s.charAt(i) == second && !queue.isEmpty()) {
                used[queue.poll()] = true;
                cnt++;
            }
        }
        return cnt;
    }
}