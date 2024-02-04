package 백준.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * S5 25193 곰곰이의 식단 관리
 * 수학
 */
public class S5_25193_곰곰이의식단관리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long count = Arrays.stream(br.readLine().split("")).filter(s -> s.equals("C")).count();
//        System.out.println("count = " + count);
//        System.out.println(Math.ceil((count) / (N - count + 1)));
        System.out.println((int)Math.ceil((double)count / (N - count + 1)));
    }
}