package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G3_15711_환상의_짝꿍
 * 수학, 정수론, 소수 판정, 에라토스테네스의 채
 * ref: https://namu.wiki/w/%EA%B3%A8%ED%8A%B8%EB%B0%94%ED%9D%90%20%EC%B6%94%EC%B8%A1
 */
public class G3_15711_환상의_짝꿍 {
    static List<Integer> primes = new ArrayList<>();
    static boolean[] isPrime;
    static int sqrt = (int) Math.sqrt(4 * 1e12);

    public static void main(String[] args) {
        init();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void init() {
        isPrime = new boolean[sqrt + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= sqrt; i++) {
            if (!isPrime[i]) continue;
            primes.add(i);
            for (int j = i * 2; j <= sqrt; j += i) {
                isPrime[j] = false;
            }
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long sum = A + B;

        if (sum < 4) {
            return "NO";
        }

        // 골트바흐의 강한 추측: 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다
        if (sum % 2 == 0) {
            return "YES";
        }

        /*
        주어진 수 x가 홀수인 경우
        1. x의 합을 두 수의 합으로 나타내려면 {짝수}+{홀수} 꼴이어야 한다
        2. 문제에서는 x의 합을 두 소수의 합으로 나타내야 한다
        3. 짝수인 소수는 2가 유일하다
        4. 따라서 x = 2 + {2가 아닌 소수 == 홀수인 소수}여야 한다.
        => x-2가 3이상의 소수인지 검사한다
         */
        if (isPrime(sum - 2)) {
            return "YES";
        }

        return "NO";
    }

    static boolean isPrime(long n) {
        if (n < isPrime.length) return isPrime[(int) n];
        for (var prime : primes) {
            if (n % prime == 0) return false;
        }
        return true;
    }

}