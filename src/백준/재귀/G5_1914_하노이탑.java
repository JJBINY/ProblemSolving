package 백준.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


/**
 * G5 1914 하노이탑
 * 재귀, 큰 수 연산
 */
public class G5_1914_하노이탑 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static StringBuilder sb = new StringBuilder();

    static int N;
    static void solve(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());

        System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE));
        if(N<=20) {
            move(N, 1, 2, 3);
            System.out.println(sb.toString());
        }
    }

    static void move(int n, int A, int B, int C) {
        if(n>1) move(n-1,A,C,B);
        sb.append(A+" "+C).append("\n");
        if(n>1) move(n-1,B,A,C);
    }


}
