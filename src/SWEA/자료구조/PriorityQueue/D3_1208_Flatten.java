package SWEA.자료구조.PriorityQueue;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
/**
 * D3 1208. [S/W 문제해결 기본] 1일차 - Flatten
 * 자료구조, 우선순위 큐, 정렬
 */
public class D3_1208_Flatten {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringBuilder ans = new StringBuilder();
//			int T  = parseInt(br.readLine());
            int T  = 10;
            for (int t = 1; t <= T; t++) {
                ans.append("#").append(t).append(" ")
                        .append(solve(br))
                        .append("\n");
            }
            System.out.println(ans);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static Object solve(BufferedReader br) throws Exception {
        int N = parseInt(br.readLine());
        List<Integer> inputs = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>(Comparator.comparingInt(i->-i));
        PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
        maxQ.addAll(inputs);
        minQ.addAll(inputs);
        for (int i = 0; i < N; i++) {
            maxQ.add(maxQ.poll()-1);
            minQ.add(minQ.poll()+1);
        }
        return maxQ.poll()-minQ.poll();

    }
}
