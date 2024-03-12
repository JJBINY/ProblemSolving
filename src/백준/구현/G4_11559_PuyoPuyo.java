package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * G4 11559 Puyo Puyo
 * 구현, 시뮬레이션, BFS
 */
public class G4_11559_PuyoPuyo {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        char[][] arr = new char[12][6];

        for (int i = 0; i < 12; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int ans = 0;

        boolean isBurst = true;
        while (isBurst) {
            isBurst = false;

            // 1cycle
            boolean[][] visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j] || arr[i][j] == '.') continue;
                    List<Integer> idxs = new ArrayList<>();

                    //find
                    int cnt = bfs(arr, visited, i, j, idxs);

                    //remove
                    if (cnt >= 4) {
                        isBurst = true;
                        for (Integer idx : idxs) {
                            arr[idx / 6][idx % 6] = '.';
                        }
                    }
                }
            }

            if(isBurst){
                ans++;
            }

            // move down
            for (int col = 0; col < 6; col++) {
                for (int row = 11; row >= 0; row--) {
                    if (arr[row][col] == '.') {
                        moveDown(arr, col, row);
                    }
                }
            }
//            print(arr);
        } // while

        System.out.println(ans);
    }

    private static void print(char[][] arr) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void moveDown(char[][] arr, int col, int row) {
        for (int r = row - 1; r >= 0; r--) {
            if (arr[r][col] != '.') {
                arr[row][col] = arr[r][col];
                arr[r][col] = '.';
                break;
            }
        }
    }

    private static int bfs(char[][] arr, boolean[][] visited, int i, int j, List<Integer> idxs) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * 6 + j);
        visited[i][j] = true;
        int cnt = 0;

        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        while (!queue.isEmpty()) {
            cnt++;
            idxs.add(queue.peek());

            int r = queue.peek() / 6;
            int c = queue.poll() % 6;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6) continue;
                if (nr == 10 && nc == 1) {
                }
                if (visited[nr][nc] || arr[nr][nc] != arr[r][c]) continue;

                visited[nr][nc] = true;
                queue.offer(nr * 6 + nc);

            }
        } //while
        return cnt;
    }
}