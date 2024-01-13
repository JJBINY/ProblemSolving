package 프로그래머스.카카오.Lv2;


import java.util.*;
import java.util.stream.Collectors;

public class Lv2_후보키 {
    /*
유일성 만족
- Set에 넣었을 때 size가 전체 size와 같다.

최소성 만족

1. 모든 속성을 검증 리스트에 추가한다

2. 검증 리스트에 남은 속성들로 n개씩 조합을 만든다 (n=[1,속성들의 개수])
2. 해당 조합이 유일성을 만족한다면 후보키이므로 검증 리스트에서 제외한다
3. 복합 속성이 유일성을 만족 -> 후보키
4. 복합 속성이 유일성을 만족하지 않는 경우 2번으로 돌아감

검증대상 set 만들기
A B C D
AB AC AD BC BD CD
ABC ABD ACD BCD
ABCD

*/
    String[][] relation;
    List<Set<Integer>> targets = new ArrayList<>();

    public int solution(String[][] relation) {
        int answer = 0;
        this.relation = relation;
        combination(new HashSet<>(), 0);

        targets.sort((t1,t2) -> {return t1.size() - t2.size();});
        while(!targets.isEmpty()){
            Set<Integer> target = targets.remove(0);
            if(isUnique(target)){
                answer+=1;
                targets = targets.stream().filter(t -> !t.containsAll(target)).collect(Collectors.toList());
            }
        }

        return answer;
    }

    public void combination(Set<Integer> set, int start){

        for (int attribute = start; attribute < relation[0].length; attribute++) {
            Set<Integer> candidateKey = new HashSet<>();
            candidateKey.addAll(set);
            candidateKey.add(attribute);
            targets.add(candidateKey);
            combination(candidateKey,attribute+1);
        }
    }
    public boolean isUnique(Set<Integer> attributes){
        Set<String> set = new HashSet<>();
        for (int row = 0; row < relation.length; row++) {
            StringBuilder sb = new StringBuilder();
            Iterator<Integer> iterator = attributes.iterator();
            while(iterator.hasNext()){
                sb.append(relation[row][iterator.next()]);
            }
            set.add(sb.toString());
        }

        return set.size() == relation.length;
    }
}

