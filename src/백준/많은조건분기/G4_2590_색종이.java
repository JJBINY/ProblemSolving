package 백준.많은조건분기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


/**
 * G4 2590 색종이
 * 그리디, 많은 조건 분기,
 */
public class G4_2590_색종이 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            solve(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    1 : 36개
    2 : 9개
    3 : 4개
    4 : 1개
    5 : 1개
    6 : 1개
     */
    static void solve(BufferedReader br) throws IOException {
        int[] paper = new int[7];
        for (int i = 1; i < 7; i++) {
            paper[i] = parseInt(br.readLine());
        }

        //6채우기
        int ans = paper[6];

        //5채우기
        ans += paper[5];
        int oneByOne = 11 * paper[5]; //5채우고 남은 1칸 공간

        //4채우기
        ans += paper[4];
        int twoByTwo = 5 * paper[4]; //4채우고 남은 2x2 공간

        //3채우기
        ans += paper[3] / 4 + (paper[3] % 4 > 0 ? 1 : 0);

        //3채우고 남은 2x2, 1x1 공간
        if (paper[3] % 4 == 1) {
            twoByTwo += 5;
            oneByOne += 7;
        } else if (paper[3] % 4 == 2) {
            twoByTwo += 3;
            oneByOne += 6;
        } else if (paper[3] % 4 == 3) {
            twoByTwo += 1;
            oneByOne += 5;
        }

        //2채우기
        if(paper[2] >= twoByTwo){
            paper[2] -= twoByTwo;
            if(paper[2] >0){
                ans += paper[2] / 9 + (paper[2] % 9 > 0 ? 1 : 0);
                if (paper[2] % 9 > 0) {
                    oneByOne += 36 - (paper[2] % 9) * 4;
                }
            }
        }else{
            oneByOne += (twoByTwo-paper[2]) * 4;
        }

        //1채우기
        if(paper[1] >= oneByOne){
            paper[1] -= oneByOne;
            if (paper[1] > 0) {
                ans += paper[1] / 36 + (paper[1] % 36 > 0 ? 1 : 0);
            }
        }

        System.out.println(ans);
    }
}