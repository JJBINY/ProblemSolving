package SWEA.자료구조.Queue_Deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;


/**
 */
public class D3_1225_암호생성기 {

    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 10;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        br.readLine();
        Deque<Integer> dq = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int[] cycle = {1, 2, 3, 4, 5};
        int d = 0;
        while (dq.peekLast() > 0) {
            dq.offerLast(Math.max(0, dq.pollFirst() - cycle[d]));
            d = (d + 1) % 5;
        }

        StringBuilder sb = new StringBuilder();
        dq.stream().forEach(i->sb.append(i).append(" "));
        return sb;
    }
}