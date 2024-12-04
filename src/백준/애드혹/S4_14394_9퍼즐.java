package 백준.애드혹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * S4_14394_9퍼즐
 * 애드혹
 */
public class S4_14394_9퍼즐 {
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

    /*
        항상 이동을 통해 각 조각을 원하는 위치에 둘 수 있음
     */
    static Object solve(BufferedReader br) throws IOException {
        char[][] puzzle = new char[2][10];
        puzzle[0] = br.readLine().toCharArray();
        puzzle[1] = br.readLine().toCharArray();
        int[] R = new int[2];
        int[] G = new int[2];
        int[] B = new int[2];
        int[] Y = new int[2];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                if(puzzle[j][i] == 'R'){
                    R[j]++;
                }else if(puzzle[j][i] == 'G'){
                    G[j]++;
                }else if(puzzle[j][i] == 'B'){
                    B[j]++;
                }else if(puzzle[j][i] == 'Y'){
                    Y[j]++;
                }
            }
        }
        int ans = 9 - Math.min(R[0],R[1]) - Math.min(G[0],G[1]) - Math.min(B[0],B[1]) - Math.min(Y[0],Y[1]);
        return ans;
    }
}