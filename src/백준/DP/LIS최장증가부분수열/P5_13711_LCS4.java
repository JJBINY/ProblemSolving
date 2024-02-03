package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * P5 13711 LCS4
 * DP, LCS(nlogn), LIS(nlogn)
 */
public class P5_13711_LCS4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //LCS 문제를 LIS문제로 변환
        Map<Integer, Integer> idxMapper = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            idxMapper.put(B[i], i + 1);
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = idxMapper.get(A[i]);
//            System.out.print(A[i]+" ");
        }

        int[] minValues = new int[N + 1];
        int[] LIS = new int[N + 1];
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            int value = A[i-1];
            if (minValues[cnt] < value) {
                LIS[i] = ++cnt;
                minValues[cnt] = value;
            } else {
                LIS[i] = lowerBound(minValues, value, cnt);
                minValues[LIS[i]] = Math.min(minValues[LIS[i]], value);
            }
        }

        System.out.println(cnt);
    }

    static int lowerBound(int[] arr, int target, int max) {
        int lo = -1;
        int hi = max + 1;
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}