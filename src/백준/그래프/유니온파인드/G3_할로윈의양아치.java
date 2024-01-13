package 백준.그래프.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G3 할로윈의 양아치
 * https://www.acmicpc.net/problem/20303
 */
public class G3_할로윈의양아치 {

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] candies = new int[n + 1];
        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        List<Item> items = makeGroups(br, n, m, candies);

        int[] dp = new int[k];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int w = k - 1; w >= item.cost; w--) {
                dp[w] = Math.max(dp[w], dp[w - item.cost] + item.val);
            }
        }
        System.out.print(dp[k - 1]);
    }

    private static List<Item> makeGroups(BufferedReader br, int n, int m, int[] candies) throws IOException {
        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] costs = new int[n + 1];
        int[] values = new int[n + 1];
        for (int i = 1; i < n+1; i++) {
            find(i);
            costs[parent[i]] += 1;
            values[parent[i]] += candies[i];
        }
        List<Item> list = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            if(i==parent[i]){
                list.add(new Item(costs[i], values[i]));
            }
        }
        return list;

//        Map<Integer, Item> map = new HashMap<>();
//        for (int i = 1; i < n + 1; i++) {
//            find(i);
//            Item item = map.getOrDefault(parent[i], new Item(0, 0));
//            item.cost += 1;
//            item.val += candies[i];
//            map.put(parent[i], item);
//        }
//        return map.values().stream().collect(Collectors.toList());
    }

    static class Item {
        int cost;
        int val;

        public Item(int cost, int val) {
            this.cost = cost;
            this.val = val;
        }
    }
}