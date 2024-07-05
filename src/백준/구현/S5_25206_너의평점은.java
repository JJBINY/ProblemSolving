package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * S5_25206_너의평점은
 * 구현, 수학, 문자열
 */
public class S5_25206_너의평점은 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        Map<String, Float> map = new HashMap<>(
                Map.of("A+", 4.5f,
                        "A0", 4.0f,
                        "B+", 3.5f,
                        "B0", 3.0f,
                        "C+", 2.5f,
                        "C0", 2.0f,
                        "D+", 1.5f,
                        "D0", 1.0f,
                        "F", 0.0f));

        float res = 0;
        float credit = 0;
        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            float point = Float.parseFloat(st.nextToken());
            String grade = st.nextToken();
            if (grade.equals("P")) {
                continue;
            }
            credit += point;
            res += point * map.get(grade);
        }
        res /= credit;
        return res;
    }
}