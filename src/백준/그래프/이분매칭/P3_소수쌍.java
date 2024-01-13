package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P3 소수 쌍
 * https://www.acmicpc.net/problem/1017
 */
public class P3_소수쌍 {

    static boolean[] visited = new boolean[2001];
    static int[] assigned = new int[2001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();
    static Set<Integer> primeSet = Set.copyOf(getPrimes(2000));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        int first = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n - 1; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (number % 2 == 0) {
                even.add(number);
            } else {
                odd.add(number);
            }
        }

        List<Integer> l1, l2;
        if (first % 2 == 0) {
            l1 = even;
            l2 = odd;
        } else {
            l1 = odd;
            l2 = even;
        }

        if(l1.size()+1 != l2.size()){
            System.out.println(-1);
            return;
        }

        for (Integer a : l1) {
            for (Integer b : l2) {
                if (isPrime(a + b)) {
                    List<Integer> list = edges.getOrDefault(a, new ArrayList<>());
                    list.add(b);
                    edges.put(a, list);
                }
            }
        }
        l2.sort(Comparator.comparingInt(b -> b));

        //매칭
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (Integer b : l2) {
            if (!isPrime(first + b)) continue;

            Arrays.fill(assigned, -1);
            assigned[b] = first;
            if (!isMatchable(l1)) continue;

            sb.append(b).append(" ");
            cnt += 1;
        }


        if (cnt == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb.toString());
        }
        br.close();
    }

    private static boolean isMatchable(List<Integer> l1) {

        for (Integer a : l1) {
            Arrays.fill(visited, false);
            if (!match(a)) {
                return false;
            }
        }

        return true;
    }

    static boolean match(int a) {

        for (int b : edges.getOrDefault(a, new ArrayList<>())) {
            if (visited[b]) continue;
            visited[b] = true;
            if (assigned[b] == -1 || match(assigned[b])) {
                assigned[b] = a;
                return true;
            }
        }
        return false;
    }

    static boolean isPrime(int p) {
        return primeSet.contains(p);
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
