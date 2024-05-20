package 백준.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


/**
 * G5 20437 문자열게임2
 * 문자열, 슬라이딩 윈도우
 */
public class G5_20437_문자열게임2 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            int T = 1;
            int T = parseInt(br.readLine());
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
        String W = br.readLine();
        int K = parseInt(br.readLine());

        Map<Character, List<Integer>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new ArrayList<>());
        }

        for (int i = 0; i < W.length(); i++) {
            List<Integer> list = map.getOrDefault(W.charAt(i), new ArrayList<>());
            list.add(i);
        }

        if(!map.values().stream().anyMatch(l -> l.size() >= K)){
            return -1;
        }

        int[] res = {100_000, 0};
        for (char c = 'a'; c <= 'z' ; c++) {
            List<Integer> list = map.get(c);
            if(list.size()<K) continue;
            for (int i = 0; i <= list.size()-K; i++) {
                res[0] = Math.min(res[0], list.get(i + K-1) - list.get(i)+1);
                res[1] = Math.max(res[1], list.get(i + K-1) - list.get(i)+1);
            }
        }

        return res[0] + " " + res[1];
    }
}