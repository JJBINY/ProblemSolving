package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * S5 3054 피터팬 프레임
 * 구현
 */
public class S5_3054_피터팬프레임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[][] chars = new char[3][5 + 4 * (s.length() - 1)];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(chars[i], '.');
        }

        for (int i = 0; i < s.length(); i++) {
            chars[2][2 + 4 * i] = s.charAt(i);
            if((i+1)%3 == 0) continue;
            chars[0][2 + 4 * i] = '#';
            chars[1][2 + 4 * i-1] = '#';
            chars[1][2 + 4 * i+1] = '#';
            chars[2][2 + 4 * i-2] = '#';
            chars[2][2 + 4 * i+2] = '#';
        }
        for (int i = 0; i < s.length(); i++) {
            if((i+1)%3 != 0) continue;
            chars[0][2 + 4 * i] = '*';
            chars[1][2 + 4 * i-1] = '*';
            chars[1][2 + 4 * i+1] = '*';
            chars[2][2 + 4 * i-2] = '*';
            chars[2][2 + 4 * i+2] = '*';
        }
        StringBuilder sb = new StringBuilder();
        sb.append(chars[0]).append("\n");
        sb.append(chars[1]).append("\n");
        sb.append(chars[2]).append("\n");
        sb.append(chars[1]).append("\n");
        sb.append(chars[0]);
        System.out.println(sb.toString());
    }
}