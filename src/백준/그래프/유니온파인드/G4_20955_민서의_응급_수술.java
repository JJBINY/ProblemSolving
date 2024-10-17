package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;


/**
 * G4_20955_민서의_응급_수술
 * 그래프, 트리, Union-Find
 */
public class G4_20955_민서의_응급_수술 {
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

    static int[] parents;

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, N).toArray();
        int ans = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            u = find(u);
            v = find(v);
            if (u == v) { // 이미 하나의 트리를 구성하는 요소일 경우
                ans++; // 두 뉴런 간의 연결을 끊는다
            } else { // 서로 다른 트리를 구성 중인 경우 두 트리를 연결한다
                union(u, v);
            }
        }
        //        System.out.println("Arrays.toString(parents) = " + Arrays.toString(parents));

        int[] roots  = Arrays.stream(parents).distinct().toArray();
//        System.out.println("Arrays.toString(roots) = " + Arrays.toString(roots));

        // 분리 집합의 개수 -1개 만큼 뉴런을 연결해야 한다
        ans += Arrays.stream(roots).map(r -> find(r)).distinct().count() - 1;
        return ans;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parents[a] = b;
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

}