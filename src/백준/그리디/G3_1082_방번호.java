package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G3 1082 방 번호
 * 그리디
 */
public class G3_1082_방번호 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {

        int N = parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = parseInt(br.readLine());
        if(N==1){
            System.out.println(0);
            return;
        }

        int minPrice = 51;
        int fm = -1;
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
            if (i > 0 && arr[i] <= minPrice) {
                fm = i;
                minPrice = arr[i];
            }
        }

        int[] ans = new int[M];
        Arrays.fill(ans, -1);

        int total = 0;
        int idx = 0;
        // 1. 자릿수를 가장 길게
        if (arr[0] < minPrice) {
            ans[idx++] = fm;
            total += arr[fm];
            fm = 0;
        }

        while (idx<M && total + arr[fm] <= M) {
            ans[idx++] = fm;
            total += arr[fm];
        }

        // 2. 앞자리 수부터 가장 큰 수로 채우기
        for (int i = 0; i < idx; i++) {
            for (int j = N-1; j >= 0; j--) {
                if(total-arr[ans[i]] + arr[j] <=M){
                    total = total-arr[ans[i]] + arr[j];
                    ans[i] = j;
                    break;
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < idx; i++) {
            System.out.print(ans[i]);
        }
    }
}