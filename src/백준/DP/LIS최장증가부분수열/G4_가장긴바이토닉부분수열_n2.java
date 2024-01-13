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
public class G4_가장긴바이토닉부분수열_n2 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        for (int i = 0; i < n; i++) {
            int now = arr[i];
            int cnt = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < now) {
                    cnt = Math.max(cnt, lis[j]);
                }
            }
            lis[i] = cnt + 1;
        }

        int[] lds = new int[n];
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int now = arr[i];
            int cnt = 0;
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < now) {
                    cnt = Math.max(cnt, lds[j]);
                }
            }
            lds[i] = cnt + 1;
            ans[i] = lis[i] + lds[i] - 1;
        }

        System.out.println(Arrays.stream(ans).max().getAsInt());
    }
}
