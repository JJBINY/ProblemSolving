package 백준.수학.정수론.포함배제의원리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * D5_1557_제곱_ㄴㄴ
 * 수학, 정수론, 포함 배제의 원리
 * ref: https://blog.naver.com/yyhjin12/222865860132
 */
public class D5_1557_제곱_ㄴㄴ {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        }
    }

    static Object solve(BufferedReader br) throws IOException {

        int K = Integer.parseInt(br.readLine()); // [1, 10^9]
        int[] mobius = getMobius((int) 1e5);
        long lo = -1, hi = Integer.MAX_VALUE;
        while (lo + 1 < hi) {
            long mid = lo + hi >> 1;

            if (countSFN(mid, mobius) < K) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
    /*
    square free number -> 어떤 소인수도 두 개 이상 가지지 않는다 = 제곱 인수를 가지지 않는다
    뫼비우스 함수 + 포함배제 원리
    => Sn = sigma i <= sqrt(n) -> (u(i)*(n/i^2) 성립
    square free number는 뫼비우스 함수 값이 0이므로 제거됨
     */

    private static long countSFN(long n, int[] mobius) {
        long cnt = 0;
        for (int i = 1; i * i <= n; i++) {
            cnt += (mobius[i] * (n / (i * i)));
        }
        return cnt;
    }

    // 뫼비우스 함수
    private static int[] getMobius(int n) {
        boolean[] isPrime = getIsPrime(n);
        int[] mobius = new int[n + 1];
        Arrays.fill(mobius, 1);
        mobius[0] = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i; j <= n; j += i) {
                mobius[j] *= -1;
                // 제곱 인수를 가지는 수 = 소수 제곱수의 배수
                if ((long) i * j <= n) {
                    mobius[i * j] = 0;
                }
            }
        }
        return mobius;
    }

    // 에라토스테네스의 체
    private static boolean[] getIsPrime(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
}