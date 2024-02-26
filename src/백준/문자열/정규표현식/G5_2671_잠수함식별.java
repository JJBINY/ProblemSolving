package 백준.문자열.정규표현식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


/**
 * G5 2671 잠수함 식별
 * 문자열, 정규표현식
 */
public class G5_2671_잠수함식별 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            if(Pattern.matches("^((100+1+)|(01))+", br.readLine())){
                System.out.println("SUBMARINE");
            }else{
                System.out.println("NOISE");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
