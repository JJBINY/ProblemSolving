package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G1 1450 냅색문제
 * MITM, 투포인터
 */
public class G1_1450_냅색문제 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, C;
    static int[] arr;

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        List<Integer> A = new ArrayList<>();
        func(N / 2, 0, 0, A); // 2^(N/2)
        A.sort(Comparator.comparingInt(i -> i));

        List<Integer> B = new ArrayList<>();
        func(N, N / 2, 0, B);
        B.sort(Comparator.comparingInt(i -> i));

        int a = 0, b = B.size()-1;
        int ans = 0;
        while (a<A.size() && b >=0){
            if(A.get(a)+B.get(b) >C){
                b--;
            }else{
                ans += b + 1;
                a++;
            }
        }

        System.out.println(ans);
    }

    static void func(int end, int idx, int sum, Collection<Integer> results) {
        if (sum > C) {
            return;
        } else if (idx == end) {
            results.add(sum);
            return;
        }
        func(end, idx + 1, sum, results);
        func(end, idx + 1, sum + arr[idx], results);
    }
}