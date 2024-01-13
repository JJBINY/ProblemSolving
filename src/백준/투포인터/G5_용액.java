package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5_용액
 * https://www.acmicpc.net/problem/2467
 */
public class G5_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans = new int[3];
        ans[0] = Integer.MAX_VALUE;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int sum = arr[r] + arr[l];
            if (Math.abs(sum) < ans[0]) {
                ans[0] = Math.abs(sum);
                ans[1] = l;
                ans[2] = r;
            }

            if(sum<0){
                l+=1;
            }else{
                r-=1;
            }

        }
        System.out.println(String.format("%d %d", arr[ans[1]], arr[ans[2]]));
    }
}