package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S5 17504 제리와 톰 2
 *
 */
public class S5_17504_제리와톰2 {

    static long [] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Fraction b = new Fraction(1, arr[N - 1]);
        for (int i = N-2; i >=0 ; i--) {
            b = plus(arr[i], b).createInverse();
        }
        Fraction ans = minus(1, b);
        System.out.println(ans.upper+" "+ans.lower);
    }

    static Fraction plus(long a, Fraction b){
        return new Fraction(a * b.lower + b.upper,b.lower);
    }

    static Fraction minus(long a, Fraction b){
        return new Fraction(a * b.lower - b.upper,b.lower);
    }



    static class Fraction {
        long upper;
        long lower;

        public Fraction(long upper, long lower) {
            this.upper = upper;
            this.lower = lower;
        }

        public Fraction createInverse(){
            return new Fraction(lower, upper);
        }
    }

}