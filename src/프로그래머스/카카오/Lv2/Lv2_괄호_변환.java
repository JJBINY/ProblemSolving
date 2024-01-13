package 프로그래머스.카카오.Lv2;

public class Lv2_괄호_변환 {

        /*
    1. 빈문자열인 경우 빈문자열 반환

    2. 문자열 w를 두 균형잡힌 문자열 u,v로 분리
    (u는 균형잡힌 문자열로 더이상 분리 불가능한 균형잡힌 문자열)
    => 앞에서부터 확인해서 처음으로 균형잡힌 문자열을 u 나머지를 v로 쪼갬

    3. (분기1) u가 올바른 문자열이라면 v에 대해 1단계부터 다시 수행 및 그 결과를 u에 이어붙인 후 반환
    => 스택을 이용해 u가 올바른 문자열인지 확인

    4. (분기2) u가 올바른 문자열이 아니라면
    '(' + v에 대해 1단계부터 재귀적으로 수행한 결과 문자열 + ')' + u의 첫번째와 마지막 문자를 제거하고 나머지 괄호 방향을 뒤집은 문자열 반환

    필요한 것
    빈 문자열인지 확인한다.
    w를 u,v로 분리한다.
    올바른 문자열인지 확인한다.
    4단계 로직
     */

    public String solution(String p) {
        return recursive(p);
    }

    private String recursive(String p) {
        if(p.isEmpty()){
            return "";
        }
        if(isCorrectString(p)){
            return p;
        }

        int splitIndex = getSplitIndex(p);
        String u = p.substring(0, splitIndex);
        String v = p.substring(splitIndex);

        if(isCorrectString(u)){
            return u + recursive(v);
        }else{
            return '(' + recursive(v) + ')' + getReverseSubString(u);
        }
    }

    private String getReverseSubString(String u){
        char[] target = u.substring(1, u.length() - 1).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : target) {
            if(c == '('){
                sb.append(')');
            }else{
                sb.append('(');
            }
        }
        return sb.toString();
    }
    private boolean isCorrectString(String u){
        //todo 빈문자인경우 생각
        int cnt = 0;
        for (int i = 0; i < u.length(); i++) {
            if(u.charAt(i) == '('){
                cnt+=1;
            }else{
                cnt-=1;
            }

            if(cnt<0){
                return false;
            }
        }
        return true;
    }

    private int getSplitIndex(String w) {
        int cnt = 0;
        for (int i = 0; i < w.length(); i++) {
            if(w.charAt(i) == '('){
                cnt+=1;
            }else{
                cnt-=1;
            }
            if(cnt == 0 && i>0){
                return i+1;
            }
        }
        return 0;
    }

}
