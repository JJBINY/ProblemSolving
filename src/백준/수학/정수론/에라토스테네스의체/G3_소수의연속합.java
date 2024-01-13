package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * G3 소수의 연속합
 * https://www.acmicpc.net/problem/1644
 */
public class G3_소수의연속합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        List<Integer> primes = getPrimes(n);

        int l = 0;
        int r = 0;
        int sum = 0;

        int cnt = 0;
        while (r<primes.size()) {
            if (sum < n) {
                sum += primes.get(r++);
            }

            while (sum > n) {
                sum -= primes.get(l++);
            }

            if(sum == n) {
                cnt += 1;
                sum -= primes.get(l++);
            }
//            System.out.println(sum);
        }
        System.out.println(cnt);
    }

    private static List<Integer> getPrimes(int n) {
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

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }

}