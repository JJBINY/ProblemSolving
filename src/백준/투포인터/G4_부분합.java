package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G_4 부분합
 * https://www.acmicpc.net/problem/1806
 */
public class G4_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int l =0;
        int r = 0;

        int len = Integer.MAX_VALUE;
        while (r<n){
            if(sum<s){
                sum += arr[r++];
            }
            while(sum >= s){
                len = Math.min(r - l, len);
                sum -= arr[l++];
            }
        }

        System.out.println(len%Integer.MAX_VALUE);

    }
}