package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;


/**
 * S1 17615 볼 모으기
 * 그리디
 */
public class S1_17615_볼모으기 {
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
        int N = parseInt(br.readLine());
        String str = br.readLine();

        // 양 끝에 해당 색이 없는 경우 그 색깔 개수만큼 이동해야함
        // 있는 경우 그 색깔 개수 - 끝에서부터 연속된 개수

        long rc = Arrays.stream(str.split("")).filter("R"::equals).count();
        long bc = Arrays.stream(str.split("")).filter("B"::equals).count();
        int ans = (int) Math.min(rc, bc);

        int seq = 0;
        for (int i = 0; i < N; i++) {
            if(str.charAt(i) == str.charAt(0)){
                seq++;
            }else{
                break;
            }
        }
        ans = Math.min(ans, (int) (str.charAt(0) == 'R' ? rc - seq : bc - seq));

        seq = 0;
        for (int i = N-1; i >=0; i--) {
            if(str.charAt(i) == str.charAt(N-1)){
                seq++;
            }else{
                break;
            }
        }
        ans = Math.min(ans, (int) (str.charAt(N-1) == 'R' ? rc - seq : bc - seq));
        return ans;
    }

}