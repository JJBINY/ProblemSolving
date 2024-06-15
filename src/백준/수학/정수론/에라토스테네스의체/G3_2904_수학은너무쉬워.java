package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * G3_2904_수학은너무쉬워
 * 수학, 정수론, 에라토스테네스의체, 소수판정
 */
public class G3_2904_수학은너무쉬워 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
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
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> primes = getPrimes(Arrays.stream(arr).max().getAsInt());
        // 연산 1번당 결과적으로 소수를 한번 옮기는 것
        int[] res = {1, 0};
        for (int p : primes) {
            int[] cnt = new int[N]; // 소수 개수
            int avgC = 0;
            for (int i = 0; i < N; i++) {
                while (arr[i]%p == 0){
                    arr[i] /= p;
                    cnt[i]++;
                }
                avgC += cnt[i];
            }
            avgC /= N;
            if(avgC>0) {
                res[0] *= Math.pow(p, avgC);
            }
            for (int c : cnt) {
                res[1] += avgC > c ? avgC - c : 0;
            }
        }
        return res[0] + " " + res[1];
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