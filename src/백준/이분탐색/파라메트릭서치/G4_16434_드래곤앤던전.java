package 백준.이분탐색.파라메트릭서치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 16434 드래곤 앤 던전
 * 구현, 이분탐색, 파라메트릭서치
 */
public class G4_16434_드래곤앤던전 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int N;
    static int[][] arr;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        int ap = parseInt(st.nextToken());
        arr = new int[N][3]; // t,a,h
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = parseInt(st.nextToken());
            int a = parseInt(st.nextToken());
            int h = parseInt(st.nextToken());
            arr[i][0] = t;
            arr[i][1] = a;
            arr[i][2] = h;
        }

        long lo = 0;
        long hi = Long.MAX_VALUE; // > 123,456 * 10^6 * (10^6-1)
        while(lo+1<hi){
            long mid = (lo + hi) / 2;

            if(possible(mid, ap)){
                hi = mid;
            }else{
                lo = mid;
            }
        }

        return hi;
    }

    private static boolean possible(long maxHp, long ap) {
        long hp = maxHp;
        for (int i = 0; i < N && hp>0; i++) {
            int a = arr[i][1];
            int h = arr[i][2];
            if(arr[i][0] == 2){
                ap += a;
                hp = Math.min(maxHp, hp + h);
                continue;
            }
            if(ap<h) {
                hp -= (long)a * (h / ap);
                if(h%ap == 0){
                    hp += a;
                }
            }
        }

        return hp > 0;
    }
}