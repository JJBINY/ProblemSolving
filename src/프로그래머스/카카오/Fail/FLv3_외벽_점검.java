package 프로그래머스.카카오.Fail;


import java.util.*;

public class FLv3_외벽_점검 {
    /*
    그리디 : 제일 많이 이동할 수 있는 애가 제일 많이 커버해야 최소 투입이 가능
    dist마다 커버할 수 있는 weak들로 Set 만들기
    set을 합쳤을 때 weak의 수와 같으면 전체 커버 가능 -> 현재가 최소
    ex)weak=[1,5,6,10],
    dist=4 인 경우 [1,5], [5,6], [6,10], [10,1] -> 전체 커버 불가능
    dist=3 인 경우 [5,6], [10,1] -> dist=4인 경우의 Set들과 조합하면 [5,6,10,1]등의 전체 커버 가능한 경우 존재 -> 2명이 최소
    */
    List<List<Set<Integer>>> distSets = new ArrayList<>();
    int weakWall;
    public int solution(int n, int[] weak, int[] dist) {
        weakWall = weak.length;
        Integer[] dists = Arrays.stream(dist).boxed().sorted(Collections.reverseOrder()).toArray(Integer[]::new);

        for (int k = 0; k < dists.length; k++) {
            distSets.add(fixWall(n, weak, dists[k]));
        }
        int ans = collectSet(0, new HashSet<>());
        return ans == 9 ? -1 : ans;
    }

    public int collectSet(int depth, Set<Integer> fixed){
        if(fixed.size() == weakWall || depth == distSets.size()){
            return depth;
        }
         int ans = 9;
        List<Set<Integer>> sets = distSets.get(depth);
        for (int i = 0; i < sets.size(); i++) {
            Set<Integer> collect = new HashSet<>();
            collect.addAll(fixed);
            collect.addAll(sets.get(i));
            int result = collectSet(depth + 1, collect);
            ans = result < ans ? result : ans;
        }
        return ans;
    }

    private static List<Set<Integer>> fixWall(int n, int[] weak, int d) {
        List<Set<Integer>> sets = new ArrayList<>();
        for (int i = 0; i < weak.length; i++) {
            int start = weak[i];
            Set<Integer> set = new HashSet<>();
            findFixable(n, weak, d, start, set);
            sets.add(set);
        }
        return sets;
    }

    private static void findFixable(int n, int[] weak, int d, int start, Set<Integer> set) {
        for (int j = 0; j < weak.length; j++) {
            if ((weak[j] >= start && weak[j] <= start + d) || (n + weak[j] >= start && n + weak[j] <= start + d)) {
                set.add(weak[j]);
            }
        }
    }
}