package 백준.이분탐색.MeetInTheMiddle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G2 2632 피자판매
 * 누적합, 이분탐색, MITM
 */
public class G2_2632_피자판매 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int S = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];
        int[] brr = new int[N];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            brr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        ans = fillPSumList(S, M, arr, ans, A);
        ans = fillPSumList(S, N, brr, ans, B);

        B.sort(Comparator.comparingInt(b -> b));
        for (int a : A) { // O(N^2 logN) ~= 3*10^6
            int target = S - a;
            ans += upperBound(target, B) - lowerBound(target, B);
        }

        System.out.println(ans);
    }

    private static int fillPSumList(int S, int M, int[] arr, int ans, List<Integer> A) {
        for (int i = 0; i < M; i++) { //O(M^2) ~= 10^6
            int pSum = 0;
            for (int j = 0; j < M -1; j++) {
                pSum += arr[(i+j)% M];
                A.add(pSum);
                if(pSum == S) ans++;
            }
            if(i == 0){
                pSum += arr[M -1];
                A.add(pSum);
                if(pSum== S) ans++;
            }
        }
        return ans;
    }

    private static int lowerBound(int target , List<Integer>list){
        int lo = -1;
        int hi = list.size();
        while (lo+1<hi){
            int mid = (lo+hi)/2;

            if(list.get(mid) <target){
                lo=mid;
            }else{
                hi=mid;
            }
        }
        return hi;
    }

    private static int upperBound(int target , List<Integer>list){
        int lo = -1;
        int hi = list.size();
        while (lo+1<hi){
            int mid = (lo+hi)/2;

            if(list.get(mid) <=target){
                lo=mid;
            }else{
                hi=mid;
            }
        }
        return hi;
    }
}