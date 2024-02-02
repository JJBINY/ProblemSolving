package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * G3 1022 소용돌이 예쁘게 출력하기
 * 구현
 */
public class G3_1022_소용돌이출력 {

    static Map<Integer, String> map = new HashMap<>();
    static int r1;
    static int c1;
    static int r2;
    static int c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = parseInt(st.nextToken());
        c1 = parseInt(st.nextToken());
        r2 = parseInt(st.nextToken());
        c2 = parseInt(st.nextToken());

        fillArr();
        int maxLen = map.values().stream().mapToInt(String::length).max().getAsInt();

        StringBuilder sb = new StringBuilder();
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                String val = get(i, j);
                sb.append(" ".repeat(maxLen - val.length()))
                        .append(val).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

        br.close();
    }

    private static void fillArr() {
        Set<Integer> vertexes = new HashSet<>();
        vertexes.add(getIdx(r1, c1));
        vertexes.add(getIdx(r1, c2));
        vertexes.add(getIdx(r2, c1));
        vertexes.add(getIdx(r2, c2));

        int r = 0;
        int c = 0;
        int val = 1;
        set(r, c, val);
        int lineLen = 1;
        int dr = -1;
        int dc = 1;

        while (!vertexes.isEmpty()) {
            if ((r == r1 || r == r2) && (c == c1 || c == c2)) vertexes.remove(getIdx(r, c));
            for (int i = 0; i < lineLen; i++) {
                if (vertexes.isEmpty()) return;
                c += dc;
                val++;
                if (r < r1 || r > r2 || c < c1 || c > c2) continue;
                if ((r == r1 || r == r2) && (c == c1 || c == c2)) vertexes.remove(getIdx(r, c));
                set(r, c, val);
            }
            dc *= -1;
            for (int i = 0; i < lineLen; i++) {
                if (vertexes.isEmpty()) return;
                r += dr;
                val++;
                if (r < r1 || r > r2 || c < c1 || c > c2) continue;
                if ((r == r1 || r == r2) && (c == c1 || c == c2)) vertexes.remove(getIdx(r, c));
                set(r, c, val);
            }
            dr *= -1;
            lineLen++;
        }
    }

    private static int getIdx(int r, int c) {
        return (r + 5000) * 10000 + (c + 5000);
    }

    public static void set(int r, int c, int val) {
        map.put(getIdx(r, c), Integer.toString(val));
    }

    public static String get(int r, int c) {
        return map.get(getIdx(r, c));
    }

}
