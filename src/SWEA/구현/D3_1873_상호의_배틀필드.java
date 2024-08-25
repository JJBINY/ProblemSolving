package SWEA.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
시간:     135ms
메모리:   21,848kb
 */
public class D3_1873_상호의_배틀필드 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            for (int tc = 1; tc <= T; tc++) {
                sb.append("#").append(tc).append(" ");
                sb.append(solve(br)).append("\n");
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int H, W, r, c;
    static char[][] field;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        field = new char[H][W];

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                field[i][j] = line.charAt(j);
                if (field[i][j] == '<' || field[i][j] == '>' || field[i][j] == '^' || field[i][j] == 'v') {
                    r = i;
                    c = j;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        String commands = br.readLine();
        for (int i = 0; i < N; i++) {
            char cmd = commands.charAt(i);
//            System.out.println("cmd = " + cmd);
            switch (cmd) {
                case 'U': field[r][c] = '^';move();break;
                case 'D': field[r][c] = 'v';move();break;
                case 'L': field[r][c] = '<';move();break;
                case 'R': field[r][c] = '>';move();break;
                case 'S': shoot();break;
                default: break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            sb.append(String.valueOf(field[i])).append("\n");
        }
        return sb.deleteCharAt(sb.length()-1);
    }

    private static void shoot() {
        int nr = r, nc = c;
        while (true) {
//            System.out.printf("%d, %d dir = %c\n",nr,nc,field[r][c]);
            int[] next = getNext(nr, nc, field[r][c]);
            nr = next[0];
            nc = next[1];
            if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                return;
            } else if(field[nr][nc] == '*'){
                field[nr][nc] = '.';
                return;
            }else if(field[nr][nc] == '#'){
                return;
            }
        }
    }

    static void move() {
        int[] next = getNext(r, c, field[r][c]);
        int nr = next[0], nc = next[1];

        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
            return;
        }
        if (field[nr][nc] != '.') {
            return;
        }

        field[nr][nc] = field[r][c];
        field[r][c] = '.';
        r = nr;
        c = nc;
    }

    static int[] getNext(int nr, int nc, int dir){
        switch (dir) {
            case '^': nr--;break;
            case 'v': nr++;break;
            case '<': nc--;break;
            case '>': nc++;break;
            default: break;
        }

        return new int[]{nr, nc};
    }
}

