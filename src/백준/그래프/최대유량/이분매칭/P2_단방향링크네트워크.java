package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * P2 단방향 링크 네트워크
 * https://www.acmicpc.net/problem/3295
 */
public class P2_단방향링크네트워크 {

    /*
    가치 = 선택된 단방향 링크 수 = 매칭 수
     */
    static boolean[] visited = new boolean[10001];
    static int[] assigned = new int[10001];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int T = parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- >0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = parseInt(st.nextToken());
            int m = parseInt(st.nextToken());

            edges.clear();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                addEdge(parseInt(st.nextToken()), parseInt(st.nextToken()));
            }

            //매칭
            int cnt = 0;
            Arrays.fill(assigned, -1);
            for (int a = 0; a < n; a++) {
                Arrays.fill(visited, false);
                if (match(a)) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
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
