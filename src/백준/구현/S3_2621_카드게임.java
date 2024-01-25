package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * S3 2621 카드게임
 * 구현
 */
public class S3_2621_카드게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<String> colors = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colors.add(st.nextToken());
            int number = parseInt(st.nextToken());
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        br.close();

        //연속
        int[] numbers = map.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        int straight = 1;
        List<Pair> straights = new ArrayList<>();
        for (int i = 0; i < numbers.length-1; i++) {
            if (numbers[i] + 1 == numbers[i + 1]) {
                straight += 1;
            }else{
                straights.add(new Pair(numbers[i], straight));
                straight = 1;
            }
        }
        straights.add(new Pair(numbers[numbers.length - 1], straight));
        straights.sort(Comparator.comparingInt(p -> p.b));

        //같은숫자들
        List<Pair> counts = map.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .map(e -> new Pair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        //가장높은숫자
        int highest = counts.stream().mapToInt(p -> p.a).max().getAsInt();

        int score;
        if(colors.size()==1 && straights.get(0).b == 5){
            score = 900 + straights.get(0).a;
        }else if(counts.get(0).b == 4){
            score = 800 + counts.get(0).a;
        }else if(counts.get(0).b == 3 && counts.get(1).b==2){
            score = 700 + counts.get(0).a * 10 + counts.get(1).a;
        }else if(colors.size() == 1){
            score = 600 + highest;
        }else if(straights.get(0).b == 5){
            score = 500 + highest;
        }else if(counts.get(0).b == 3){
            score = 400 + straights.get(0).a;
        }else if(counts.get(0).b == 2 && counts.get(1).b == 2){
            score = 300 + Math.max(counts.get(0).a, counts.get(1).a) * 10
                    + Math.min(counts.get(0).a, counts.get(1).a);
        }else if(counts.get(0).b == 2){
            score = 200 + counts.get(0).a;
        }else{
            score = 100 + highest;
        }
        System.out.println(score);
    }

    static class Pair{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
