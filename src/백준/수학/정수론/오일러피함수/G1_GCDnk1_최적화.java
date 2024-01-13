package 백준.수학.정수론.오일러피함수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * G1 GCD(n,k)=1
 * https://www.acmicpc.net/problem/11689
 * https://ko.wikipedia.org/wiki/%EC%98%A4%EC%9D%BC%EB%9F%AC_%ED%94%BC_%ED%95%A8%EC%88%98
 * https://namu.wiki/w/%EC%98%A4%EC%9D%BC%EB%9F%AC%20%ED%94%BC%20%ED%95%A8%EC%88%98
 * https://www.acmicpc.net/board/view/80616
 */
public class G1_GCDnk1_최적화 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        /*
        n*PI(1-1/p) = result
        n(1-1/p) = n-n/p = n1
        n1(1-1/p1) = n1-n1/p1 = n2
        n2-n2/p2 = n3
        n3-n3/p3 = result
         */
        long result = n;
        for (int prime = 2; prime <= Math.sqrt(n); prime++) {
            if(n%prime == 0) {
                result -= result /prime;
                while (n%prime == 0){
                    n /= prime;
                }
            }
        }
        if(n>1){
            result -= result / n;
        }
        System.out.println(result);
    }

}