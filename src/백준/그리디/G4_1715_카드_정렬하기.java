package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


/**
 * G4 1715 카드 정렬하기
 * 그리디, 정렬
 */
public class G4_1715_카드_정렬하기 {
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
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int ans = 0;
        while (pq.size()>1){
            int cnt = pq.poll() + pq.poll();
            ans += cnt;
            pq.offer(cnt);
        }
        return ans;
    }
}