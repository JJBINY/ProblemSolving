package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * G2 20191 줄임말
 * 이분탐색
 */
public class G2_20191_줄임말 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void solve(BufferedReader br) throws IOException {
        String S = br.readLine();
        String T = br.readLine();

        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            map.put(c, list);
        }

        int idx = -1;
        int n = 1;
        for (char s : S.toCharArray()) {
            if (!map.containsKey(s)) {
                System.out.println(-1);
                return;
            }

            List<Integer> list = map.get(s);
            int result = search(idx, list);
            if (result == list.size()) {
                n++;
                result = 0;
            }

            idx = list.get(result);
        }

        System.out.println(n);
    }

    public static int search(int idx, List<Integer> list) {
        int lo = -1;
        int hi = list.size();
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (list.get(mid) <= idx) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}