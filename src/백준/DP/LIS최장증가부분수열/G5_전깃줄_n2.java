package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G5 전깃줄
 * https://www.acmicpc.net/problem/2565
 * Longest Increasing Subsequence 응용
 */
public class G5_전깃줄_n2 {
    //1 2 3 4 6 7 9 10 from
    //8 2 9 1 4 6 7 10 to
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Line> lines = new PriorityQueue<>(Comparator.comparing(Line::getFrom));
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a, b));
        }
        int[] sequence = new int[n + 1];
        int idx = 1;
        while (!lines.isEmpty()){
            sequence[idx++] = lines.poll().to;
        }
        int[] dp = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            int now = sequence[i];
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(sequence[j]<now){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }

        System.out.println(n- Arrays.stream(dp).max().getAsInt());
    }

    static class Line{
        int from;
        int to;

        public Line(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }
    }
}
