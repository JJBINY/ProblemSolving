package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 22862 가장 긴 짝수 연속한 부분 수열 (large)
 * 투포인터
 */
public class G5_22762_가장긴짝수연속부분수열large {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int K = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int l =0;
        int even = 0, odd = 0;
        int ans = 0;
        for (int r = 0; r < N; r++) {
            if(isEven(arr[r])){
                even++;
                ans = Math.max(ans,even);
                continue;
            }else if(odd<K){
                odd++;
                continue;
            }

            while (isEven(arr[l])){
                l++;
                even--;
            }
            l++;
        }
        System.out.println(ans);
    }
    static boolean isEven(int n){
        return n % 2 == 0;
    }
}
