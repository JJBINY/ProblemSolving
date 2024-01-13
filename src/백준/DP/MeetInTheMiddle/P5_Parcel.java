package 백준.DP.MeetInTheMiddle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * P5 Parcel
 * https://www.acmicpc.net/problem/16287
 */
public class P5_Parcel {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int idx =0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int sum = arr[i] + arr[j];
                //Map 관련 연산은 평균적으로 O(1)이지만 해시충돌이 많이 일어날 경우 O(N)
                if(sum<=w-3 && !map.containsKey(sum)){
                    map.put(sum, new Pair(i, j));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int target = w - (arr[i] + arr[j]);
                if (target <3 || !map.containsKey(target)) continue;

                Pair pair = map.get(target);
                if(pair.a != i && pair.a != j && pair.b != i && pair.b != j){
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");

    }

    static class Pair{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}