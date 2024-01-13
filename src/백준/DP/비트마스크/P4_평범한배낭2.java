package 백준.DP.비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * P4 평범한 배낭2
 * https://www.acmicpc.net/problem/12920
 */
public class P4_평범한배낭2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            int idx = 1;
            while (cnt>0) {
                /*
                cnt개의 숫자를 2^n<=cnt를 만족하는 n개의 비트+나머지를 사용하여 더 적은 숫자로 cnt개를 표현 가능

                예를들어 cnt=13인 경우 4개의 비트를 사용하면,
                 최대 (1111) = 15까지 표현 가능하므로 충분히 표현 가능함
                하지만 14,15인 경우까지 포함되므로 최적해를 구하지 못하게됨
                따라서 3개의 비트를 사용하여 (000~111) = 1~7까지 표현하고
                여기에 6을 더하면 1~7에 추가로 7~13까지 표현 가능해짐
                 */
                int tmp = Math.min(idx, cnt);
                items.add(new Item(cost * tmp, val * tmp));
                idx = idx<< 1;//숫자를 표현하기 위한 비트를 1개 추가한다는 관점이 더 올바름
//                idx *=2;
                cnt -= tmp;
            }

        }

        int[] dp = new int[m + 1];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int c = m; c >= item.cost; c--) {
                dp[c] = Math.max(dp[c], dp[c - item.cost] + item.val);
            }
        }
        System.out.println(dp[m]);
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