package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G2_2352_반도체_설계
 * LIS(nlogn)
 */
public class G2_2352_반도체_설계 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[N+1];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (lis[ans] < arr[i]) {
                lis[++ans] = arr[i];
            } else {
                int lb = lowerBound(lis, arr[i], ans);
                lis[lb] = Math.min(lis[lb], arr[i]);
            }
        }
        return ans;
    }

    static int lowerBound(int[] arr, int target, int max) {
        int lo = -1;
        int hi = max + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}