package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 20922 겹치는 건 싫어
 * 투포인터
 */
public class S1_20922_겹치는건싫어 {

    static int[] cntArr = new int[100_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int l = 0, r = 1;
        int ans = 0;
        int len = 1;
        plus(arr[0]);
        while (r<N) {
            while (r<N && getCnt(arr[r]) < K) {
                plus(arr[r++]);
                len++;
            }
            ans = Math.max(ans, len);
            while (r<N && arr[l] != arr[r]){
                minus(arr[l++]);
                len--;
            }
            if(r<N && arr[l] == arr[r]){
                minus(arr[l++]);
                len--;
            }
        }

        System.out.println(ans);
    }
    static int getCnt(int n){
        return cntArr[n];
    }
    static void plus(int n){
        cntArr[n]++;
    }
    static void minus(int n){
        cntArr[n]--;
    }
}
