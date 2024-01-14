package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P4 노트북의 주인을 찾아서
 * https://www.acmicpc.net/problem/1298
 */
public class P4_노트북의주인을찾아서 {

    static boolean[] visited = new boolean[5001];
    static int[] assigned = new int[5001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            List<Integer> list = edges.getOrDefault(a, new ArrayList<>());
            list.add(b);
            edges.put(a, list);
        }

        //매칭
        int cnt = 0;
        Arrays.fill(assigned, -1);
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            if (match(i)) cnt++;
        }

        System.out.println(cnt);
        br.close();
    }

    static boolean match(int a) {

        for (int b : edges.getOrDefault(a,new ArrayList<>())) {
            if (visited[b]) continue;
            visited[b] = true;
            if (assigned[b] == -1 || match(assigned[b])) {
                assigned[b] = a;
                return true;
            }
        }
        return false;
    }

}
