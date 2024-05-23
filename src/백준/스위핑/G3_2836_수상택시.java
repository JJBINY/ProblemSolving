package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G3 2836 수상택시
 * 정렬, 스위핑
 */
public class G3_2836_수상택시 {
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
        int M = parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int e = parseInt(st.nextToken());
            if (s < e || e == M) {
                continue;
            }
            pq.add(new int[]{s, e});
        }

        long ans = M;
        while (!pq.isEmpty()){
            int s = pq.peek()[0];
            int e = pq.poll()[1];
            while (!pq.isEmpty()){
                int e2 = pq.peek()[1];
                if(e<=e2 && e2<s){
                    s = Math.max(s, pq.poll()[0]);
                }else{
                    break;
                }
            }
            ans += (s - e) * 2;
        }

        return ans;
    }
}