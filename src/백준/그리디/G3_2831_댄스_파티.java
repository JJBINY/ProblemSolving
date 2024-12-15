package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * G3_2831_댄스_파티
 * 그리디, 정렬, 투포인터
 */
public class G3_2831_댄스_파티 {
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
        int N = Integer.parseInt(br.readLine());
        List<Integer> mt = new ArrayList<>(); // 자신보다 큰 여성 선호하는 남성 그룹
        List<Integer> ms = new ArrayList<>();
        List<Integer> ft = new ArrayList<>();
        List<Integer> fs = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            int height = Integer.parseInt(st.nextToken());
            if (height > 0) {
                mt.add(height);
            } else {
                ms.add(-height);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            int height = Integer.parseInt(st.nextToken());
            if (height > 0) {
                ft.add(height);
            } else {
                fs.add(-height);
            }
        }

        int ans = 0;
        mt.sort(Comparator.comparingInt(h -> h));
        fs.sort(Comparator.comparingInt(h -> h));
        int m = 0, f = 0;
        while (m < mt.size() && f < fs.size()) {
            if (mt.get(m) < fs.get(f)) { // 매칭
                ans++;
                m++;
                f++;
            } else {
                f++;
            }
        }

        ft.sort(Comparator.comparingInt(h -> h));
        ms.sort(Comparator.comparingInt(h -> h));
        m = f = 0;
        while (m < ms.size() && f < ft.size()) {
            if (ft.get(f) < ms.get(m)) {
                ans++;
                m++;
                f++;
            } else {
                m++;
            }
        }
        return ans;
    }
}