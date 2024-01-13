package 백준.그래프.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * P3 룩 어택
 * https://www.acmicpc.net/problem/1574
 */
public class P3_룩어택 {

    static boolean[] visited = new boolean[301];
    static int[] assigned = new int[301];
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = parseInt(st.nextToken());
        int c = parseInt(st.nextToken());
        int n = parseInt(st.nextToken());

        boolean[][] isBlank = new boolean[r + 1][c + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            isBlank[parseInt(st.nextToken())][parseInt(st.nextToken())] = true;
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if(isBlank[i][j]) continue;
                addEdge(i,j);
            }
        }

        //매칭
        int cnt = 0;
        Arrays.fill(assigned, -1);
        for (int a = 1; a <= r; a++) {
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
