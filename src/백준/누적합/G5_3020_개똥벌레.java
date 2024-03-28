package 백준.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 3020 개똥벌레
 * 누적합
 */
public class G5_3020_개똥벌레 {
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
        int H = parseInt(st.nextToken());
        int[] arr = new int[H+1];
        int[] brr = new int[H+1];
        for (int i = 0; i < N/2; i++) {
            arr[parseInt(br.readLine())]++;
            brr[parseInt(br.readLine())]++;
        }

        for (int i = 1; i <= H; i++) {
            arr[i] += arr[i - 1];
            brr[i] += brr[i - 1];
        }

        int cnt = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < H; i++) {
            int ac = arr[H] - arr[i];
            int bc = brr[H] - brr[H-i-1];
            int collision = ac + bc;
            if(collision<ans){
                ans = collision;
                cnt = 1;
            }else if(collision == ans){
                cnt++;
            }
        }

        System.out.println(ans+" "+cnt);
    }

}