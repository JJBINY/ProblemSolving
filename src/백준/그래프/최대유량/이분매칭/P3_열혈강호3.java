package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P3 열혈강호3
 * https://www.acmicpc.net/problem/11377
 */
public class P3_열혈강호3 {

    static boolean[] visited = new boolean[1001];
    static int[] assigned = new int[1001];
    static List<List<Integer>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> edge = new ArrayList<>();
            int iter = Integer.parseInt(st.nextToken());
            for (int j = 0; j < iter; j++) {
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

        int cnt2 = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            if (match(i)) cnt2++;
            if (cnt2 == k) break;
        }
        System.out.println(cnt + cnt2);
        br.close();
    }

    static boolean match(int a) {

        for (int b : edges.get(a)) {
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
