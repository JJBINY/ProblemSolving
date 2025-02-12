package 백준.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G5_30678_별_안에_별_안에_별_찍기
 */
public class G5_30678_별_안에_별_안에_별_찍기 {

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(5, N);

        char[][] star = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                star[i][j] = ' ';
            }
        }
//        System.out.println("size = " + size);
        fillStar(star, N , size / 2, size / 2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(star[i]).append("\n");
        }

        return sb;
    }

    static void fillStar(char[][] star, int N, int r, int c) {
        if(N==0){
            star[r][c] = '*';
            return;
        }
        int size = (int) Math.pow(5, N-1);
        fillStar(star, N - 1, r - size, c);
        fillStar(star, N - 1, r - size * 2, c);
        for (int i = -2; i < 3; i++) {
            fillStar(star, N - 1, r, c + size * i);
        }
        for (int i = -1; i < 2; i++) {
            fillStar(star, N - 1, r + size, c + size * i);
        }
        fillStar(star, N - 1, r + size * 2, c - size);
        fillStar(star, N - 1, r + size * 2, c + size);
    }

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
}