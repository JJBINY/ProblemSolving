package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 13904 과제
 * 그리디, 정렬
 */
public class G3_13904_과제 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator
                .comparingInt((Pair p) -> -p.w)
                .thenComparingInt(p -> -p.due));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());
            pq.add(new Pair(d, w));
        }

        boolean[] day = new boolean[1001];
        int ans = 0;
        while (!pq.isEmpty()){
            Pair task = pq.poll();
            for (int i = task.due; i > 0; i--) {
                if(!day[i]){
                    day[i] = true;
                    ans += task.w;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
    
    static class Pair{
        int due;
        int w;

        public Pair(int due, int w) {
            this.due = due;
            this.w = w;
        }
    }
}