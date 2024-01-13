package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * P5 카드게임
 * https://www.acmicpc.net/problem/16566
 */
public class P5_카드게임 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();

        parent = IntStream.range(0, m+1).toArray();
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) { // klogm
            int idx = upperBound(cards, Integer.parseInt(st.nextToken()));
            sb.append(cards[find(idx)]+"\n");
            parent[idx] = parent[idx] + 1;
        }
        System.out.println(sb.toString());

    }
    private static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static int upperBound(int[] arr, int target){
        int lo = -1;
        int hi = arr.length;
        while (lo+1<hi){
            int mid = (lo + hi) / 2;
            if(arr[mid]<=target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }
}
