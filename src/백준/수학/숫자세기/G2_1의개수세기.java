package 백준.수학.숫자세기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G2 1의 개수 세기
 * https://www.acmicpc.net/problem/9527
 */
public class G2_1의개수세기 {
    static List<Long> cumulative = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        cumulative.add(0L);
        int bit = 0;
        while ((1L << bit) < b) {
            cumulative.add(2 * cumulative.get(bit) + (1L << bit));
            bit+=1;
        }
        System.out.println(countOneBits(b) - countOneBits(a - 1));
    }

    static long countOneBits(long target){
        if(target <= 1) return target;
        int bit = 1;
        while ((1L << bit) <= target) {
            bit += 1;
        }
        long sum = 0;
        target -= 1L << (bit - 1);
        sum += cumulative.get(bit - 1) + target + 1;
        return sum+countOneBits(target);
    }
}