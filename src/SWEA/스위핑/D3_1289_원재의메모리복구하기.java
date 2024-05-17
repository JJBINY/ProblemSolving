package SWEA.스위핑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 1289. 원재의 메모리 복구하기 D3
 * 스위핑, 투포인터,
 */
public class D3_1289_원재의메모리복구하기 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
            int T  = Integer.parseInt(br.readLine());
            //						int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Object solve(BufferedReader br) throws Exception {
        String mem = br.readLine();
        int result = 0;
        char prev = mem.charAt(0);
        if(prev == '1') {
            result =1;
        }

        for (int i = 1; i < mem.length(); i++) {
            if(prev!=mem.charAt(i)) {
                prev = mem.charAt(i);
                result++;
            }
        }

        return result;
    }
}
