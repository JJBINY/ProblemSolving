package 프로그래머스.카카오.인턴십19;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class 나_튜플 {
    /*
    문자열 쪼개서 집합 단위로 만든 다음 길이로 정렬, 가장 긴 튜플을 배열로 변환

    20분
    2:19:00 남음
    */
    public int[] solution(String s) {


        List<int[]> tuples = Arrays.stream(s.substring(0, s.length() - 2)
                        .replaceAll("\\{", "")
                        .split("},"))
                .sorted((t1, t2) -> t1.length() - t2.length())
                .map(t -> Arrays.stream(t.split(",")).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList());


        Set<Integer> set = new LinkedHashSet<>();
        for (int[] tuple : tuples) {
            for (int num : tuple) {
                set.add(num);
            }
        }

        set.stream().forEach(System.out::println);

        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
