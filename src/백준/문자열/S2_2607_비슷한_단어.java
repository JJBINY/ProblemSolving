package 백준.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S2_2607_비슷한_단어
 */
public class S2_2607_비슷한_단어 {

    static int len = 26;

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();
        int[] firstCounts = countAlphabet(firstWord);
        int res = 0;

        for (int i = 0; i < N - 1; i++) {
            String word = br.readLine();
            int[] counts = countAlphabet(word);

            int diff = word.length() == firstWord.length() ? -1 : 0;
            boolean isSimilar = true;
            for (int j = 0; j < len; j++) {
                if (firstCounts[j] != counts[j]) {
                    diff += Math.abs(firstCounts[j] - counts[j]);
                    if(diff > 1){
                        isSimilar = false;
                        break;
                    }
                }
            }
            if(isSimilar){
                res++;
            }
        }
        return res;
    }

    private static int[] countAlphabet(String word) throws IOException {
        int[] arr = new int[len];
        for (char c : word.toCharArray()) {
            arr[c - 'A']++;
        }
        return arr;
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