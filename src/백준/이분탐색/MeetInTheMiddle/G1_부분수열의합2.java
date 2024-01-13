package 백준.이분탐색.MeetInTheMiddle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G1 부분수열의합2
 * https://www.acmicpc.net/problem/1208
 */
public class G1_부분수열의합2 {

    static int[] arr;

    /*
    이분탐색 쓰지 않고 1번째 부분합들을 Key, 등장 횟수를 value로 하는 HashMap 만들고
     2번째 부분합 만들면서 바로 결과 확인하는 방법도 가능
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        dfs(left, 0, 0, n/2);
        dfs(right, 0, n/2, n);
        right.sort(Comparator.comparing(i->i));

//        left.stream().forEach(System.out::print);
//        System.out.println();
//        right.stream().forEach(System.out::print);
//        System.out.println();

        long cnt = 0;
        if(s ==0 ) cnt = -1;// left, right 둘 모두에서 아무것도 고르지 않는 경우 1개 제외
        for (Integer val : left) {
            int target = s - val;
            cnt += upperBound(right,target)- lowerBound(right, target);
        }

        System.out.println(cnt);
    }
    public static void dfs(List<Integer> list, int sum, int now, int last) {
        if(now >= last){
            list.add(sum);
            return;
        }

        //현재 값을 부분수열에 포함시킨다/안시킨다 2가지 경우
        dfs(list, sum+arr[now],now+1,last);
        dfs(list, sum,now+1,last);
    }
    public static int lowerBound(List<Integer> list, int target){
        int lo = -1;
        int hi = list.size();

        while (lo+1<hi){
            int mid = (lo+hi)/2;

            if(list.get(mid) < target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }

    public static int upperBound(List<Integer> list, int target){
        int lo = -1;
        int hi = list.size();

        while (lo+1<hi){
            int mid = (lo+hi)/2;

            if(list.get(mid) <= target){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        return hi;
    }

}