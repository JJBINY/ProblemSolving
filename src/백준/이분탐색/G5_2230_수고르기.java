package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5 2230 수 고르기
 * 이분탐색
 */
public class G5_2230_수고르기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int idx = lowerBound(arr[i] + M, i - 1, N, arr);
            if(idx == N) continue;
            ans = Math.min(ans,arr[idx]-arr[i]);
        }
        System.out.println(ans);
    }

    static int lowerBound(int target, int lo, int hi, int[] arr) {

        while (lo+1<hi) {
            int mid = (lo + hi) / 2;
            if(arr[mid]<target){
                lo = mid ;
            }else{
                hi = mid;
            }
        }
        return hi;
    }
}