package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G2 보석 도둑
 * https://www.acmicpc.net/problem/1202
 */
public class G2_보석도둑 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Jewel[] jewels = new Jewel[n];
        int[] bags = new int[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        Arrays.sort(jewels, Comparator.comparing(Jewel::getMass));

        //무게 작은 가방부터 넣을 수 있는 무게 중 최대 가치 넣기
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getVal).reversed());

        int idx = 0;
        long ans = 0;
        for (int i = 0; i < k; i++) {
            while (idx < n) { //현재 가방에 담을 수 있는 무게보다 가벼운 보석들을 pq에 담는다.
                Jewel now = jewels[idx];
                if (now.mass > bags[i]) { //담을 수 없는 경우
                    break;
                }
                pq.add(now);
                idx += 1;
            }

             //현재까지 가장 가치가 높은 보석 담음
            if (!pq.isEmpty()) {
                ans += pq.poll().val;
            }
        }

        System.out.println(ans);
    }

    static class Jewel {
        int mass;
        int val;

        public Jewel(int mass, int val) {
            this.mass = mass;
            this.val = val;
        }
        public int getMass() {
            return mass;
        }
        public int getVal() {
            return val;
        }
    }
}