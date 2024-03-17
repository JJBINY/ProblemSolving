package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * G4 1461 도서관
 * 그리디, 정렬
 */
public class G4_1461_도서관 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int M = parseInt(st.nextToken());
        PriorityQueue<Integer> positiveQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> negativeQ = new PriorityQueue<>(Comparator.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = parseInt(st.nextToken());
            if(idx<0){
                negativeQ.offer(-idx);
            }else{
                positiveQ.offer(idx);
            }
        }

        int ans = 0;

        int pMax = positiveQ.isEmpty()?0:positiveQ.peek();
        while (!positiveQ.isEmpty()){
            ans += positiveQ.peek()*2;    
            for (int i = 0; i < M; i++) {
                positiveQ.poll();
                if(positiveQ.isEmpty()){
                    break;
                }
            }
        }
        int nMax = negativeQ.isEmpty() ? 0 : negativeQ.peek();
        while (!negativeQ.isEmpty()){
            ans += negativeQ.peek()*2;
            for (int i = 0; i < M; i++) {
                negativeQ.poll();
                if(negativeQ.isEmpty()){
                    break;
                }
            }
        }
        ans -= pMax > nMax ? pMax : nMax;
        System.out.println(ans);
        //2 11 -> 11 11
        //-39 -37 -29 -28 -6 -> 6 6 29 29 39
    }
}