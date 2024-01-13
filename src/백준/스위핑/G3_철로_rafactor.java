package 백준.스위핑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * G2 철로
 * https://www.acmicpc.net/problem/13334
 */
public class G3_철로_rafactor {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        PriorityQueue<Integer> starts = new PriorityQueue<>();
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] idxes = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).sorted().toArray();
            lines.add(new Line(idxes[0], idxes[1]));
        }
        int d = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            Line line = lines.get(i);
            if(line.end - line.start >d) continue;
            starts.add(line.start);
            ends.add(line.end);
        }

        int cnt = 0;
        int ans = 0;
        while(!ends.isEmpty()){
            Integer now = ends.poll();
            cnt+=1;
            while (!starts.isEmpty()&& starts.peek()<now-d){
                starts.poll();
                cnt-=1;
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    static class Line {
        int start;
        int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
