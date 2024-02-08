package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G5 1148 단어 만들기
 * 구현, 문자열, 조합
 */
public class G5_1148_단어만들기 {
    static Map<String, Integer> map = new HashMap<>();
    static String puzzle;
    static int matchCount;
    static Set<String> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String word = br.readLine();
            if (word.equals("-")) {
                break;
            }
            WordHash hash = new WordHash();
            hash.add(word);
            map.put(hash.toString(), map.getOrDefault(hash.toString(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            puzzle = br.readLine();
            if (puzzle.equals("#")) {
                break;
            }

            int[] answers = new int[9];
            for (int i = 0; i < 9; i++) {
                set = new HashSet<>();
                WordHash now = new WordHash();
                now.add(puzzle.charAt(i));
                matchCount = 0;
                combination(now, 0,i);
                answers[i] = matchCount;
            }

            int min = Arrays.stream(answers).min().getAsInt();
            int max = Arrays.stream(answers).max().getAsInt();
            Set<Character> minSet = new HashSet<>();
            Set<Character> maxSet = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (answers[i] == min) {
                    minSet.add((puzzle.charAt(i)));
                }

                if (answers[i] == max) {
                    maxSet.add((puzzle.charAt(i)));
                }
            }

            minSet.stream().sorted().forEach(c -> sb.append(c));
            sb.append(" ").append(min).append(" ");
            maxSet.stream().sorted().forEach(c -> sb.append(c));
            sb.append(" ").append(max).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void combination(WordHash now, int depth, int center) {

        if (now.cnt >= 4 && !set.contains(now.toString())) {
            String key = now.toString();
            set.add(key);
            matchCount += map.getOrDefault(key, 0);
        }

        if (depth >= 9) return;

        combination(now, depth + 1,center);
        if(depth == center) return;

        WordHash next = new WordHash(now);
        next.add(puzzle.charAt(depth));
        combination(next, depth + 1,center);
    }

    static class WordHash {
        int[] chars = new int[26];
        int cnt = 0;
        public WordHash() {
        }

        public WordHash(WordHash hash) {
            for (int i = 0; i < 26; i++) {
                chars[i] = hash.chars[i];
            }
            cnt = hash.cnt;
        }

        void add(String s) {
            for (char c : s.toCharArray()) {
                add(c);
            }
        }

        void add(char c) {
            chars[c - 'A'] += 1;
            cnt += 1;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int count : chars) {
                sb.append(count);
            }
            return sb.toString();
        }
    }
}