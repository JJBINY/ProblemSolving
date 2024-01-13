package 프로그래머스.카카오.Lv4;
import java.util.*;

public class Lv4_가사_검색 {
    /*
     선형으로는 최악의 경우 1000억 O(n^2), n=10만이므로 불가능
     길이별로 다른 배열에 저장
     오름차순 정렬
     ["frame", "frodo", "front", "frost", "forzen","kakao"]
     fro?? 이분탐색 2번 -> lower bound와 upper bound의 차+1이 정답
     ????o -> 가사들을 뒤집어서 저장하고 정렬한 배열에서 똑같이 이분탐색 2번
    */
    public class Solution {
        public int[] solution(String[] words, String[] queries) {
            String[] arr = new String[words.length];
            String[] rArr = new String[words.length];
            int idx = 0;
            for (String word : words) {
                arr[idx] = word;
                rArr[idx++] = new StringBuilder(word).reverse().toString();
            }
            Arrays.sort(arr);
            Arrays.sort(arr, (a, b) -> a.length() - b.length());
            Arrays.sort(rArr);
            Arrays.sort(rArr, (a, b) -> a.length() - b.length());

            int[] answer = new int[queries.length];

            for (int i = 0; i < queries.length; i++) {
                String query = queries[i];
                int cnt = 0;
                if (query.charAt(0) != '?') {
                    // cnt = getUpperBound(arr,query) - getLowerBound(arr,query) +1;
                    cnt = getLowerBound(arr, query.replaceAll("\\?", "z")) - getLowerBound(arr, query.replaceAll("\\?", "a"));
                } else {
                    String rQuery = new StringBuilder(query).reverse().toString();
                    // cnt = getUpperBound(rArr, rQuery) -getLowerBound(rArr, rQuery) +1;
                    cnt = getLowerBound(rArr, rQuery.replaceAll("\\?", "z")) - getLowerBound(rArr, rQuery.replaceAll("\\?", "a"));
                }
                answer[i] = cnt;

            }

            return answer;
        }

        /*
        LB : FFFF F(T)TTTTTTT froaa
        UB : FFFFFF(F)TTTTTT frozz

        LB : FFFFFFFFFFFF(T) froaa
        UB : FFFFFFFFFFF(F)T frozz => len-1-len+1 =0

        LB : F(T)TTTTTTTTTTT froaa
        UB : (F)TTTTTTTTTTTT frozz => -1-0+1 =0
        목적에따라 ? -> 'a','z'로 대체
        */
        public int getLowerBound(String[] arr, String target) {
            // target = target.replaceAll("\\?","a");
            int lo = -1;
            int hi = arr.length;
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (check(arr[mid], target)) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }
            return hi;
        }

        public int getUpperBound(String[] arr, String target) {
            target = target.replaceAll("\\?", "z");
            int lo = -1;
            int hi = arr.length;
            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;
                if (check(arr[mid], target)) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }
            return lo;
        }

        // target과 비교했을 때 사전순으로 빠르면 false, 느리면 true
        public boolean check(String now, String target) {
            if (now.length() < target.length()) return false;
            if (now.length() > target.length()) return true;

            // 길이 같은 경우
            if (now.compareTo(target) < 0) {
                return false;
            } else {
                return true;
            }
        }
    }
}