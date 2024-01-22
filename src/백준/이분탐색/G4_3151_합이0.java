package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 3151 합이0
 * 이분탐색
 * 정답 범위가 int범위 넘길 수 있음에 주의
 */
public class G4_3151_합이0 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        // -6 -5 -4 -4 0 1 2 2 3 7
        long cnt = 0;
        for (int i = 0; i < N-2; i++) {
            for (int j = i+1; j < N-1; j++) {
                int target = -(arr[i] + arr[j]);
                int ub = upperBound(j, N, arr, target);
                int lb = lowerBound(j, N, arr, target);
                cnt += ub-lb;
            }
        }
        System.out.println(cnt);
        br.close();

    }

    private static int lowerBound(int s, int e, int[] arr, int target) {
        int lo = s;
        int hi = e;
        while (lo+1<hi){
            int mid =(lo+hi)/2;
            if(arr[mid]< target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }

    private static int upperBound(int s, int e, int[] arr, int target) {
        int lo = s;
        int hi = e;
        while (lo+1<hi){
            int mid =(lo+hi)/2;
            if(arr[mid]<= target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }
}
