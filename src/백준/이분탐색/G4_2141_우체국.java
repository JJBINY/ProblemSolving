package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G4 2142 우체국
 * 수학, 이분탐색, 누적합
 */
public class G4_2141_우체국 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = parseInt(st.nextToken());
            arr[i][1] = parseInt(st.nextToken());
        }

        // 1. sum(|x-X[i]|*A[i]) -> 그래프를 그려보면 최소가 될 수 있는 x 값은 마을 위치
        // 2. 토크로 생각해보면 축의 위치를 스위핑할 경우 회전 방향이 변하는 지점은 단 하나 뿐
        // 3. 따라서 마을 기준으로 그래프의 좌우 기울기 부호 변하는 마을이 극소값
        // 4. x에 대해 미분하면 A값만 남게됨. 즉, 기울기는 A에 비례
        // 4-1. x기준 왼쪽 마을과 오른쪽 마을의 부호는 다름
        // 4-2 A에 대한 누적합을 사용해 회전방향 판단
        // 5. 왼쪽부터 시작해서 오른쪽으로 x값 옮겨가는 도중에 처음으로 회전방향 바뀌는 경우가 정답
        // 5-1. 왼쪽 누적합이 오른쪽 누적합보다 크거나 같아지는 마을
        // 따라서 A의 누적합을 사용해 극소값 구할 수 있음

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        long[] pSums = new long[N+1];
        for (int i = 1; i <= N; i++) {
            pSums[i] = pSums[i - 1] + arr[i-1][1];
        }
        int lo = -1;
        int hi = N;
        while(lo+1<hi){
            int mid = (lo+hi)/2;

            if(pSums[mid] >= pSums[N]-pSums[mid]){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        return arr[lo][0];
    }
}
/*
4
1 1
2 1
3 1
4 1
 */