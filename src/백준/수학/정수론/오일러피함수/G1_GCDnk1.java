package 백준.수학.정수론.오일러피함수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * G1 GCD(n,k)=1
 * https://www.acmicpc.net/problem/11689
 * https://ko.wikipedia.org/wiki/%EC%98%A4%EC%9D%BC%EB%9F%AC_%ED%94%BC_%ED%95%A8%EC%88%98
 * https://namu.wiki/w/%EC%98%A4%EC%9D%BC%EB%9F%AC%20%ED%94%BC%20%ED%95%A8%EC%88%98
 * https://www.acmicpc.net/board/view/80616
 */
public class G1_GCDnk1 {

    static List<Integer> primes = getPrimes();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        List<Factor> factors = getPrimeFactorsOf(n);
        long result = 1;
        for (Factor factor : factors) {
            result *= phiFunction(factor);
        }
        System.out.println(result);
    }
    private static long phiFunction(Factor factor){
        long pn = (long) Math.pow(factor.val, factor.degree);
        return pn-pn/ factor.val;
    }

    private static List<Factor> getPrimeFactorsOf(long n) {
        List<Factor> factors = new ArrayList<>();
        for (Integer prime : primes) {
            int cnt =0;
            while (n%prime == 0){
                n /= prime;
                cnt += 1;
            }
            if(cnt>0){
                factors.add(new Factor(prime, cnt));
            }
        }
        if(n>1){
            factors.add(new Factor(n, 1));
        }
        return factors;
    }

    static class Factor{
        long val;
        int degree;

        public Factor(long val, int degree) {
            this.val = val;
            this.degree = degree;
        }
    }

    private static List<Integer> getPrimes() {
        int n = (int) Math.pow(10, 6);
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