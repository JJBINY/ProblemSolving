package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * G2 2632 피자판매
 * 누적합
 */
public class G2_2632_피자판매 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int S = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];
        int[] brr = new int[N];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            brr[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer,Integer> A = new HashMap<>();
        Map<Integer,Integer> B = new HashMap<>();
        fillPSumList(M, arr, A);
        fillPSumList(N, brr, B);

        int ans = A.getOrDefault(S, 0) + B.getOrDefault(S, 0);
        for (int i = 1; i <= S; i++) {
            ans += A.getOrDefault(i, 0) * B.getOrDefault(S - i, 0);
        }

        System.out.println(ans);
    }

    private static void fillPSumList(int M, int[] arr, Map<Integer,Integer> map) {
        for (int i = 0; i < M; i++) { //O(M^2) ~= 10^6
            int pSum = 0;
            for (int j = 0; j < M -1; j++) {
                pSum += arr[(i+j)% M];
                map.put(pSum, map.getOrDefault(pSum, 0) + 1);
            }
            if(i == 0){
                pSum += arr[M -1];
                map.put(pSum, map.getOrDefault(pSum, 0) + 1);
            }
        }
    }
}