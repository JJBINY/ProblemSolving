package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * P2 비숍2
 * https://www.acmicpc.net/problem/1760
 */
public class P2_비숍2 {

    static boolean[] visited = new boolean[10001];
    static int[] assigned = new int[10001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int n = parseInt(br.readLine());
        int m = parseInt(br.readLine());


        boolean[][] arr = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        int[][] B = new int[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            idx++;
            int r = i - 1;
            int c = -1;
            while (++r < n && ++c < n) {
                if (arr[r][c]) idx++;
                B[r][c] = idx;
            }
            if (i == 0) continue;
            idx++;
            r = -1;
            c = i - 1;
            while (++r < n && ++c < n) {
                if (arr[r][c]) idx++;
                B[r][c] = idx;
            }
        }

        idx = 0;
        int[][] A = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            idx++;
            int r = i + 1;
            int c = -1;
            while (--r >= 0 && ++c < n) {
                if (arr[r][c]) idx++;
                A[r][c] = idx;
            }
            if (i == n - 1) continue;
            idx++;
            r = n;
            c = i;
            while (--r >= 0 && ++c < n) {
                if (arr[r][c]) idx++;
                A[r][c] = idx;
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]) continue;
                addEdge(A[i][j], B[i][j]);
            }
        }

        //매칭
        int cnt = 0;
        Arrays.fill(assigned, -1);
        for (int a = 1; a <= idx; a++) {
            Arrays.fill(visited, false);
            if (match(a)) cnt++;
        }
        System.out.println(cnt);
        br.close();
    }

    private static void addEdge(int a, int b) {
        List<Integer> list = edges.getOrDefault(a, new ArrayList<>());
        list.add(b);
        edges.put(a, list);
    }

    static boolean match(int a) {

        for (int b : edges.getOrDefault(a, new ArrayList<>())) {
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
