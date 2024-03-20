package 프로그래머스.완전탐색;

public class Lv2_모음사전 {
    // 완전탐색
    public int solution(String word) {
        int[] arr = new int[]{156 * 5 + 1, 31 * 5 + 1, 31, 6, 1};
        char[] chars = new char[]{'A', 'E', 'I', 'O', 'U'};
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            ans++;
            for (int j = 0; j < 5; j++) {
                if (word.charAt(i) == chars[j]) {
                    break;
                } else {
                    ans += arr[i];
                }
            }
        } // for c


        return ans;
    }
}