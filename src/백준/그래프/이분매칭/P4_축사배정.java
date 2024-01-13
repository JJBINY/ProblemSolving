package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P4 축사배정
 * https://www.acmicpc.net/problem/2188
 */
public class P4_축사배정 {

    static boolean[] visited = new boolean[1001];
    static int[] assigned = new int[1001];
    static List<List<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            List<Integer> edge = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                edge.add(Integer.parseInt(st.nextToken()));
            }
            edges.add(edge);
        }

        //매칭
        int cnt = 0;
        Arrays.fill(assigned, -1);
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            if (match(i)) cnt++;
        }
        System.out.println(cnt);

        br.close();
    }

    static boolean match(int x) {

        for (int b : edges.get(x)) {
            if (visited[b]) continue;
            visited[b] = true;
            if (assigned[b] == -1 || match(assigned[b])) {
                assigned[b] = x;
                return true;
            }
        }
        return false;
    }

}
