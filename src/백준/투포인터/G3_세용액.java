package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G3 세용액
 * https://www.acmicpc.net/problem/2473
 * 한가지 용액을 타겟으로 잡고 투포인터로 나머지 두 용액 결정
 */
public class G3_세용액 {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long[] ans = new long[4];
        ans[0] = Long.MAX_VALUE;

        for (int i = 0; i < n-2; i++) {
            int target = -1 * arr[i] ;
            int l = i+1;
            int r = n - 1;
            while (l < r) {
                long sum = arr[r] + arr[l];

                if (Math.abs(sum-target) < ans[0]) {
                    ans[0] = Math.abs(sum-target);
                    ans[1] = i;
                    ans[2] = l;
                    ans[3] = r;
                }

//                System.out.println("l+r+sum = " + l+"," + r +","+ sum);
                if(sum< target){
                    l+=1;
                }else{
                    r-=1;
                }

            }
        }


        System.out.println(String.format("%d %d %d",
                arr[(int)ans[1]], arr[(int)ans[2]], arr[(int)ans[3]]));
    }

}