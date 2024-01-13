package 프로그래머스.카카오.인턴십19;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class 다_불량사용자 {
    /*
    2:18:30 시작
    1:37:40 종료 (67점)

1 2 3 4
12 13 14 23 24 34
    정규표현식으로 걸러냄
    입력에 같은 응모자가 중복해서 제재 아이디 목록에 들어가는 경우는 없다고 함

    1. banned_id 원소들을 key, 매칭되는 유저들의 목록을 val로 하여 map만들기
    2. banned_id의 값 key에 대해, 중복되는 값이 r개 라면 정답을 r!으로 나눔

    fr*d* -> frodo, fradi
    *rodo -> frodo, crodo
    ****** -> abc123, frodoc
     */

    Map<String, List<String>> map = new HashMap<>();
    int last;
    int answer = 0;
    public int solution(String[] user_id, String[] banned_id) {
        last = banned_id.length;
        Set<String> set = Arrays.stream(banned_id).collect(Collectors.toSet());

        for (String s : set) {
            String regex = s.replaceAll("\\*", "[a-z0-9]");
            Pattern pattern = Pattern.compile(regex);

            List<String> list = map.getOrDefault(s, new ArrayList<>());
            for (String userId : user_id) {
                if(pattern.matcher(userId).matches()){
                    list.add(userId);
                }
            }
            map.put(s, list);
        }

        dfs(banned_id, 0);

        return results.size();
    }

    Set<String> visited = new TreeSet<>();
    Set<String> results = new HashSet<>();
    public void dfs(String[] keys, int depth){
        if(depth == last){
            answer+=1;

            results.add(visited.toString());
            return;
        }

        for (String val : map.get(keys[depth])) {
            if(visited.contains(val)) continue;
            visited.add(val);
            dfs(keys, depth+1);
            visited.remove(val);
        }
    }

    public int getFactorial(int n){
        if(n == 1) return 1;
        return n* getFactorial(n-1);
    }
}
