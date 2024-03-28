package 백준.누적합.imos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 3020 개똥벌레
 * 누적합, imos
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
        int[] arr = new int[H];
        for (int i = 0; i < N/2; i++) {
            arr[0]++;
            arr[parseInt(br.readLine())]--;
            arr[H-parseInt(br.readLine())]++;
        }

        for (int i = 1; i < H; i++) {
            arr[i] += arr[i - 1];
        }

        int ans = Integer.MAX_VALUE, cnt = 0;
        for (int i = 0; i < H; i++) {
            if(arr[i]<ans){
                ans = arr[i];
                cnt = 1;
            }else if(arr[i] == ans){
                cnt++;
            }
        }

        System.out.println(ans+" "+cnt);
    }

}