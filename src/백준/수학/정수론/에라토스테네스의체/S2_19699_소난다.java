package 백준.수학.정수론.에라토스테네스의체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * S2_19699_소난다
 * 소수판정, 조합, 브루트포스
 */
public class S2_19699_소난다 {

    private static int N, M;
    private static int[] weights;
    private static boolean[] isPrime;
    private static TreeSet<Integer> resultSet = new TreeSet<>();

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        weights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int maxSum = Arrays.stream(weights).sum();
        isPrime = initIsPrime(maxSum);
        func(0, 0, 0);

        if (resultSet.isEmpty()) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iter = resultSet.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next()).append(" ");
        }
        return sb;
    }

    private static void func(int idx, int selected, int sum) {
        if (idx == N) {
            if (selected == M && isPrime[sum]) {
                resultSet.add(sum);
            }
            return;
        }

        func(idx + 1, selected, sum);
        if (selected < M) {
            func(idx + 1, selected + 1, sum + weights[idx]);
        }

    }

    private static boolean[] initIsPrime(int n) {
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
}