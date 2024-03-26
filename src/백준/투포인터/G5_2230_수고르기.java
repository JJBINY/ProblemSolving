package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5 2230 수 고르기
 * 투포인터, 정렬
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

        for (int l = 0, r = 0; l < N; l++) {
            int target = arr[l] + M;
            while (r< N && arr[r]<target) r++;
            if(r>=N) break;
            ans = Math.min(ans, arr[r] - arr[l]);
        }
        System.out.println(ans);
    }

}