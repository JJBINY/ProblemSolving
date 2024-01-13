package 프로그래머스.카카오.Lv3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Lv3_외벽_점검_순열사용 {
    /*
    친구들의 이동 거리가 4 1 2순으로 투입되야 해결할 수 있는 경우 등 순서도 중요함 결국 순열을 만들어야함
    => 최악의 경우 8! ~= 4만개 정도
    취약점 직선으로 펼쳐서 배치, 원형을 고려하면 weak 개수만큼 경우의 수(시작지점) 나옴
    => 최악의 경우 15개

    ==> 최악의 경우 대략 60만개정도 완전 탐색
    */
    int answer = 9;
    int n;
    int[] weak;
    List<List<Integer>> serialized = new ArrayList<>();

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        //1 2 3, 2 3 11, 3 11 12, 11 12 13
        List<Integer> weaks = Arrays.stream(weak).boxed().collect(Collectors.toList());
        for (int i = 0; i < weak.length; i++) {
            serialized.add(List.copyOf(weaks));
            weaks.add(weaks.remove(0) + n); //벽 배치 순환
        }
        permute(dist, 0, 0, new LinkedList<>());

        return answer == 9 ? -1 : answer;
    }

    //1600만 *300 = 48억
    //순열 만드는 거 8^8 = 2^24 = 16M = 1600만
    private void permute(int[] dist, int visited, int depth, LinkedList<Integer> result) {
        if (depth == dist.length) {
            // 현재 배치로 고칠 수 있는지 확인 -> 15*23 ~= 300
            for (int i = 0; i < serialized.size(); i++) {
                fixWall(serialized.get(i), result); // 최대 23번
            }
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if ((visited & 1 << i) == 1) {
                continue;
            }
            result.addLast(dist[i]);
            permute(dist, visited | 1 << i, depth + 1, result);
            result.removeLast();
        }
    }

    private void fixWall(List<Integer> weaks, List<Integer> friends) {
        int idx = 0;
        for (int i = 0; i < friends.size(); i++) {

            while (weaks.get(idx) <= weaks.get(0) + friends.get(i)) {
                // 고칠 수 있으면 정답 갱신
                if (++idx == weaks.size()) {
                    answer = answer < i + 1 ? answer : i + 1;
                    return;
                }
            }
        }
    }
}