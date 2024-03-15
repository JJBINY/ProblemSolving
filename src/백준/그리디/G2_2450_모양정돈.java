package 백준.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


/**
 * G2 2450 모양 정돈
 * 그리디, 구현
 */
public class G2_2450_모양정돈 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<List<Integer>> permutation = new ArrayList<>();
    static void solve(BufferedReader br) throws IOException {
        int N = parseInt(br.readLine());
        int[] arr = new int[N];
        int[] cnt = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
            cnt[arr[i]]++;
        }
        permutate(new ArrayList<>());

        int ans = Integer.MAX_VALUE;
        for (List<Integer> p : permutation) {
            int[][] incorrect = new int[4][4];
            int idx = 0;
            for (int num : p) {
                for (int i = 0; i < cnt[num]; i++,idx++) {
                    if(arr[idx] !=num){
                        incorrect[num][arr[idx]]++;
                    }
                } //count the number of incorrect positions
            } //for

            int nChanges = incorrect[1][2] + incorrect[1][3];
            nChanges += Math.max(incorrect[2][3], incorrect[3][2]);
            ans = Math.min(ans, nChanges);
        }//permutation

        System.out.println(ans);
    }

    static void permutate(List<Integer> list){
        if(list.size() >=3){
            permutation.add(list);
        }
        for (int i = 1; i <=3 ; i++) {
            if(list.contains(i)) continue;
            List<Integer> next = new ArrayList<>(list);
            next.add(i);
            permutate(next);
        }

    }
}