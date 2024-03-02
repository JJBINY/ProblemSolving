package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 10711 모래성
 * 구현, 그래프, BFS
 */
public class G2_10711_모래성 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        주변 모래성이 쌓여 있지 않는 부분의 갯수가
        자신의 튼튼함 이상인 경우 파도가 치면 무너진다.

        *모래성은 격자의 가장자리와 접해 있지 않다.
     */
    static void solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = parseInt(st.nextToken()); //height
        int W = parseInt(st.nextToken()); //width

        int[][] sands = new int[H][W];
        input(br, H, W, sands);
//        print(arr);

        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (sands[i][j] == 0) {
                    queue.offer(new Pair(i * W + j, 0));
                }
            }
        }

        int[] dh = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
        int[] dw = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
        int wave = 0;

        while (!queue.isEmpty()) {  //O(W*H)
            int h = queue.peek().idx / W;
            int w = queue.peek().idx % W;
            wave = queue.poll().wave;
//            System.out.println("wave = " + wave);
//            print(arr);

            for (int i = 0; i < 8; i++) {
                int nh = h + dh[i];
                int nw = w + dw[i];

                if (nh < 0 || nh >= H || nw < 0 || nw >= W) continue;
                if (sands[nh][nw] == 0) continue;

                sands[nh][nw] -= 1;
                if (sands[nh][nw] == 0) {
                    queue.offer(new Pair(nh * W + nw, wave + 1));
                }
            }
        }

        System.out.println(wave);

    }

    private static void print(int[][] arr) {
        Arrays.stream(arr).forEach(line -> {
            Arrays.stream(line).forEach(i -> System.out.print(i));
            System.out.println();
        });
    }

    private static void input(BufferedReader br, int H, int W, int[][] arr) throws IOException {
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) != '.') {
                    arr[i][j] = str.charAt(j) - '0';
                }
            }
        }
    }

    static class Pair {
        int idx;
        int wave;

        public Pair(int idx, int wave) {
            this.idx = idx;
            this.wave = wave;
        }
    }

}