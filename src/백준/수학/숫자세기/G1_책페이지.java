package 백준.수학.숫자세기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * G1 책페이지
 * https://www.acmicpc.net/problem/1019
 */
public class G1_책페이지 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[10];

        int digit = 1;
        int remainder = 0;
        while (n > 0) {
            int boundary = n % 10;
            n /= 10;
            cnt[0] -= digit;
            for (int i = 0; i < boundary; i++) {
                cnt[i] += (n + 1) * digit;
            }
            cnt[boundary] += n * digit + 1 + remainder;
            for (int i = boundary + 1; i < 10; i++) {
                cnt[i] += n * digit;
            }
            remainder += boundary * digit;
            digit *= 10;
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(cnt[i] + " ");
        }
    }
}
