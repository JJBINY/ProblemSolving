package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * G5_13273_로마숫자
 * 구현
 */
public class G5_13273_로마숫자 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
//            int T = 1;
            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] arabians = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    static Object solve(BufferedReader br) throws IOException {
        String number = br.readLine();
        if(isRoman(number)){
            return toArabian(number);
        }else{
            return toRoman(Integer.parseInt(number));
        }
    }

    static boolean isRoman(String number){
        char c = number.charAt(0);
        if(c>='0' && c<='9'){
            return false;
        }
        return true;
    }



    static String toRoman(int number){

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < arabians.length; i++) {
            int coe = number / arabians[i];
            if (coe > 0) {
                roman.append(romans[i].repeat(coe));
                number %= arabians[i];
            }
        }
        return roman.toString();
    }

    static int toArabian(String number){
        int arabian = 0;
        int idx = 0;
        for (int i = 0; i < romans.length; i++) {
            if (idx < number.length()-1 && number.substring(idx, idx + 2).equals(romans[i])) {
                arabian += arabians[i];
                idx += 2;
            }
            while (idx < number.length() && number.substring(idx, idx + 1).equals(romans[i])){
                arabian += arabians[i];
                idx += 1;
            }
        }
        return arabian;
    }

    /*
    1 I, 4 IV, 5 V, 9 IX, 10 X
    40 XL, 50 L, 90 XC, 100 C
    400 CD, 500 D, 900 CM, 1000 M
    최대 3999 = (MMM)(CM)(XC)(IX)
     */
}