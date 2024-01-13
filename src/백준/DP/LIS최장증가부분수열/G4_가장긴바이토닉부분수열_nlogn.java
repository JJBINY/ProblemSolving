package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G4_가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 */
public class G4_가장긴바이토닉부분수열_nlogn {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];
        int x = 0;
        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            int now = arr[i];
            if (dp[x] < now) {
                x+=1;
                dp[x] =now;
                lis[i] = x;
            }else{
                int lb = lowerBound(dp, now,x);
                dp[lb]=now;
                lis[i]=lb;
            }
        }

        dp = new int[n + 1];
        x=0;
        int[] lds = new int[n];
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int now = arr[i];
            if (dp[x] < now) {
                x += 1;
                dp[x] = now;
                lds[i] = x;
            } else {
                int lb = lowerBound(dp, now,x);
                dp[lb] = now;
                lds[i] = lb;
            }
            ans[i] = lis[i] + lds[i] - 1;
        }
        System.out.println(Arrays.stream(ans).max().getAsInt());
    }

    static int lowerBound(int[] arr, int target,int max){
        int lo = -1;
        int hi = max+1;
        while(lo+1<hi){
            int mid = (lo + hi) / 2;
            if(arr[mid]<target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }
}
