package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * G5 2212 센서
 * 그리디, 정렬
 */
public class G5_2212_센서 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted()
                .toArray();

        if(K>=N){
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N-1; i++) {
            pq.offer(arr[i + 1] - arr[i]);
        }
        for (int i = 0; i < K-1; i++) {
            pq.poll();
        }
        System.out.println(pq.stream().mapToInt(i->i).sum());
        /*
         3 / 6 7 8 / 10/ 12 14 15 /18 20
         dist 3d 1 1 2d 2 2d 1 3d 2
         거리 기준 최대값 K-1개 제거
         */

    }
}