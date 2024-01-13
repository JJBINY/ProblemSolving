package 프로그래머스.데브매칭2021;

import java.util.*;

public class 가_로또최고최저순위 {
    //10분 1:51:22
    public int[] solution(int[] lottos, int[] win_nums) {

        Set<Integer> set = new HashSet<>();
        Arrays.stream(win_nums).forEach(i -> set.add(i));
        int zero = 0;
        for (int lotto : lottos) {
            if(lotto == 0){
                zero+=1;
            }else {
                set.remove(lotto);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 6);
        map.put(1, 6);
        map.put(2, 5);
        map.put(3, 4);
        map.put(4, 3);
        map.put(5, 2);
        map.put(6, 1);

        // 6-set size
        return new int[]{map.get(6 - set.size() + zero), map.get(6 - set.size())};
    }
}
