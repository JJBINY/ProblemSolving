package 백준.수학.정수론.유클리드호제법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * G5_21870_시철이가_사랑한_GCD
 * 분할정복, 유클리드 호제법
 */
public class G5_21870_시철이가_사랑한_GCD {
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

    static int N;
    static int[] arr;
    static Object solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        return func(0, N).sum;
    }

    // [l,r)
    static Pair func(int l, int r){
        if(l+1 == r){
            return new Pair(arr[l], arr[l]);
        }
        int m = l + r >> 1;
        Pair left = func(l, m);
        Pair right = func(m, r);
        int sum = Math.max(left.sum + right.gcd, left.gcd + right.sum);
        return new Pair(gcd(left.gcd, right.gcd), sum);
    }

    static int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        return gcd(b, r);
    }

    static class Pair{
        int gcd;
        int sum;

        public Pair(int gcd, int sum) {
            this.gcd = gcd;
            this.sum = sum;
        }
    }
}