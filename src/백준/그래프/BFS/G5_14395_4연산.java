package 백준.그래프.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Long.parseLong;

/**
 * 백준.그래프.BFS.G5_14395_4연산
 * 그래프, BFS
 */
public class G5_14395_4연산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long S = parseLong(st.nextToken());
        long T = parseLong(st.nextToken());
        br.close();

        if (S == T) {
            System.out.println(0);
        } else {
            Queue<Pair<Long, StringBuilder>> queue = new LinkedList<>();
            Set<Long> visited = new HashSet<>();
            queue.add(new Pair<>(S, new StringBuilder()));
            visited.add(S);
            while (!queue.isEmpty()) {
                long s = queue.peek().a.longValue();
                StringBuilder sb = queue.poll().b;
                if (s == T) {
                    System.out.println(sb.toString());
                    return;
                }

                if (s < T && s < Math.sqrt(1_000_000_000)) {
                    long ns = s * s;
                    if (!visited.contains(ns)) {
                        visited.add(ns);
                        queue.add(new Pair<>(ns, new StringBuilder(sb.toString()).append('*')));
                    }
                }
                if (s < T) {
                    long ns = s + s;
                    if (!visited.contains(ns)){
                        visited.add(ns);
                        queue.add(new Pair<>(ns, new StringBuilder(sb.toString()).append('+')));
                    }
                }
                long ns = s / s;
                if (!visited.contains(ns)){
                    visited.add(ns);
                    queue.add(new Pair<>(s / s, new StringBuilder(sb.toString()).append('/')));
                }
            }
            System.out.println(-1);
        }
        return;
    }

    static class Pair<T1, T2> {
        T1 a;
        T2 b;

        public Pair(T1 a, T2 b) {
            this.a = a;
            this.b = b;
        }
    }

}
