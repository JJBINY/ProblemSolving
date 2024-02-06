package 백준.수학.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S1 1850 최대공약수
 * 수학, 정수론, 유클리드 호제법
 */
public class S1_1850_최대공약수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        br.close();

        long gcd =0;
        if(a>b){
            gcd = gcd(a, b);
        }else {
            gcd = gcd(b, a);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gcd; i++) {
            sb.append(1);
        }
        System.out.println(sb.toString());
    }

    static long gcd(long a, long b){
        long r = a%b;
        if(r==0) return b;
        return gcd(b, r);
    }

}