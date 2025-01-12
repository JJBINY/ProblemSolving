package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P5 전깃줄 2
 * https://www.acmicpc.net/problem/2568
 */
public class P5_전깃줄2 {
    //1 2 3 4 6 7 9 10 from
    //8 2 9 1 4 6 7 10 to
    //1 1 2 1 2 3 4 5

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Line> lines = new PriorityQueue<>(Comparator.comparing(Line::getFrom));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(b, a);
            lines.add(new Line(a, b));
        }
        int[] sequence = new int[n + 1];
        int idx = 1;
        while (!lines.isEmpty()) {
            sequence[idx++] = lines.poll().to;
        }
        int[] dp = new int[n + 1];
        int[] lis = new int[n + 1];
        int x = 0;
        for (int i = 1; i < n + 1; i++) {
            int now = sequence[i];
            if (lis[x] < now) {
                x += 1;
                dp[i] = x;
                lis[x] = now;
            } else {
                int lb = lowerBound(lis, now, x);
                dp[i] = lb;
                lis[lb] = Math.min(lis[lb], now);
            }
        }
        System.out.println(n - x);

        Set<Integer> set = new HashSet<>();
        for (int i = n; i > 0; i--) {
            if (dp[i] == x) {
                x -= 1;
                set.add(sequence[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (set.contains(sequence[i])) continue;
            sb.append(map.get(sequence[i])+"\n");
        }
        System.out.println(sb.toString());
    }

    static int lowerBound(int[] arr, int target, int max) {
        int lo = -1;
        int hi = max + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    static class Line {
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
/*
9
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6
8 3

    1 2 3 4 6 7 8 9 10 from
    8 2 9 1 4 6 3 7 10 to
    1 1 2 1 2 3 2 4 5 result
 */
