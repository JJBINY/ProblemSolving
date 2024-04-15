package 백준.그래프.최단경로._01BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static java.lang.Integer.parseInt;


/**
 * G4 2665 미로만들기
 * 01BFS, 최단경로
 */
public class G4_2665_미로만들기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int n = parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Deque<Node> dq = new LinkedList<>();
        dq.offerFirst(new Node(0, 0, 1 ^ arr[0][0]));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        while (!dq.isEmpty()){
            Node now = dq.pollFirst();
            if(now.r == n-1 && now.c == n-1){
                System.out.println(now.w);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if(nr<0||nr>=n || nc<0||nc>=n) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if(arr[nr][nc] == 1){
                    dq.offerFirst(new Node(nr, nc, now.w));
                }else{
                    dq.offerLast(new Node(nr, nc, now.w + 1));
                }
            }
        }
    }

    static class Node{
        int r;
        int c;
        int w;

        public Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}