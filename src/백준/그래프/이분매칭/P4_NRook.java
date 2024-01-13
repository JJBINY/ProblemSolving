package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * P3 N-Rook
 * https://www.acmicpc.net/problem/1760
 */
public class P4_NRook {

    static boolean[] visited = new boolean[10001];
    static int[] assigned = new int[10001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[][] rows = new int[n][m];
        int idx =0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            idx++;
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    idx++;
                }else{
                    rows[i][j] = idx;
                }
            }
        }

        idx = 0;
        int[][] cols = new int[n][m];
        for (int i = 0; i < m; i++) {
            idx++;
            for (int j = 0; j < n; j++) {
                if(arr[j][i] == 2){
                    idx++;
                }else {
                    cols[j][i] = idx;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 1) continue;
                addEdge(cols[i][j],rows[i][j]);
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
