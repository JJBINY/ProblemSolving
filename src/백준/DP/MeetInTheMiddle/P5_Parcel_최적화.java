package 백준.DP.MeetInTheMiddle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P5 Parcel
 * https://www.acmicpc.net/problem/16287
 */
public class P5_Parcel_최적화 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int idx =0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        boolean[] dp = new boolean[w + 1];
        for (int i = 0; i < n; i++) {
            if(i>0) {
                for (int j = 0; j < i-1; j++) {
                    int sum = arr[i - 1] + arr[j];
                    if (sum > w) continue;
                    dp[sum] = true;
                }
            }

            for (int j = i+1; j < n; j++) {
                int sum =  arr[i] + arr[j];
                if (sum > w) continue;
                if(dp[w-sum]){
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");

    }


}