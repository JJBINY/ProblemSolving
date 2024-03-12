package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G5 1484 다이어트
 * 투포인터
 */
public class G5_1484_다이어트 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int G = parseInt(br.readLine());
        //y^2 = x^2+G
        //G = y^2 - x^2, y>x>0

        StringBuilder sb = new StringBuilder();
        int x = 1;
        int y = 1;
        int cnt =0;
        while (true) {
            int diff = y * y - x * x;

            if (y * y - x * x == G) {
                sb.append(y).append("\n");
                cnt++;
            }else if (y - x == 1 && diff > G) {
                break;
            }
            if (diff <= G) {
                y++;
            }else{
                x++;
            }
        }
        System.out.println(cnt > 0 ? sb.toString() : -1);
    }
}