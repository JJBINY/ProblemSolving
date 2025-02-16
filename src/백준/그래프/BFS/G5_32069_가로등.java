package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


/**
 * G5_32069_가로등
 */
public class G5_32069_가로등 {

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long L = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(st.nextToken()); // 가로등 개수
        int K = Integer.parseInt(st.nextToken()); // <= 500,000
        List<Point> A = Arrays.stream(br.readLine().split(" "))
                .map(s -> new Point(Long.parseLong(s), 0))
                .collect(Collectors.toList());

        Deque<Point> dq = new ArrayDeque<>(A);
        Set<Long> visited = new HashSet<>(A.stream().mapToLong(a -> a.idx).boxed().collect(Collectors.toList()));
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty() && cnt++ < K) {
            Point p = dq.poll();
//            System.out.println("p.idx = " + p.idx);
            sb.append(p.darkness).append("\n");
            for (int i = -1; i <2 ; i++) {
                if(i==0) continue;
                long next = p.idx + i;
//                System.out.println("next = " + next);
                if(next < 0 || next> L) continue;
                if(visited.contains(next)) continue;
                visited.add(next);
                dq.offer(new Point(next, p.darkness+1));
            }
        }

        return sb;
    }

    static class  Point{
        long idx;
        int darkness;

        public Point(long idx, int darkness) {
            this.idx = idx;
            this.darkness = darkness;
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