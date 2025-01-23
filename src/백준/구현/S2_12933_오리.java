package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * S2_12933_오리
 * 구현, 문자열, 그리디
 */
public class S2_12933_오리 {

    /*
    quqacukqauackck
    q  1  3  8
    u  2  6 10
    a  4  9 11
    c  5 12 14
    k  7 13 15

    kcauq
    q 5
    u 4
    a 3
    c 2
    k 1

    qqqqq
    q 1 2 3 4 5
     */
    static Object solve(BufferedReader br) throws IOException {
        String record = br.readLine();
        if (record.length() % 5 != 0) {
            return -1;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('q', 0);
        map.put('u', 1);
        map.put('a', 2);
        map.put('c', 3);
        map.put('k', 4);

        int[] arr = new int[5];
        int res = 0;
        for (char c : record.toCharArray()) {
            int idx = map.get(c);
            arr[idx]++;
            if(idx>0 && arr[idx] > arr[idx-1]) {
                return -1;
            }
            res= Math.max(res, arr[map.get('q')] - arr[map.get('k')]);
        }

        if( Arrays.stream(arr).filter(cnt -> cnt < record.length() / 5).count() > 0){
            return -1;
        }

        return res;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}