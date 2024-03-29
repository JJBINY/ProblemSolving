package 백준.누적합.imos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G5 28018 시간이 겹칠까?
 * 누적합, imos
 */
public class G5_28018_시간이겹칠까 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[] arr = new int[1_000_002];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = parseInt(st.nextToken());
            int E = parseInt(st.nextToken());
            arr[S]++;
            arr[E+1]--;
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        int Q = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            sb.append(arr[parseInt(st.nextToken())]).append("\n");
        }
        System.out.println(sb);
    }
}