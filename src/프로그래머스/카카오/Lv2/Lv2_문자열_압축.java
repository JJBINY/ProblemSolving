package 프로그래머스.카카오.Lv2;


public class Lv2_문자열_압축 {
        /*
    1. n개 단위로 문자열 자르기
    2. 연속으로 같은 문자열 개수 세기
    3. 개수+단위문자열 스트링 변환
    4. 2단계부터 반복
    */

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length(); i++) {
            String compressed = compress(s, i);
            answer = Math.min(answer, compressed.length());
        }
        return answer;
    }
    public String compress(String s, int n){
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx+n <= s.length()) {

            String target = s.substring(idx, idx + n);
            idx += n;
            int cnt = 1;
            while (idx+n <= s.length() && s.substring(idx, idx + n).equals(target)) {
                cnt += 1;
                idx += n;
            }
            if (cnt > 1) {
                sb.append(cnt);
            }
            sb.append(target);
        }
        sb.append(s.substring(idx));
        return sb.toString();
    }
}
