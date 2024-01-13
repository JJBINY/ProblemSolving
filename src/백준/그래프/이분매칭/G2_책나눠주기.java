package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G2 책 나눠주기
 * https://www.acmicpc.net/problem/14725
 */
public class G2_책나눠주기 {

    static boolean[] visited = new boolean[1001];
    static int[] assigned = new int[1001];
    static List<Pair<Integer>> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            //입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            arr = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                arr.add(new Pair<>(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }

            //매칭
            int cnt = 0;
            Arrays.fill(assigned, -1);
            for (int i = 0; i < m; i++) {
                Arrays.fill(visited, false);
                if(match(i)) cnt++;
            }
            System.out.println(cnt);
        }
        br.close();
    }
    static boolean match(int x){
        Pair<Integer> pair = arr.get(x);
        for (int i = pair.a; i <= pair.b; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            if (assigned[i] == -1 || match(assigned[i])) {
                assigned[i] = x;
                return true;
            }
        }
        return false;
    }

    static class  Pair<T>{

        T a;
        T b;

        public Pair(T a, T b) {
            this.a = a;
            this.b = b;
        }
    }

}
