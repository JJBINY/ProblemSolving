package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * G3_1644_소수의_연속합
 * 에라스토테네스의 체, 브루트포스
 */
public class G3_1644_소수의_연속합 {
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
        List<Integer> primes = getPrimes(N);

        int cnt = 0;

        for (int i = 0; i < primes.size(); i++) {
            int sum = 0;
            for (int j = i; sum< N && j < primes.size(); j++) {
                sum += primes.get(j);
                if(sum == N){
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static List<Integer> getPrimes(int N){
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i*i <= N; i++) {
            if(!isPrime[i]) continue;
            for (int j = i*i; j <= N; j+=i) {
                isPrime[j] = false;
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i <=N; i++) {
            if(isPrime[i]) results.add(i);
        }
        return results;
    }
}