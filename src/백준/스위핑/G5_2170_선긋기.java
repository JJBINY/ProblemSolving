package 백준.스위핑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 2170 선 긋기
 * 정렬, 스위핑
 */
public class G5_2170_선긋기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = parseInt(st.nextToken());
            arr[i][1] = parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int l = arr[0][0];
        int r = arr[0][1];
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i][0] > r) {
                ans += r - l;
                l = arr[i][0];
            }
            r = Math.max(r, arr[i][1]);
        }
        ans += r - l;
        System.out.println(ans);
    }
}