package 백준.수학.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * G4 2981 검문
 * 수학, 정수론, 유클리드 호제법
 */
public class G4_2981_검문 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(i>0){
                list.add(Math.abs(arr[i] - arr[i - 1]));
            }
        }

        int M = list.stream().sorted().reduce((i1, i2) -> gcd(i1, i2)).get().intValue();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <=M ; i++) {
            if(M%i==0){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    static int gcd(int a, int b){
        int r = a%b;
        if(r==0) return b;
        return gcd(b, r);
    }
}