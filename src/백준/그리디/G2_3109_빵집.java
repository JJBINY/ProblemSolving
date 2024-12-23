package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * G2_3109_빵집
 * 그리디, DFS
 */
public class G2_3109_빵집 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int R, C, nConnected;
    static char[][] arr;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                nConnected++;
            }
        }
        return nConnected;
    }

    static int[] dr = {-1, 0, 1};

    static boolean dfs(int r, int c) {
        if (r < 0 || r >= R) return false;
        if(c == C) return true;
//        print();
        if (arr[r][c] != '.') return false;

        arr[r][c] = (char)('0' + nConnected);
        for (int i = 0; i < 3; i++) {
            if (dfs(r + dr[i], c + 1)) {
                return true;
            }
        }

        return false;
    }

    static void print(){
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("=============");
    }
}

/*
..x.......
.....x....
.x....x...
...x...xx.
..........
....x.....

11x1111111
22122x2222
3x2332x333
433x433xx4
5444544445
.555x5555.
 */