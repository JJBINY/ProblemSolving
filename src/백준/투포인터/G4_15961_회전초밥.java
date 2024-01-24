package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G4 15961 회전 초밥
 * 투포인터, 슬라이딩 윈도우
 */
public class G4_15961_회전초밥 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        int d = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());
        int coupon = parseInt(st.nextToken());

        int[] cnts = new int[d+1];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(br.readLine());
        }
        int cnt = 1;
        cnts[coupon] = 1;
        for (int i = 0; i < k; i++) {
            int sushi = arr[i];
            if(cnts[sushi] == 0) cnt++;
            cnts[sushi]++;
        }

        int ans = cnt;
        int l = 0;
        int r=k;
        while (l<N-1){ // 원형 큐
            if(--cnts[arr[l]] ==0){cnt--;}
            if(cnts[arr[r]]++ ==0) {cnt++;}

            l+=1;
            r = (r + 1) % N;
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

}
