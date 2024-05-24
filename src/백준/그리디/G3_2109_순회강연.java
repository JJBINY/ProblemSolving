package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;


/**
 * G3_2109_순회강연
 * 그리디, 정렬
 */
public class G3_2109_순회강연 {
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
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(p -> -p[0]));
        for (int i = 0; i < N; i++) {
            pq.offer(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        }
        int[] pay = new int[10_001];
        while (!pq.isEmpty()){
            int[] now = pq.poll();
            int p = now[0];
            int d = now[1];
            for (int i = d; i >0; i--) {
                if(pay[i] == 0){
                    pay[i] = p;
                    break;
                }
            }
        }
        return Arrays.stream(pay).sum();
    }
}