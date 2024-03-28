package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G5 3020 개똥벌레
 * 이분탐색, 정렬
 */
public class G5_3020_개똥벌레 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] arr = new int[N / 2];
        int[] brr = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            brr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        Arrays.sort(brr);
        int[] hrr = new int[H];
        Arrays.fill(hrr, N);
        for (int h = 0; h < H; h++) {
            hrr[h] -= upperBound(h, arr);
            hrr[H-1-h] -= upperBound(h, brr);
        }

        Arrays.sort(hrr);
        long count = Arrays.stream(hrr).filter(rst -> rst == hrr[0]).count();
        System.out.println(hrr[0]+" "+count);
    }

    static int upperBound(int target, int[] arr) {
        int lo = -1;
        int hi = arr.length;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (arr[mid] <= target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

}