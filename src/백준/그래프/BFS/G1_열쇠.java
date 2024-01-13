package 백준.그래프.BFS;

import java.io.*;
import java.util.*;

/**
 * G1 열쇠
 * https://www.acmicpc.net/problem/9328
 */
public class G1_열쇠 {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            String[] arr = new String[h];
            for (int i = 0; i < h; i++) {
                arr[i] = br.readLine();
            }
            String keys = br.readLine();
            int result = bfs(h, w, arr, keys);
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int h, int w, String[] arr, String keys) {

        boolean[][] visited = new boolean[h][w];
        Queue<Pair> queue = new LinkedList<>();
        addEntrance(h, w, arr, visited, queue);

        Map<Character, List<Pair>> doorMap = new HashMap<>();
        Set<Character> keySet = new HashSet<>();
        for (int i = 0; i < keys.length(); i++) {
            if(keys.charAt(i) == '0') continue;
            keySet.add((char) (keys.charAt(i) + 'A' - 'a'));
        }
//        System.out.println("keySet = " + keySet);

        int cnt = 0;
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int r = queue.peek().a;
            int c = queue.poll().b;
//            System.out.println(r+ ", "+c);
            char now = arr[r].charAt(c);
            if (now >= 'A' && now <= 'Z' && !keySet.contains(now)) {
                // 열쇠가 없는 문을 마주친 경우 기록해뒀다가 열쇠를 획득하면 이어서 탐색해야함
                List<Pair> doors = doorMap.getOrDefault(now, new ArrayList<>());
                doors.add(new Pair(r, c));
                doorMap.put(now, doors);
                continue;
            }

            if (now == '$') {
                cnt += 1;
            } else if (now >= 'a' && now <= 'z') {
                char key = (char) (now + 'A' - 'a');
                keySet.add(key);
                if(!doorMap.getOrDefault(key,new ArrayList<>()).isEmpty()){
                    queue.addAll(doorMap.get(key));
                    doorMap.put(key, new ArrayList<>());
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                if(arr[nr].charAt(nc) == '*') continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new Pair(nr, nc));
            }
        }
//        System.out.println("keySet = " + keySet);
        return cnt;
    }

    private static void addEntrance(int h, int w, String[] arr, boolean[][] visited, Queue<Pair> queue) {
        for (int i = 0; i < w; i++) {
            if (arr[0].charAt(i) != '*') {
                queue.add(new Pair(0, i));
                visited[0][i] = true;
            }
            if (arr[h - 1].charAt(i) !='*') {
                queue.add(new Pair(h - 1, i));
                visited[h - 1][i] = true;
            }
        }
        for (int i = 1; i < h - 1; i++) {
            if (arr[i].charAt(0) !='*') {
                queue.add(new Pair(i, 0));
                visited[i][0] = true;
            }
            if (arr[i].charAt(w - 1) !='*') {
                queue.add(new Pair(i, w - 1));
                visited[i][w - 1] = true;
            }
        }
    }

    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}