package 백준.많은조건분기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * S3_1783_병든_나이트
 * 구현, 그리디, 많은 조건 분기
 */
public class S3_1783_병든_나이트 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long height = Long.parseLong(st.nextToken());
        long width = Long.parseLong(st.nextToken());

        if (height == 1) {
            return 1;
        }

        if (height == 2) {
            if (width < 3) {
                return 1;
            } else if (width < 5) {
                return 2;
            } else if (width < 7) {
                return 3;
            } else {
                return 4;
            }
        }

        if (width < 4) {
            return width;
        } else if (width < 7) {
            return 4;
        } else {
            return 4 + width - 6;
        }
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