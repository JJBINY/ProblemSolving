package 백준.많은조건분기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * S1 17074 정렬
 * 많은 조건 분기
 */
public class S1_17074_정렬 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }
        br.close();

        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                cnt++;
                idx = i;
            }
        }

        System.out.println(solve(arr,cnt, idx));
    }

    static int solve(int[] arr, int cnt , int idx){
        int cases = 0;

        if (cnt == 0) {
            return arr.length;
        } else if (cnt == 1) {
            if (idx < arr.length - 2 && arr[idx] <= arr[idx + 2]) { //idx+1 버릴 수 있다.
                cases += 1;
            }

            if (idx > 0 && arr[idx - 1] <= arr[idx + 1]) { //idx 버릴 수 있다.
                cases += 1;
            }

            if(idx==arr.length-2 || idx == 0) return cases+1;
        }

        return cases;
    }
}