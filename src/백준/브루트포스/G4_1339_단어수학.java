package 백준.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * G4 1339 단어수학
 * 완전탐색
 */
public class G4_1339_단어수학 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int ans;
    static List<List<Character>> list = new ArrayList<>();
    static List<Character> alphas = new ArrayList<>();
    static Map<Character, Integer> map = new HashMap<>();

    static void solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            List<Character> l = new ArrayList<>();
            for (int j = word.length()-1; j>=0; j--) {
                l.add(word.charAt(j));
                map.put(word.charAt(j),i);
            }
            list.add(l);
        }

        alphas = map.keySet().stream().collect(Collectors.toList());

        combination(0, new boolean[10]);

        System.out.println(ans);
    }

    static void combination(int idx, boolean[] used){

        if(idx>=alphas.size()){
            ans = Math.max(ans, calculate());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if(used[i]) continue;
            used[i] = true;
            map.put(alphas.get(idx),i);
            combination(idx+1,used);
            used[i] = false;
        }
    }

    static int calculate(){

        int sum = 0;
        for (List<Character> word : list) {
            int degree = 1;
            for (Character c : word) {
                sum += map.get(c) * degree;
                degree*=10;
            }
        }
        return sum;
    }
}