package 백준.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * G5 15686 치킨 배달
 * 조합, 백트래킹
 */
public class G5_15686_치킨배달 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int N, M, MAX_VALUE = 100_000, ans = MAX_VALUE;
    static int[][] arr;
    static List<Point> chickens = new ArrayList<>();

    static void solve(BufferedReader br) throws IOException {
        input(br);
        combination(0,0,0);
        System.out.println(ans);
    }

    static void combination(int state, int cnt, int depth){
        if(cnt >= M || depth>=chickens.size()){
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(arr[i][j] == 1){
                        result += calculateMinDist(state,i, j);
                    }
                }
            }
            ans = Math.min(ans, result);
            return;
        }
        combination(state, cnt, depth + 1);
        combination(state | 1 << depth, cnt + 1, depth + 1);
    }

    private static int calculateMinDist(int state, int r, int c) {
        int dist = MAX_VALUE;
        for (int i = 0; i < chickens.size(); i++){
            Point chicken = chickens.get(i);
            if((state & 1<<i) >0) {
                dist = Math.min(dist, Math.abs(chicken.r - r) + Math.abs(chicken.c - c));
            }
        }
        return dist;
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    chickens.add(new Point(i, j));
                }
            }
        }
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
