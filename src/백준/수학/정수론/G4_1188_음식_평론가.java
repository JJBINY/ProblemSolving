package 백준.수학.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G4_1188_음식_평론가
 * 수학, 정수론, 유클리드 호제법
 */
public class G4_1188_음식_평론가 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        System.out.println((M - 1) - (gcd(N, M) - 1));
    }

    static int gcd(int a, int b){
        int r = a%b;
        if(r==0) return b;
        return gcd(b, r);
    }
}