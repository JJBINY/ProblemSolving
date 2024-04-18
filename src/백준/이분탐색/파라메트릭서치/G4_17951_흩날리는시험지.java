package 백준.이분탐색.파라메트릭서치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 17951 흩날리는 시험지 속에서 내 평점이 느껴진거야
 * 이분탐색, 파라메트릭서치
 */
public class G4_17951_흩날리는시험지 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int N, K;
    static int[] arr;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int lo = -1;
        int hi = Arrays.stream(arr).sum() + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2; // score
            if (isPossible(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        System.out.println(lo);
    }

    static boolean isPossible(int score) {
        int sum = 0;
        int groupNum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (sum < score){
                continue;
            } else if(++groupNum == K){
                return true;
            }
            sum = 0;
        }
        return false;
    }

}