package SWEA.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    D2_14510_나무_높이
 */
public class D2_14510_나무_높이 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int T = Integer.parseInt(br.readLine());
            for (int tc = 1; tc <= T; tc++) {
                sb.append("#").append(tc).append(" ");
                sb.append(solve(br)).append("\n");
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] values = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxH = Arrays.stream(values).max().getAsInt();
        values = Arrays.stream(values).map(height -> maxH - height).filter(diff -> diff > 0).toArray();

        int nTwo = Arrays.stream(values).map(diff -> diff / 2).sum();
        int nOne = Arrays.stream(values).map(diff -> diff % 2).sum();

//        System.out.println("nOne = " + nOne);
//        System.out.println("nTwo = " + nTwo);

        int day = 0;

        if (nOne > nTwo) {
//            day += nTwo * 2;
//            nOne -= nTwo;
            day += nOne * 2 - 1; // 1 + (x-1)*2
        }else if(nOne == nTwo){
            day += nOne * 2;
        }else { // nOne < nTwo
            day += nOne * 2; // 1 2
            nTwo -= nOne;

            day += nTwo / 3 * 4; // 1 2 1 2 -> 4일 주기로 3개 처리
            nTwo %= 3; // mod 3

            if(nTwo == 2){
                day += 3;
            }else if(nTwo == 1){
                day += 2;
            }
        }

        return day;
    }
}

/*
20
1 3 6 5 5 1 5 4 3 5 4 2 4 6 5 5 4 5 5 3
5 3 0 1 1 5 1 2 3 1 2 4 2 0 1 1 2 1 1 3 -> diff
5 3 1 1 5 1 2 3 1 2 4 2 1 1 2 1 1 3 -> 0제외
2 0 1 1 2 1 2 0 1 2 1 2 1 1 2 1 1 0 -> mod 3 , day = 6*2 = 12
2 1 1 2 1 2 1 2 1 2 1 1 2 1 1 -> 0 제외
nOne = 9, nTwo = 6 -> day += 6*2 -> day = 24
nOne = 3
 */