package 백준.자료구조.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * S1_25758_유전자_조합
 * 브루트포스, 해시
 */
public class S1_25758_유전자_조합 {
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

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cnt = new int[26 * 26];
        for (int i = 0; i < N; i++) {
            String gene = st.nextToken();
            cnt[geneToIdx(gene)]++;
        }

        Set<String> combiSet = new HashSet<>();
        for (int i = 0; i < cnt.length; i++) {
            if(cnt[i] == 0) continue;
            String a = idxToGene(i);
            if(cnt[i] >=2){
                combiSet.add(a);
            }
            for (int j = i+1; j < cnt.length; j++) {
                if(cnt[j] == 0) continue;
                String b = idxToGene(j);
                combiSet.add(a.charAt(0) +""+ b.charAt(1));
                combiSet.add(b.charAt(0) +""+ a.charAt(1));
            }
        }

        Set<Character> set = new HashSet<>();
        for (String s : combiSet) {
            if(s.charAt(0) < s.charAt(1)){
                set.add(s.charAt(1));
            }else{
                set.add(s.charAt(0));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(set.size()).append("\n");
        set.stream().sorted().forEach(c->sb.append(c).append(" "));
        return sb;
    }

    static int geneToIdx(String gene){
        return (gene.charAt(0) - 'A') * 26 + (gene.charAt(1) - 'A');
    }

    static String idxToGene(int idx){
        StringBuilder sb = new StringBuilder();
        sb.append((char)('A'+idx / 26)).append((char)('A'+idx % 26));
        return sb.toString();
    }
}