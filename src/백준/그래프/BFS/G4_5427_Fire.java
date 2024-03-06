package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * G4 5427 Fire
 * BFS
 */
public class G4_5427_Fire {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInt(br.readLine());
            while (T-- > 0) {
                solve(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int W, H;
    static char[][] arr;
    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());


        List<Integer> fires = new ArrayList<>();
        int start = -1;
        arr = new char[H][W];
        for (int i = 0; i < H; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if(arr[i][j] == '*'){
                    fires.add(i * W + j);
                }else if(arr[i][j] == '@'){
                    start = i * W + j;
                }
            }
        }

        boolean[][] visited = new boolean[H][W];
        Queue<Node> queue = new LinkedList<>();

        for (Integer fire : fires) {
            int r = fire / W;
            int c = fire % W;
            visited[r][c] = true;
            queue.offer(new Node(r * W + c, true, 0));
        }

        int r = start / W;
        int c = start % W;
        visited[r][c] = true;
        queue.offer(new Node(r * W + c, false, 0));

        //bfs
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            r = node.idx / W;
            c = node.idx % W;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W){
                    if(!node.isFire){
                        System.out.println(node.sequence+1);
                        return;
                    }
                    continue;
                }
                if(arr[nr][nc] == '#') continue;
                if(arr[nr][nc] == '*') continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if(node.isFire){
                    arr[nr][nc] = '*';
                    queue.offer(new Node(nr * W + nc, true, node.sequence + 1));
                }else {
                    queue.offer(new Node(nr * W + nc, false, node.sequence + 1));
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }


    static class Node {
        int idx;
        boolean isFire;
        int sequence;

        public Node(int idx, boolean isFire, int sequence) {
            this.idx = idx;
            this.isFire = isFire;
            this.sequence = sequence;
        }
    }
}