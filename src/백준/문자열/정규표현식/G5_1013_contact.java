package 백준.문자열.정규표현식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * G5 1013 Contact
 * 문자열, 정규표현식
 */
public class G5_1013_contact {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine());
        Pattern pattern = Pattern.compile("((10(0)+(1)+)|01)+");

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String wave = br.readLine();
            Matcher matcher = pattern.matcher(wave);
            if(matcher.matches()){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

}
