package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * G2 1493 박스 채우기
 * 그리
 */
public class G2_1493_박스채우기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static long L, W, H;
    static int N;
    static int[] cube;

    static void solve(BufferedReader br) throws IOException {
        init(br);

        //fill box
        long fill = 0;
        long ans = 0;
        long volume = L * W * H;
        for (int degree = N - 1; degree >= 0; degree--) {
            fill*=8; //변의 길이 2배씩 커지고 부피이므로 현재 크기 박스 2^3개로 다음 크기 박스 하나를 채울 수 있다.
            long cnt =(L >> degree) * (W >> degree) * (H >> degree) -fill;
            cnt = Math.min(cnt, cube[degree]);
            fill += cnt;
            ans += cnt;
        }
        if (fill < volume) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        cube = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            cube[size] = cnt;
        }
    }

}