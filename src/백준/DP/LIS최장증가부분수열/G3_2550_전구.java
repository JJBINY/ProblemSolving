package 백준.DP.LIS최장증가부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * G3_2550_전구
 * DP, LIS(nlogn)
 */
public class G3_2550_전구 {
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
        Map<Integer, Integer> idToIdxMap = new HashMap<>();
        Map<Integer, Integer> bulbToId = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Switch[] switches = new Switch[N+1];
        for (int i = 1; i <= N; i++) {
            int id = Integer.parseInt(st.nextToken());
            switches[i] = new Switch(id);
            idToIdxMap.put(id, i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int id = Integer.parseInt(st.nextToken());
            int idx = idToIdxMap.get(id);
            switches[idx].bulb = i;
            bulbToId.put(i,id);
        }

        int[] dp = new int[N+1];
        int[] x = new int[N + 1];
        Arrays.fill(x, Integer.MAX_VALUE);
        x[0] = -1;
        Stack<int[]> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            int seq = lowerBound(x, switches[i].bulb);
//            System.out.println("seq = " + seq);
            dp[i] = seq;
            stack.push(new int[]{seq, bulbToId.get(switches[i].bulb)});
            x[seq] = Math.min(x[seq], switches[i].bulb);
        }

        StringBuilder sb = new StringBuilder();
        int length = Arrays.stream(dp).max().getAsInt();
        sb.append(length).append("\n");
        int target = length;
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty() && target > 0) {
            int[] ints = stack.pop();
            if (ints[0] == target) {
                target--;
                res.add(ints[1]);
            }
        }
        res.stream().sorted().forEach(r->sb.append(r).append(" "));
        return sb;
    }

    static int lowerBound(int[] x, int key) {
        int lo = -1;
        int hi = x.length;
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;

            if (x[mid] < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    static class Switch {
        int id;
        int bulb;

        public Switch(int id) {
            this.id = id;
        }
    }
}
/*
5
1 2 3 4 5
5 4 3 2 1
->
1
5
---

3
1 2 3
3 1 2
->
2
1 2
 */