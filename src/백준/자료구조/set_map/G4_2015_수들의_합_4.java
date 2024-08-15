package 백준.자료구조.set_map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * G4_2015_수들의_합_4
 * 누적합, 해시맵, 자료구조
 */
public class G4_2015_수들의_합_4 {
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

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Map<Integer, Long> map = new HashMap<>();
        int[] pSums = new int[N + 1];
        long cnt = 0;
        map.put(0, 1L);
        for (int i = 1; i <= N; i++) {
            pSums[i] = pSums[i-1] + Integer.parseInt(st.nextToken());
//            if(pSums[i] == K){
//                cnt++;
//            }
            cnt += map.getOrDefault(pSums[i]-K, 0L);
            map.put(pSums[i], map.getOrDefault(pSums[i], 0L) + 1);
        }
        return cnt;
    }
}