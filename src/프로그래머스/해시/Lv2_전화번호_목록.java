package 프로그래머스.해시;

import java.util.HashSet;

public class Lv2_전화번호_목록 {
    public boolean solution(String[] phoneBook) {

        HashSet set = new HashSet();
        for (String phoneNumber : phoneBook) {
            set.add(phoneNumber);
        }


        for (String phoneNumber : phoneBook) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phoneNumber.length()-1; i++) {
                sb.append(phoneNumber.charAt(i));
                if (set.contains(sb.toString())) {
                    return false;
                }

            }
        }
        return true;
    }
}
