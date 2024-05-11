package SWEA.자료구조.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * D2 1859 백만 장자 프로젝트
 */
public class D2_1859_백만장자프로젝트 {

    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append("#").append(i).append(" ");
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stack.push(parseInt(st.nextToken()));
        }

        int cost = stack.pop();
        long profit = 0;
        while (!stack.isEmpty()){
            if(cost>stack.peek()){
                profit += cost - stack.pop();
            }else{
                cost = stack.pop();
            }
        }

        return profit;
    }
}