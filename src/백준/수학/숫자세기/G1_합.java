package 백준.수학.숫자세기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G1 합
 * https://www.acmicpc.net/problem/1081
 */
public class G1_합 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        long[] lCnt = getDigitNums(l-1);
        long[] uCnt = getDigitNums(u);
        long ans = 0;
        for (int i = 1; i < 10; i++) {
            ans += i*(uCnt[i] - lCnt[i]);
        }
        System.out.println(ans);
    }

    private static long[] getDigitNums(int n) {
        long[] cnt = new long[10];

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
        return cnt;
    }
}