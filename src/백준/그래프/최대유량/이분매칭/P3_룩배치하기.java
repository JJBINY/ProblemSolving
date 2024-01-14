package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * P3 룩 배치하기
 * https://www.acmicpc.net/problem/9525
 */
public class P3_룩배치하기 {

    static boolean[] visited = new boolean[10001];
    static int[] assigned = new int[10001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        int[][] rows = new int[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
            idx++;
            for (int j = 0; j < n; j++) {
                if(arr[i].charAt(j) == 'X'){
                    idx++;
                    continue;
                }
                rows[i][j] = idx;
            }
        }

        idx = 0;
        int[][] cols = new int[n][n];
        for (int i = 0; i < n; i++) {
            idx++;
            for (int j = 0; j < n; j++) {
                if(arr[j].charAt(i) == 'X'){
                    idx++;
                    continue;
                }
                cols[j][i] = idx;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(rows[i][j] == 0) continue;
                addEdge(cols[i][j], rows[i][j]);
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
