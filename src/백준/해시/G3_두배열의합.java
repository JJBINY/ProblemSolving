package 백준.해시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * G3 두 배열의 합
 * https://www.acmicpc.net/problem/2143
 */
public class G3_두배열의합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        int n = parseInt(br.readLine());
        int[] arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int m = parseInt(br.readLine());
        int[] arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list1 = getPrefixSums(n, arr1);
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer v : list1) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }

        //arr1,arr2의 모든 부분합을 구한 뒤 target-부분합1 = 부분합2인 값을 이분탐색
        //배열로 구간합 배열 새로 만들면 최대 10^3^2=10^6
        //최악의 경우 답의 크기 10^6 * 10^6 = 10^12 > 2^31 ~= 10^9 이므로 long사용해야함
        long cnt = 0;
        List<Integer> list2 = getPrefixSums(m, arr2);
        for (Integer v : list2) {
            int target = T - v;
            cnt += map.getOrDefault(target, 0);
        }
        System.out.println(cnt);
    }

    private static List<Integer> getPrefixSums(int n, int[] arr1) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;  
            for (int j = i; j < n; j++) {
                sum += arr1[j];
                list1.add(sum);
            }
        }
        return list1;
    }

}