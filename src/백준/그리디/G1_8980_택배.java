package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G1 8980 택배
 * 그리디, 정렬
 */
public class G1_8980_택배 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());
        int M = parseInt(br.readLine());
        PriorityQueue<Parcel> pq = new PriorityQueue<>(Comparator
                .comparingInt((Parcel p) -> p.to)
                .thenComparingInt(p -> -p.from));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int cnt = parseInt(st.nextToken());
            pq.offer(new Parcel(from, to, cnt));
        }

        int ans = 0;
        int[] loaded = new int[N];
        while (!pq.isEmpty()) { // O(MN)
            Parcel p = pq.poll();
            int l = 0;
            for (int i = p.to - 1; i >= p.from && l < C; i--) {
                l = Math.max(l, loaded[i]);
            }
            if (l == C) continue;
            int load = Math.min(C - l, p.cnt);
            ans += load;
            for (int i = p.to - 1; i >= p.from; i--) {
                loaded[i] += load;
            }
        }
        return ans;
    }

    static class Parcel {
        int from;
        int to;
        int cnt;

        public Parcel(int from, int to, int cnt) {
            this.from = from;
            this.to = to;
            this.cnt = cnt;
        }
    }
}