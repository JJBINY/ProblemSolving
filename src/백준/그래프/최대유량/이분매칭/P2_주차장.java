package 백준.그래프.최대유량.이분매칭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * P2 주차장
 * https://www.acmicpc.net/problem/1348
 */
public class P2_주차장 {

    static boolean[] visited = new boolean[50*50];
    static int[] assigned = new int[50*50];
    static Map<Integer, List<Pair<Integer>>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = parseInt(st.nextToken());
        int C = parseInt(st.nextToken());

        String[] arr = new String[R];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine();
        }

        //연결
        int numOfCar = 0;
        edges.clear();
        int[] dr = new int[]{0, 0, 1, -1};
        int[] dc = new int[]{1, -1, 0, 0};
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i].charAt(j) == 'C') {
                    numOfCar++;

                    //bfs : 최단 거리 기록
                    Queue<Pair<Integer>> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[R][C];
                    queue.add(new Pair<>(i * C + j, 0));
                    while (!queue.isEmpty()) {
                        int r = queue.peek().a / C;
                        int c = queue.peek().a % C;
                        int dist = queue.poll().b;
                        if (arr[r].charAt(c) == 'P') {
                            addEdge(numOfCar, new Pair<>(r*C+c, dist));
                        }
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                            visited[nr][nc] = true;
                            if (arr[nr].charAt(nc) == 'X') continue;
                            queue.add(new Pair<>(nr * C + nc, dist + 1));
                        }
                    }
                }
            }
        }

        //이분탐색 (LowerBound)
        int lo = -1;
        int hi = R*C;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            //매칭
            int cnt = 0;
            Arrays.fill(assigned, -1);
            for (int a = 1; a <= numOfCar; a++) {
                Arrays.fill(visited, false);
                if (match(a, mid)) cnt++;
            }

            if(cnt < numOfCar){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        System.out.println(hi == R*C ? -1 : hi);
        br.close();
    }

    private static void addEdge(int a, Pair<Integer> b) {
        List<Pair<Integer>> list = edges.getOrDefault(a, new ArrayList<>());
        list.add(b);
        edges.put(a, list);
    }

    static boolean match(int a, int k) {

        for (Pair<Integer> pair : edges.getOrDefault(a, new ArrayList<>())) {
            if (visited[pair.a] || pair.b > k) continue;
            visited[pair.a] = true;
            if (assigned[pair.a] == -1 || match(assigned[pair.a], k)) {
                assigned[pair.a] = a;
                return true;
            }
        }
        return false;
    }

    static class Pair<T> {
        T a;
        T b;

        public Pair(T a, T b) {
            this.a = a;
            this.b = b;
        }
    }
}
